package com.thoughtworks.geeknight.service

import org.junit.jupiter.api.Test

class GeekNightServiceTest {
    @Test
    fun `should log greetMessage`() {
        val greetMessage = GeekNightService().getGreetMessage()
    }
}