package com.thoughtworks.geeknight

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.stream.Collectors.toList

@Service
class ApplicationService {

    fun getDoubledNumbersImperative(numbers: List<Int>): MutableList<Int> {
        println("***************Imperative***************")
        val doubledNumbers = mutableListOf<Int>()
        for (i in numbers) {
            if (i % 2 == 0) {
                val doubled = i * 2
                doubledNumbers.add(doubled)
                println("Imperative Number $doubled")
            }
        }

        return doubledNumbers
    }

    fun getDoubledNumbersFunctionalStream(numbers: List<Int>): List<Int> {
        println("***************Functional***************")

        return numbers
            .stream()
            .filter { it % 2 == 0 }
            .map { it * 2 }
            .map {
                println("Functional Number $it")
                it
            }
            .collect(toList())

    }

    fun getDoubledNumbersReactive(numbers: Flux<Int>): Flux<Int> {
        println("***************Reactive***************")

        return numbers
            .filter { it % 2 == 0 }
            .map { it * 2 }
            .map {
                println("Reactive Number $it")
                it
            }
    }

}