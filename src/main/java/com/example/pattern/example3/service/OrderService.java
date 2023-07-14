package com.example.pattern.example3.service;

import com.example.pattern.example3.model.OrderR;
import java.util.List;

public interface OrderService {
  List<OrderR> getOrdersByCustomerId(Long customerId);

  void createOrder(Long customerId, Long movieId);

  void deleteOrder(Long orderId);
}
