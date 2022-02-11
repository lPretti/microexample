package com.amigos.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerServices {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public  void  registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.LastName())
                .mail(request.mail())
                .build();
        // todo : check if email valid
        // todo : check if email not taken
        // todo : check is fraudster
        customerRepository.saveAndFlush(customer);
        final FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
        // todo : send notification
    }
}
