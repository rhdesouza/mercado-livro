package com.mercadolivro.infra

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class SenderConfig {
    val CUSTOMER_EXCHANGE = "customer-exchage"
    val CUSTOMER_QUEUE = "customer-queue"
    val CUSTOMER_QUEUE_KEY = "customer-queue-key"

    val CUSTOMER_QUEUE_DLQ = "customer-queue.dlq"
    val CUSTOMER_QUEUE_DLQ_KEY = "customer-queue-dlq-key"


    @Bean
    fun miaoShaQueue(): Queue? {
        val args: MutableMap<String, Any> = HashMap()
        // The default exchange
        args["x-dead-letter-exchange"] = CUSTOMER_EXCHANGE
        // Route to the incoming queue when the TTL occurs
        args["x-dead-letter-routing-key"] = CUSTOMER_QUEUE_DLQ_KEY
        // TTL 5 seconds
        args["x-message-ttl"] = 5000

        return Queue(CUSTOMER_QUEUE, true, false, true, args)
    }

    @Bean
    fun exchange(): DirectExchange? {
        return DirectExchange(CUSTOMER_EXCHANGE, true, false)
    }

    @Bean
    fun exchangeBinding(exchange: DirectExchange?, queue: Queue?): Binding? {
        return BindingBuilder.bind(queue).to(exchange).with(CUSTOMER_QUEUE_KEY)
    }


   /* @Bean
    fun dlq() {
        Queue(CUSTOMER_QUEUE_DLQ, true, false, true)
    }

    @Bean
    fun dlqBinding(){
        BindingBuilder.bind(
            Queue(CUSTOMER_QUEUE_DLQ, true, false, true),
        ).to(
            DirectExchange(CUSTOMER_EXCHANGE, true, false)
        ).with(CUSTOMER_QUEUE_DLQ_KEY)
    }*/

}