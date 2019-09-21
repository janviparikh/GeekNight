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
                .parallel()
                .runOn(Schedulers.parallel())
                .map {trafficEvent-> trafficAnalysisService.analyzeEvents(trafficEvent) }
                .sequential()
                .publishOn(Schedulers.elastic())
                .flatMap {trafficEvent-> trafficAnalysisService.sendToAudit(trafficEvent) }
                .sort()
    }

}