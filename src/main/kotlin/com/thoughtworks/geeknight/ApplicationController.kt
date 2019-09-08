package com.thoughtworks.geeknight

import com.thoughtworks.geeknight.model.ParticipantProfile
import com.thoughtworks.geeknight.service.GeekNightService
import com.thoughtworks.geeknight.service.HostService
import com.thoughtworks.geeknight.service.SocialMediaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ApplicationController(val geekNightService: GeekNightService,
                            val hostService: HostService,
                            val socialMediaService: SocialMediaService
) {

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

}

data class WelcomeMessage(val message: String)
