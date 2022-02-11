package com.amigos.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerServices(CustomerRepository customerRepository) {
    public  void  registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.LastName())
                .mail(request.mail())
                .build();
        // todo : check if email valid
        // todo: check if email not taken
        // todo : store customer in db
        customerRepository.save(customer);
    }
}
