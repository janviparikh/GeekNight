package com.thoughtworks.geeknight.service

import com.thoughtworks.geeknight.model.GithubProfile
import com.thoughtworks.geeknight.model.LinkedInProfile
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

class SocialMediaServiceTest {

    @Test
    fun `social media service test`() {
        val profile = SocialMediaService()
                .getSocialMediaProfile("Janvi")

        StepVerifier
                .create(profile)
                .consumeNextWith {
                    it.githubProfile shouldBe GithubProfile(emptyList(), emptyList())
                    it.linkedInProfile shouldBe LinkedInProfile(5.5, 2, "Mumbai")
                }
                .verifyComplete()
    }
}   