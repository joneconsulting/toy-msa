package com.example.userservice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptDemo {
    public static void main(String[] args) {
//        $2a$10$/FK01g0jzGvygjONWyne.O.x0ZM2O7ZCW2PyvTQx4wauDbMYgzVtG
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        int i = 0;
        while (i < 5) {
            String password = "123456";
            String hashedPassword = passwordEncoder.encode(password);
            System.out.println(hashedPassword);
            i++;
        }

        System.out.println(passwordEncoder.matches("123456",
                "$2a$10$GHI5DSLoScy6mBu2uFm0PeJkiN0W5fnsknM/ja7EAhxexTqqO4QTq"));
        System.out.println(passwordEncoder.matches("123456",
                "$2a$10$pKsi9//8s.w20E4e3p3ZqeyC7kOsZHqrYvDRg0auQb0BxmTqH0yzm"));
        System.out.println(passwordEncoder.matches("123456",
                "$2a$10$xu.8LmuYK2WBt2fpUXc3Q.tsiCtEhSDB7essMDXFCcNCOwrHIbBie"));
    }
}
