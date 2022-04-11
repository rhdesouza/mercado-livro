package com.mercadolivro.infra


import org.springframework.amqp.AmqpRejectAndDontRequeueException
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitConsumer {

    //@RabbitListener(queues = ["customer-queue"], ackMode = "MANUAL")
    @RabbitListener(queues = ["customer-queue"])
    fun receiveMessageFromCustomerFila(message: String) {
        if (message != null) {
            throw AmqpRejectAndDontRequeueException("Deu ruim")
        }
        println("Customer Recebido: $message")
    }
}
