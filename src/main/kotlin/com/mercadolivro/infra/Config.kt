package com.mercadolivro.infra

import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    @Value("\${customer.queue}")
    val CUSTOMER_QUEUE: String = ""

    @Bean
    fun miaoShaQueue(): Queue? {
        return Queue(CUSTOMER_QUEUE, true)
    }

}