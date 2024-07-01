### 2023-12-20
* Spring Boot 2.7.6 + Spring Cloud 2021.0.3 버전에 맞춰 예제 파일이 작성 되었습니다.
* Kafka 관련 예제는 kafka 브랜치에서 확인해 보실 수 있습니다.
* Hateoas 관련 예제는 hateoas 브랜치에서 확인해 보실 수 있습니다. 

### 2024-06-30 (수정)
* OpenJDK 17
* Spring Boot 3.2.x(3.2.2, 3.2.4, 3.2.7 확인) + Spring Cloud 2023.0.0(2023.0.2 확인) 버전에 맞춰 예제 파일이 업데이트 되었습니다.
* Spring Security 6 버전으로 회원가입, 로그인, 인증 예제 변경 되었습니다.
* 이전 버전 (Spring Boot 2.7.6) 은 main 브랜치에서 확인하실 수 있습니다.
> * service-discovery: 버전 변경
> * config-service: 버전 변경
> * apigateway-service: 버전 변경
>   * httptrace를 httpexchanges로 변경
>   * AuthenticationHeaderFilter 변경 
> * first-service: 버전 변경
>   * lombok 버전 변경
>   * javax 패키지 jakarta 패키지로 변경
> * second-service: 버전 변경
> * catalog-service: 버전 변경
>   * javax 패키지 jakarta 패키지로 변경
> * order-service: 버전 변경
>   * javax 패키지 jakarta 패키지로 변경
>   * sleuth를 micrometer로 변경
> * user-service: 버전 변경
>   * Spring Security 부분 수정 (6.2.1)
>   * WebSecurityNew 클래스 추가 (기존 WebSecurity 클래스 삭제)
>      * .requestMatchers("/**").access(new WebExpressionAuthorizationManager("hasIpAddress('127.0.0.1') or hasIpAddress('172.30.1.48')")) --> 172.30.1.48 대신 사용하시는 IP Address로 변경 
>   * AuthenticationFilterNew 클래스 추가 (기존 AuthenticationFilter 클래스 삭제)
