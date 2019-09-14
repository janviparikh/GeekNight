package com.thoughtworks.geeknight

import com.thoughtworks.geeknight.repository.ParticipantRepository
import com.thoughtworks.geeknight.repository.ReactiveParticipantRepository
import org.junit.jupiter.api.Test

class ParticipantServiceTest {

    private val participantService = ParticipantService(ParticipantRepository(), ReactiveParticipantRepository())


    @Test
    fun `ParticipantService Non Reactive`() {
        participantService
                .getList(count = 10)
    }

    @Test
    fun `ParticipantService Reactive`() {
        participantService
                .getFlux(count = 10)
    }
}
