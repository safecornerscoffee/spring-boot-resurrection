# Spring Boot REST-ful Service
[![Build Status](https://app.travis-ci.com/safecornerscoffee/spring-boot-resurrection.svg?branch=master)](https://app.travis-ci.com/safecornerscoffee/spring-boot-resurrection)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=safecornerscoffee_spring-boot-resurrection&metric=alert_status)](https://sonarcloud.io/dashboard?id=safecornerscoffee_spring-boot-resurrection)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=safecornerscoffee_spring-boot-resurrection&metric=coverage)](https://sonarcloud.io/dashboard?id=safecornerscoffee_spring-boot-resurrection)

- [x] [Spring Boot를 이용한 RESTful Web Services 개발](https://www.inflearn.com/course/spring-boot-restful-web-services/)
- [ ] [Spring Boot JWT Tutorial](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-jwt/)

## JWT

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.2</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.2</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
    <version>0.11.2</version>
    <scope>runtime</scope>
</dependency>
```

```shell
echo 'jwt-super-secret-key-jwt-super-secret-key-jwt-super-secret-key-jwt-super-secret-key-jwt-super-secret-key-jwt-super-secret-key-jwt-super-secret-key' | base64
```
