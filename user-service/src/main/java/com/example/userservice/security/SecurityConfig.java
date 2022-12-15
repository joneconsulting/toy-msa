package com.example.userservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {
//    private UserService userService;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    private Environment env;
//
//    public SecurityConfig(Environment env, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.env = env;
//        this.userService = userService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @Bean
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();
//        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
//        http.authorizeRequests().antMatchers("/health_check/**").permitAll();
//        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
//        http.authorizeRequests().antMatchers("/error/**").permitAll();
//        http.authorizeRequests().antMatchers("/**")
////                .hasIpAddress(env.getProperty("gateway.ip")) // <- IP 변경
////                .hasIpAddress("127.0.0.1") // <- IP 변경
//                .access("hasIpAddress('127.0.0.1')")
//                .and()
//                .addFilter(getAuthenticationFilter());
//
////        http.authorizeRequests().antMatchers("/users")
////                .hasIpAddress(env.getProperty("gateway.ip")) // <- IP 변경
////                .and()
////                .addFilter(getAuthenticationFilter());
////
////        http.authorizeRequests().anyRequest().denyAll();
//
//        http.headers().frameOptions().disable();
//
//        return http.build();
//    }
//
//    private com.example.userservice.security.AuthenticationFilter getAuthenticationFilter() throws Exception {
//        com.example.userservice.security.AuthenticationFilter authenticationFilter =
//                new com.example.userservice.security.AuthenticationFilter(authenticationManager(), userService, env);
//
//        return authenticationFilter;
//    }
//
//    // select pwd from users where email=?
//    // db_pwd(encrypted) == input_pwd(encrypted)
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
//    }
}