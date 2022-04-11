package com.mercadolivro.infra

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SenderConfig {

    @Bean
    fun miaoShaQueue(): Queue? {
        return Queue("customerFila", true)
    }

    @Bean
    fun exchange(): DirectExchange? {
        return DirectExchange("customer-exchage", true, false)
    }

    @Bean
    fun exchangeBinding(exchange: DirectExchange?, queue: Queue?): Binding? {
        return BindingBuilder.bind(queue).to(exchange).with("customer-exchage-customerFila")
    }

}