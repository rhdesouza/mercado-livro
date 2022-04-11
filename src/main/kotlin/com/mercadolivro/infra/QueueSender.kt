package com.mercadolivro.infra

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class QueueSender {

    @Autowired
    private val rabbitTemplate: RabbitTemplate? = null

    @Autowired
    private val queue: Queue? = null

    fun sendCustomer(order: String) {
        rabbitTemplate!!.convertAndSend("customer-exchage", "customer-exchage-customerFila", order);
        println("Enviado para o Rabbit")
    }
}

