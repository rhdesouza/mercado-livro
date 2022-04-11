package com.mercadolivro.infra


import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitConsumer {

    @RabbitListener(queues = ["customerFila"])
    fun receiveMessageFromCustomerFila(message: String) {
        println("Customer Recebido: $message")
    }

}
