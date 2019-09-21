package com.thoughtworks.geeknight.service

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class MonitoringClient {
    fun feedLiveData(): Flux<TrafficEvent> {

        return Flux.fromIterable(listOf(
                TrafficEvent(1, "Bandra", "TRAIN_FEED", emptyMap(), "false"),
                TrafficEvent(2, "CST", "STATION_FEED", emptyMap(), "false"),
                TrafficEvent(3, "Vashi", "SIGNAL_FEED", emptyMap(), "true")
        ))
    }
}

data class TrafficEvent(val eventId: Int, val location: String, val type: String, val data: Map<String, Any>, val warning: String) : Comparable<TrafficEvent> {
    override fun compareTo(other: TrafficEvent): Int {
        return eventId.compareTo(other.eventId)
    }
}
