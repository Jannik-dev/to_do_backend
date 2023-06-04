package com.nordenms.to_do

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@SpringBootApplication
@RestController
class ToDoApplication

fun main(args: Array<String>) {
    runApplication<ToDoApplication>(*args)
}

