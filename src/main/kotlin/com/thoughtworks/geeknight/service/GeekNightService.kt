package com.thoughtworks.geeknight.service

import com.thoughtworks.geeknight.WelcomeMessage
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Service
class GeekNightService {

    fun getGreetMessage(): Mono<WelcomeMessage> {
        return WelcomeMessage("Welcome to geek night")
                .toMono()
    }
}