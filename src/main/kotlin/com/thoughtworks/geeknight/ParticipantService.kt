package com.thoughtworks.geeknight

import com.thoughtworks.geeknight.repository.ParticipantRepository
import com.thoughtworks.geeknight.repository.ReactiveParticipantRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ParticipantService(val participantRepository: ParticipantRepository,
                         val reactiveParticipantRepository: ReactiveParticipantRepository) {


    fun getList(count: Int): List<String> {
        return participantRepository
                .getParticipants()
                .subList(0, count - 1)
                .map {
                    println("Participant : $it")
                    it
                }
    }

    fun getFlux(count: Long): Flux<String> {
        return reactiveParticipantRepository
                .getParticipants()
                .take(count)
                .map {
                    println("Participant : $it")
                    it
                }
    }
}
