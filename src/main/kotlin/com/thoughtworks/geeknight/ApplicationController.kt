package com.thoughtworks.geeknight

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ApplicationController(val applicationService: ApplicationService) {

    @GetMapping("imperative/doubledNumbers")
    fun getDoubledNumbersImperative(): MutableList<Int> {
        val numbers = NumberGenerator.generateNumberList(count = 10, delay = true)
        return applicationService.getDoubledNumbersImperative(numbers)
    }

    @GetMapping("functional/doubledNumbers")
    fun getDoubledNumbersFunctional(): List<Int> {
        val numbers = NumberGenerator.generateNumberList(count = 10, delay = true)
        return applicationService.getDoubledNumbersFunctionalStream(numbers)
    }

    @GetMapping(value = ["reactive/doubledNumbers"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getDoubledNumbersReactive(): Flux<Int> {
        val producer = NumberGenerator.generateFlux(count = 10, delay = true)
        return applicationService.getDoubledNumbersReactive(producer)
    }

}