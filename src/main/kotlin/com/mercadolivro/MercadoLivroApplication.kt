package com.mercadolivro

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableRabbit
@SpringBootApplication
class MercadoLivroApplication {

/*    @Bean
    fun messageConverter(objectMapper: ObjectMapper): MessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }*/
}

fun main(args: Array<String>) {
    runApplication<MercadoLivroApplication>(*args)
}
