package com.thoughtworks.geeknight

import com.thoughtworks.geeknight.model.ParticipantProfile
import com.thoughtworks.geeknight.service.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@RestController
class ApplicationController(val geekNightService: GeekNightService,
                            val hostService: HostService,
                            val socialMediaService: SocialMediaService,
                            val trafficMonitoringService: TrafficMonitoringService)
{

    @GetMapping("welcome/{user}")
    fun greetReactive(@PathVariable("user") user: String): Mono<WelcomeMessage> {
        return geekNightService.getGreetMessage()
                .map { WelcomeMessage(it.message.plus(" ").plus(user)) }
                .flatMap { welcomeMessage ->
                    hostService.getHosts()
                            .map {
                                WelcomeMessage(welcomeMessage.message + " : your host for tonight would be " + it.toString())
                            }
                }
                .log()
    }


    @GetMapping("profile/{user}")
    fun getParticipantProfile(@PathVariable("user") user: String): Mono<ParticipantProfile> {
        return socialMediaService
                .getSocialMediaProfile(user)
                .log()
    }


    @GetMapping("welcome1")
    fun greetNonReactive(): WelcomeMessage {
        return WelcomeMessage("Welcome to geek night")
    }

    @GetMapping("feed",produces = ["application/stream+json"])
    fun getFeed(): Flux<TrafficEvent> {
        return trafficMonitoringService.getFeed()
    }

    @GetMapping("welcome-delay")
    fun greetWithDelay(): Mono<WelcomeMessage> {
        return geekNightService.getGreetMessage()
                .map {
                    Thread.sleep(1000)
                    it
                }
    }

}

data class WelcomeMessage(val message: String)
