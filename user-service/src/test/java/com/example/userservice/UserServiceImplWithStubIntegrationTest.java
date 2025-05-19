package com.example.userservice;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.ResponseOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class UserServiceImplWithStubIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UserDto savedUser;

    @BeforeEach
    void setup() {
        // 사용자 생성 요청 DTO 준비
        UserDto userDto = new UserDto();
        userDto.setEmail("edowon0623@gmail.com");
        userDto.setName("Kenneth Lee");
        userDto.setPwd("12345678");

        // 실제 저장 수행
        savedUser = userService.createUser(userDto);
    }

    @Test
    void test_getUserByUserId_integration() {
        // given
        String userId = savedUser.getUserId();

        // WireMock의 stubFor를 사용한 외부 API 응답 설정
        stubFor(get(urlEqualTo("/order-service/" + userId + "/orders"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("[{\"orderId\":\"order-123\",\"productId\":\"item-123\",\"qty\":10}]")));

        // when
        UserDto resultDto = userService.getUserByUserId(userId);

        // then
        assertNotNull(resultDto);
        assertEquals(1, resultDto.getOrders().size());
    }

}
