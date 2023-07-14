package com.example.pattern.example3.service;

import com.example.pattern.example3.model.Customer;
import com.example.pattern.example3.model.Movie;
import com.example.pattern.example3.model.OrderR;
import com.example.pattern.example3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final CustomerService customerService;
  private final MovieService movieService;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService, MovieService movieService) {
    this.orderRepository = orderRepository;
    this.customerService = customerService;
    this.movieService = movieService;
  }

  @Override
  public List<OrderR> getOrdersByCustomerId(Long customerId) {
    return orderRepository.findByCustomerId(customerId);
  }

  @Override
  public void createOrder(Long customerId, Long movieId) {
    Customer customer = customerService.getCustomerById(customerId);
    Movie movie = movieService.getMovieById(movieId);
    OrderR order = new OrderR(customer, movie);
    orderRepository.save(order);
  }

  @Override
  public void deleteOrder(Long orderId) {
    orderRepository.deleteById(orderId);
  }
}
