package com.thoughtworks.geeknight.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.time.Duration

@Service
class TrafficAnalysisService {

    fun analyzeEvents(trafficEvent: TrafficEvent): TrafficEvent {
        println("AnalyzeEvents Running on ${Thread.currentThread().name}")
        Thread.sleep(5000)
        return trafficEvent

    }


    fun sendToAudit(trafficEvent: TrafficEvent): Mono<TrafficEvent> {
        return trafficEvent.toMono()
                .log("SendAudit running on ${Thread.currentThread().name}")
                .map { it }

    }



}
