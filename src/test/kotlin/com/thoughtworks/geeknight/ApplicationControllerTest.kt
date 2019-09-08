package com.thoughtworks.geeknight

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
internal class ApplicationControllerTest {

    @Autowired
    lateinit var webTestClient:WebTestClient

    @Test
    fun `welcome to geeknight`() {

        webTestClient.get().uri("http://localhost:9004/welcome/mandar")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().jsonPath("$.message").isEqualTo("Welcome to geek night mandar")

    }

}