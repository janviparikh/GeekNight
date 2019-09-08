package com.thoughtworks.geeknight.service

import com.thoughtworks.geeknight.model.GithubProfile
import com.thoughtworks.geeknight.model.LinkedInProfile
import com.thoughtworks.geeknight.model.ParticipantProfile
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Service
class SocialMediaService {

    fun getSocialMediaProfile(user: String): Mono<ParticipantProfile> {
        val linkedInProfile = getLinkedInProfile()
        val githubProfile = getGitHubProfile()

        return Mono.zip(linkedInProfile,githubProfile).map {
            ParticipantProfile(it.t1,it.t2)
        }
/*
        return linkedInProfile.flatMap { linkedIn ->
            githubProfile.map { github ->
                ParticipantProfile(linkedIn, github)
            }
        }
*/
    }

    private fun getGitHubProfile(): Mono<GithubProfile> {
        return GithubProfile(listOf("spring-boot-extension", "jwt-auth"), listOf("java", "python", "kotlin")).toMono()
    }

    private fun getLinkedInProfile(): Mono<LinkedInProfile> {
        return LinkedInProfile(5.5, 2, "Mumbai").toMono()
    }

}