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
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private OrderServiceClient orderServiceClient;

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
//        stubFor(get(urlEqualTo("/"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withBody("")
//                        .withHeader("Content-Type", "application/json")));

        // given
        String userId = savedUser.getUserId();

        // WireMock을 사용한 외부 서비스(OrderService) Mock 설정
        List<ResponseOrder> mockOrders = new ArrayList<>();
        mockOrders.add(ResponseOrder.builder()
                        .orderId("order-1").productId("product-123").unitPrice(1000).qty(1).totalPrice(1000)
                .build());

        when(orderServiceClient.getOrders(userId)).thenReturn(mockOrders);

        // when
        UserDto resultDto = userService.getUserByUserId(userId);

        // then
        assertNotNull(resultDto);
        assertEquals(1, resultDto.getOrders().size());
        verify(orderServiceClient, times(1)).getOrders(userId);
    }

}
