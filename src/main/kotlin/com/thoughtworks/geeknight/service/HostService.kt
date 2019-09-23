package com.thoughtworks.geeknight.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class HostService {

    fun getHosts(): Mono<List<String>> {
        return Mono.just(listOf("Janvi", "Partha", "Nikesh")).log()
    }

}