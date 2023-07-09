package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import io.micrometer.core.annotation.Timed;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/")
public class UserController {
    private Environment env;
    private UserService userService;

//    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private Greeting greeting;

    @Autowired
    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
//        this.rabbitTemplate = rabbitTemplate;
    }

//    @PostMapping("/send-message")
//    public ResponseEntity sendMessage() {
//        rabbitTemplate.convertAndSend("springCloudBus", "A", "{\"result\":\"OK\"}");
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

    @GetMapping("/health-check")
    @Timed(value="users.status", longTask = true)
    public String status() {
        return String.format("It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", gateway ip(env)=" + env.getProperty("gateway.ip")
                + ", gateway ip(value)=" + greeting.getIp()
                + ", message=" + env.getProperty("greeting.message")
//                + ", token secret=" + env.getProperty("token.secret")
                + ", token secret=" + greeting.getSecret()
                + ", token expiration time=" + env.getProperty("token.expiration_time"));
    }

    @GetMapping("/welcome")
    @Timed(value="users.welcome", longTask = true)
    public String welcome(HttpServletRequest request, HttpServletResponse response) {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);

        ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);

        EntityModel entityModel = EntityModel.of(returnValue);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
        entityModel.add(linkTo.withRel("all-users"));

        try {
            return ResponseEntity.status(HttpStatus.OK).body(entityModel);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @GetMapping("/users/hateoas")
    public ResponseEntity<CollectionModel<EntityModel<ResponseUser>>> getUsersWithHateoas() {
        List<EntityModel<ResponseUser>> result = new ArrayList<>();
        Iterable<UserEntity> users = userService.getUserByAll();

        for (UserEntity user : users) {
            EntityModel entityModel = EntityModel.of(user);
            entityModel.add(linkTo(methodOn(this.getClass()).getUser(user.getUserId())).withSelfRel());

            result.add(entityModel);
        }

        return ResponseEntity.ok(CollectionModel.of(result, linkTo(methodOn(this.getClass()).getUsersWithHateoas()).withSelfRel()));
    }
}
