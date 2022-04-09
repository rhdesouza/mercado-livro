package com.mercadolivro.infra


import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RabbitConsumer {

    @RabbitListener(queues = ["testeFila"])
    fun receiveMessageFromFanout2(message: String) {
        println("Received fanout 2 message: $message")
    }

    @RabbitListener(queues = ["customerFila"])
    fun receiveMessageFromCustomerFila(message: String) {
        println("Customer Recebido: $message")
    }

}
