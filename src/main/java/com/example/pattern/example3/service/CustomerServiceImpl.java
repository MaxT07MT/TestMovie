// CustomerServiceImpl.java
package com.example.pattern.example3.service;

import com.example.pattern.example3.model.Customer;
import com.example.pattern.example3.model.Movie;
import com.example.pattern.example3.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Customer getCustomerById(Long id) {
    return customerRepository.findById(id).orElse(null);
  }

  @Override
  public void addCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public void updateCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }

  public List<Customer> searchCustomers(String keyword) {
    return customerRepository.findByNameContainingIgnoreCase(keyword);
  }
}
