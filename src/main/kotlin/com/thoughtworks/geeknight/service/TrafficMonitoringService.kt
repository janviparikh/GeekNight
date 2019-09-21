package com.thoughtworks.geeknight.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

@Service
class TrafficMonitoringService(val monitoringClient: MonitoringClient,
                               val trafficAnalysisService: TrafficAnalysisService) {

    fun getFeed(): Flux<TrafficEvent> {
        return monitoringClient
                .feedLiveData()
                .map {trafficEvent-> trafficAnalysisService.analyzeEvents(trafficEvent) }
    }

}