package com.example.controller;

import com.example.dao.UserDaoService;
import com.example.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "user-controller", description = "일반 사용자 서비스를 위한 컨트롤러")
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @Operation(summary = "사용자 정보 조회 API", description = "사용자 ID를 이용하여 사용자 상세 정보 조회를 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!"),
    })
    @GetMapping("/users/{id}")
    public User retrieveUser(
            @Parameter(description = "사용자 ID", required = true, example = "1") @PathVariable int id) {
        User user = service.findOne(id);

        try {
            if (user == null) {
                throw new Exception("id-" + id);
            }

            return user;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        User savedUser = service.save(user);
    }
}
