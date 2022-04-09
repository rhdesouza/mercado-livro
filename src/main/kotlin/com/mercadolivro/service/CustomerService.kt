package com.mercadolivro.service

import com.mercadolivro.infra.RabbitService
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
) {
    fun getAll(name: String?): List<CustomerModel> {
        if (name != null){
            return customerRepository.findByNameContaining(name)
        }
       return customerRepository.findAll()?.toList()
    }

    fun create(customer: CustomerModel) {
        var newCustomer = customerRepository.save(customer)
        RabbitService("customer","customerFila","customerKey").send(newCustomer.toString())

    }

    fun getById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow();
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        customerRepository.delete(getById(id))
    }

}

