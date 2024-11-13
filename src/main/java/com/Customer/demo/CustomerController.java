package com.Customer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/new")
    public List<Customer> addNewCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
        return customerService.getAllCustomers();
    }

    @PutMapping("/update/{customerId}")
    public Customer updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) {
        customerService.updateCustomer(customerId, customer);
        return customerService.getCustomerById(customerId);
    }


}
