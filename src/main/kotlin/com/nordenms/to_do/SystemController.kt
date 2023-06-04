package com.nordenms.to_do

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController

class SystemController {
    @GetMapping("/status")
    fun hello(): String {
        val current = LocalDateTime.now()

        return "Time: $current | Server is up and running"
    }
}