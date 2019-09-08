package com.thoughtworks.geeknight

import com.thoughtworks.geeknight.service.GeekNightService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ApplicationController(val geekNightService: GeekNightService) {

    @GetMapping("welcome")
    fun greetReactive(): Mono<WelcomeMessage> {
     return geekNightService.getGreetMessage()
    }

    @GetMapping("welcome1")
    fun greetNonReactive(): WelcomeMessage {
        return WelcomeMessage("Welcome to geek night")
    }

}

data class WelcomeMessage(val message: String)
