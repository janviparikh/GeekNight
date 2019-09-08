package com.thoughtworks.geeknight.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.lang.RuntimeException

@Service
class HostService {

    fun getHosts(): Mono<List<String>> {
        return getHostsFromRemoteService()
                .doOnError {
                    println("Error encountered ${it.message}")
                }
                .onErrorReturn(listOf("Agent A , Agent B"))


    }

    private fun getHostsFromRemoteService() = Mono.just(listOf("Janvi", "Partha", "Nikesh"))
            .map {
                throw RuntimeException("error while calling")
                it
            }
}
