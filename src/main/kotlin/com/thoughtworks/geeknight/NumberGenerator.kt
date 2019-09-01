package com.thoughtworks.geeknight

import reactor.core.publisher.Flux
import java.time.Duration

class NumberGenerator {

    companion object {
        fun generateNumberList(count: Int, delay: Boolean = true): MutableList<Int> {
            val numbers = mutableListOf<Int>()
            for (i in 1..count) {
                numbers.add(i)
                if (delay)
                    Thread.sleep(1000)
            }
            return numbers
        }

        fun generateFlux(count: Long, delay: Boolean = true): Flux<Int> {
            return Flux
                .interval(if (delay) Duration.ofSeconds(1) else Duration.ZERO)
                .take(count)
                .map { it.toInt() }
        }
    }
}
