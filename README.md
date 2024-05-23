
<img width="797" alt="스크린샷 2024-05-19 오후 5 12 44" src="https://github.com/KNU-AEYE/AEYE-BE/assets/82511301/5fc1ca39-d5e2-4775-aed8-636927542951">

## 종합설계프로젝트2 A-EYE
빅데이터 기반 텍스트화된 이벤트 영상을 검색하는 AI 모니터링 시스템 구축
<br>
<br>

## 프로젝트 아키텍처
![A-EYE (3)](https://github.com/KNU-AEYE/AEYE-BE/assets/82511301/e9fc932a-ae15-4618-8a0e-7710c0eb0b23)

## 시작 가이드
### 개발 환경
- Spring Boot 3.2.4
- Java 17
- Postgresql

### 필요 파일
`src/main/resources/application.yml` 아래 양식의 파일이 필요합니다.
<details>
<summary>application.yml</summary>

```yaml
spring:
  datasource:
    url: 
    username: 
    password: 
    driver-class-name: org.postgresql.Driver

  jpa:
    database: postgresql
    show-sql: true
    generate-ddl: true
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        default_batch_fetch_size: 500

  security:
    oauth2:
      client:
        redirect-uri: 
        registration:
          google:
            client-id: 
            client-secret: 
            scope:
              - email
              - profile
          kakao:
            client-id: 
            client-secret: 
            scope:
              - profile_nickname
              - profile_image
              - account_email
            authorization-grant-type: authorization_code
            redirect-uri: http://localhost:8080/login/oauth2/code/kakao
            client-authentication-method: client_secret_post
        provider:
          kakao:
            authorization-uri: https://kauth.kakao.com/oauth/authorize
            token-uri: https://kauth.kakao.com/oauth/token
            user-info-uri: https://kapi.kakao.com/v2/user/me
            user-name-attribute: id

  elasticsearch:
    username: 
    password: 
    host: 

  cloud:
    gcp:
      storage:
        credentials:
          location: classpath:
        project-id:
        bucket: 
        default-thumbnail-uri: 

  sendgrid:
    from: 
    template-id: 
    api-key: 

logging:
  level:
    org:
      springframework:
        security: DEBUG
      data:
        elasticsearch:
          client:
            WIRE: TRACE

ncp:
  service-id: 
  access-key: 
  secret-key: 
  plus-friend-id: 

springdoc:
  packages-to-scan: com.server.aeye
  default-consumes-media-type: application/json;charset=UTF-8
  default-produces-media-type: application/json;charset=UTF-8
  swagger-ui:
    tags-sorter: alpha
    operations-sorter: alpha
    display-request-duration: true
  api-docs:
    path: /api-docs/json
    groups:
      enabled: true

server:
  forward-headers-strategy: framework

ffmpeg:
  path: 
ffprobe:
  path: 


jwt:
  secret: 
```
</details>

`src/main/resources/credentials.json` 파일이 필요합니다.
[Google Cloud Storage 인증 JSON 발급방법 참고](https://choo.oopy.io/35bffd94-7a41-4cfa-812c-b8aaf148604a)
