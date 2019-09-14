package com.thoughtworks.geeknight.repository

import org.springframework.stereotype.Component

@Component
class ParticipantRepository {
    private val participants = listOf("Shailesh", "Thota", "Ajay", "Avinash", "Shubham", "Vinayak", "HARSHAD",
            "Rajan", "Ashwin", "Mithila", "Rupesh", "Ayush", "Nitin", "Ankit", "Lkki", "AMIT", "Ashish",
            "Vibin", "Ujjwal", "Vachan", "Jitendra")

    fun getParticipants() = participants
}