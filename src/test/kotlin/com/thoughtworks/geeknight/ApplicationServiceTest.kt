package com.thoughtworks.geeknight

import org.junit.jupiter.api.Test

class ApplicationServiceTest {

    private val applicationService = ApplicationService()

    @Test
    fun `ApplicationService Imperative`() {
        applicationService
                .getDoubledNumbersImperative(NumberGenerator.generateNumberList(count = 10, delay = true))
    }

    @Test
    fun `ApplicationService Functional`() {
        applicationService
                .getDoubledNumbersFunctionalStream(NumberGenerator.generateNumberList(count = 10, delay = true))
    }

    @Test
    fun `ApplicationService Reactive`() {
        applicationService
                .getDoubledNumbersReactive(NumberGenerator.generateFlux(count = 10, delay = true))
                .subscribe()

        Thread.sleep(10000)
    }
}
