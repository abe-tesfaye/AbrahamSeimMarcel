package com.Customer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public void addNewCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(int customerId, Customer customer) {
        Customer existing = getCustomerById(customerId);
        if (existing != null) {
            existing.setName(customer.getName());
            existing.setEmail(customer.getEmail());
            existing.setPhone(customer.getPhone());
            existing.setAddress(customer.getAddress());
            existing.setPassword(customer.getPassword());
            existing.setUsername(customer.getUsername());
            existing.setGender(customer.getGender());
            existing.setCity(customer.getCity());
            existing.setState(customer.getState());
            customerRepository.save(existing);
        }

    }
}
