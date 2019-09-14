package com.thoughtworks.geeknight.repository

import org.springframework.stereotype.Component
import reactor.core.publisher.toFlux

@Component
class ReactiveParticipantRepository {
    private val participants = listOf("Shailesh", "Thota", "Ajay", "Avinash", "Shubham", "Vinayak", "HARSHAD",
            "Rajan", "Ashwin", "Mithila", "Rupesh", "Ayush", "Nitin", "Ankit", "Lkki", "AMIT", "Ashish",
            "Vibin", "Ujjwal", "Vachan", "Jitendra").toFlux()

    fun getParticipants() = participants
}