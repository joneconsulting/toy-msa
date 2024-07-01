package com.example.firstservice;

import com.example.firstservice.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
@Tag(name = "first-service-controller", description = "일반 사용자 서비스를 위한 컨트롤러입니다.")
public class FirstServiceController {
    Environment env;

    @Autowired
    public FirstServiceController(Environment env) {
        this.env = env;
    }

    // GET http://localhost:12345/first-service/welcome
    @Operation(summary = "Welcome API", description = "Hello 메시지를 출력하는 테스트 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR!!"),
    }
    )
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First service.";
    }

    @Operation(summary = "Request Header 예제 API", description = "사용자로부터 Request Header 값을 받아오거나, Gateway에서 일괄적인 Header를 부여할 수 있습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR!!"),
    }
    )
    @GetMapping("/message")
    public String message(@Parameter(description = "Request Header", required = true, example = "request-header-by-java") @RequestHeader("first-request") String header) {
        log.info(header);
        return "Hello World in First Service.";
    }

    @Operation(summary = "Port Check API", description = "First service의 Port 정보를 확인하기 위한 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR!!"),
    }
    )
    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port={}", request.getServerPort());
        User user = new User();

        return String.format("Hi, there. This is a message from First Service on PORT %s"
                , env.getProperty("local.server.port"));
    }
}
