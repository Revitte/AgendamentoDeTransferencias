package com.jazz.agendamento.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teste")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Spring Boot está rodando na 8080!";
    }
}