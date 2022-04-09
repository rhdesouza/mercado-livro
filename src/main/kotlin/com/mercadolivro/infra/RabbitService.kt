package com.mercadolivro.infra

import com.rabbitmq.client.ConnectionFactory

class RabbitService(
    val direcionador: String, val fila: String, val chaveDirecionadorFila: String
) {
    private val connectionFactory: ConnectionFactory

    init {
        connectionFactory = ConnectionFactory()
        connectionFactory.port = 5672
        connectionFactory.host = "localhost"
        connectionFactory.username = "guest"
        connectionFactory.password = "guest"
        connectionFactory.virtualHost = "/"
    }

    fun gimmeFactory(): ConnectionFactory {
        return connectionFactory;
    }

    fun defaultExcengeAndQueue() {
        val newConnection = gimmeFactory().newConnection()
        val channel = newConnection.createChannel()

        channel.exchangeDeclare(direcionador, "direct", true)
        channel.queueDeclare(fila, true, false, false, emptyMap())
        channel.queueBind(fila, direcionador, chaveDirecionadorFila)

        channel.close()
        newConnection.close()
    }

    fun send(mensagem: String) {
        try {
            defaultExcengeAndQueue()

            val connection = gimmeFactory().newConnection()
            val channel = connection.createChannel()

            channel.basicPublish(direcionador, chaveDirecionadorFila, null, mensagem.toByteArray())
            println("Mensagem enviada.")
        } catch (e: Exception) {
            e.stackTrace
        }
    }

}

fun main() {
    RabbitService("testeExchange", "testeFila", "testeKey").defaultExcengeAndQueue()

    val connection = RabbitService("testeExchange", "testeFila", "testeKey").gimmeFactory().newConnection()
    val channel = connection.createChannel()

    channel.basicPublish("testeExchange", "testeKey", null, "{mensagem: Teste mensagem_3}".toByteArray())
    println("Mensagem enviada.")

}