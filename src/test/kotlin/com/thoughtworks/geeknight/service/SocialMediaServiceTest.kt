package com.thoughtworks.geeknight.service

import com.thoughtworks.geeknight.model.GithubProfile
import com.thoughtworks.geeknight.model.LinkedInProfile
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class SocialMediaServiceTest {

    @Test
    fun `social media service test`() {
        val profile = SocialMediaService()
                .getSocialMediaProfile("Janvi")
                .block()!!

        profile.githubProfile shouldBe GithubProfile(emptyList(), emptyList())
        profile.linkedInProfile shouldBe LinkedInProfile(5.5, 2, "Mumbai")
    }
}