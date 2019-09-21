package com.thoughtworks.geeknight

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Duration


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
internal class ApplicationControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setUp() {
        webTestClient = webTestClient.mutate().responseTimeout(Duration.ofSeconds(20)).build()
    }

    @Test
    fun `welcome to geeknight`() {

        webTestClient.get().uri("http://localhost:9004/welcome/mandar")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().jsonPath("$.message")
                .isEqualTo("Welcome to geek night mandar : your host for tonight would be [Agent A , Agent B]")

    }


    @Test
    fun `participant profile`() {

        webTestClient.get().uri("http://localhost:9004/profile/partho")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().json("{\"linkedInProfile\":{\"experience\":5.5,\"companies\":2,\"location\":\"Mumbai\"},\"githubProfile\":{\"projects\":[],\"languages\":[]}}\n")

    }


    @Test
    fun `traffic  feed`() {

        webTestClient.get().uri("http://localhost:9004/feed")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .json("{\"eventId\":1,\"location\":\"Bandra\",\"type\":\"TRAIN_FEED\",\"data\":{},\"warning\":\"false\"}\n" +
                        "{\"eventId\":2,\"location\":\"CST\",\"type\":\"STATION_FEED\",\"data\":{},\"warning\":\"false\"}\n" +
                        "{\"eventId\":3,\"location\":\"Vashi\",\"type\":\"SIGNAL_FEED\",\"data\":{},\"warning\":\"true\"}")
    }

}