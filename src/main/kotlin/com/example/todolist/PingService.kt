package com.example.todolist

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class PingService {


    @GetMapping("/ping")
    fun ping(): String {
        return "pong"
    }

}