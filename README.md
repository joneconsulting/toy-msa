### 2023-12-20
* Spring Boot 2.7.6 + Spring Cloud 2021.0.3 버전에 맞춰 예제 파일이 작성 되었습니다.
* Kafka 관련 예제는 kafka 브랜치에서 확인해 보실 수 있습니다.
* Hateoas 관련 예제는 hateoas 브랜치에서 확인해 보실 수 있습니다. 

### 2024-02-02
* 이전 버전 (Spring Boot 2.7.6) 은 main 브랜치에서 확인하실 수 있습니다.
* 최신 버전 (Spring Boot 3.2.0) 은 springboot3.2 브랜치에서 확인하실 수 있습니다.
  
* OpenJDK 17
* Spring Boot 3.2.x (3.2.2, 3.2.4, 3.2.7) + Spring Cloud 2023.0.0(2023.0.2) 버전에 맞춰 예제 파일이 업데이트 되었습니다.
* Spring Security 6 버전으로 회원가입, 로그인, 인증 예제 변경 되었습니다.

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
>   * AuthenticationFilterNew 클래스 추가 (기존 AuthenticationFilter 클래스 삭제)

### 2024-05-20
* Spring Boot 3.2.x (3.2.2, 3.2.4, 3.2.7) + Kubernetes 배포 (spring3.2_k8s 브랜치) 
* user-service, order-service, catalog-service를 Kubernetes에 배포하는 예제를 추가하였습니다. 
* docker-compose.yml 파일을 이용하여 docker kafka를 기동하시기 바랍니다. 
  * docker-compose.yml 파일에서 'KAFKA_ADVERTISED_LISTENERS' 설정에서 사용하시는 IP로 변경하시기 바랍니다. 
* user-service 실행 
  * kubectl apply -f ./k8s/user-deploy.yml
  * PORT: 60000, NodePort: 32000 사용
* order-service 실행
  * kubectl apply -f ./k8s/order-deploy.yml
  * PORT: 10000, NodePort: 31000 사용
* catalog-service 실행
  * kubectl apply -f ./k8s/catalog-deploy.yml
  * PORT: 8080, NodePort: Random 사용
