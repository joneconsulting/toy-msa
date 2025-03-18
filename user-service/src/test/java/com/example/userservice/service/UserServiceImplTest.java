package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.vo.RequestOrder;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseOrder;
import com.example.userservice.vo.ResponseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderServiceClient orderServiceClient;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto savedUser;

//    @BeforeEach
//    void createUser() {
//        userDto = new UserDto();
//        userDto.setEmail("edowon0623@gmail.com");
//        userDto.setName("Kenneth Lee");
//        userDto.setPwd("12345678");
//
//        UserEntity userEntity = new UserEntity();
//
//        when(userService.createUser(userDto)).thenReturn(userDto);
//
//        when(userRepository.findByUserId(userDto.getUserId())).thenReturn(Optional.of(userEntity));
//    }

    @BeforeEach
    void test_createUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("edowon0623@gmail.com");
        userDto.setName("Kenneth Lee");
        userDto.setPwd("12345678");

        when(passwordEncoder.encode("12345678")).thenReturn("encodedPassword");

        savedUser = userService.createUser(userDto);
    }

    @Test
    void test_getUserByUserId() {
        // given
        RequestOrder requestOrder = RequestOrder.builder()
                .productId("test-product-id-001").qty(10).unitPrice(1200).build();
        orderServiceClient.createOrder(savedUser.getUserId(), requestOrder);

        List<ResponseOrder> ordersList = new ArrayList<>();
        when(orderServiceClient.getOrders(any(String.class))).thenReturn(ordersList);

        // when
        UserDto userDto = userService.getUserByUserId(savedUser.getUserId());

        // then
        assertNotNull(userDto);
        assertEquals(0, userDto.getOrders().size());
        verify(orderServiceClient, times(1)).getOrders(any(String.class));
    }
}