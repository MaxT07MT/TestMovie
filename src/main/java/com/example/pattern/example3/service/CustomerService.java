package com.example.pattern.example3.service;

import com.example.pattern.example3.model.Customer;

import com.example.pattern.example3.model.Movie;
import java.util.List;

public interface CustomerService {
  List<Customer> getAllCustomers();

  Customer getCustomerById(Long id);

  void addCustomer(Customer customer);

  void updateCustomer(Customer customer);

  void deleteCustomer(Long id);
  List<Customer> searchCustomers(String keyword);
}