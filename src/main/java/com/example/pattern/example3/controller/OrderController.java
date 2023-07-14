package com.example.pattern.example3.controller;

import com.example.pattern.example3.model.Customer;
import com.example.pattern.example3.model.Movie;
import com.example.pattern.example3.model.OrderR;
import com.example.pattern.example3.service.CustomerService;
import com.example.pattern.example3.service.MovieService;
import com.example.pattern.example3.service.OrderService;
import com.example.pattern.example3.type.MovieTypeFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers/info/{id}/orderR")
public class OrderController {
  private final OrderService orderService;
  private final CustomerService customerService;
  private final MovieService movieService;

  @Autowired
  public OrderController(OrderService orderService, CustomerService customerService,
      MovieService movieService) {
    this.orderService = orderService;
    this.customerService = customerService;
    this.movieService = movieService;
  }

  @GetMapping
  public String getOrderList(@PathVariable("id") Long customerId, Model model) {
    List<OrderR> orders = orderService.getOrdersByCustomerId(customerId);

    double totalAmount = orders.stream()
        .mapToDouble(order -> MovieTypeFactory.calculateAmount(order.getMovie().getMovieType(), 1))
        .sum();

    int totalBonusPoints = orders.stream()
        .mapToInt(order -> MovieTypeFactory.calculateBonus(order.getMovie().getMovieType(), 1))
        .sum();

    model.addAttribute("orders", orders);
    model.addAttribute("totalAmount", totalAmount);
    model.addAttribute("totalBonusPoints", totalBonusPoints);

    return "order/orderList";
  }

  @GetMapping("/add")
  public String showAddOrderForm(@PathVariable("id") Long customerId, Model model) {
    Customer customer = customerService.getCustomerById(customerId);
    List<Movie> movies = movieService.getAllMovies();
    model.addAttribute("customerId", customerId);
    model.addAttribute("customerName", customer.getName());
    model.addAttribute("movies", movies);
    return "order/addOrder";
  }

  @PostMapping("/add")
  public String addOrder(@PathVariable("id") Long customerId, @RequestParam("movieId") Long movieId) {
    orderService.createOrder(customerId, movieId);
    return "redirect:/customers/info/{id}/orderR/add";
  }

  @GetMapping("/delete/{orderId}")
  public String deleteOrder(@PathVariable("orderId") Long orderId) {
    orderService.deleteOrder(orderId);
    return "redirect:/customers/info/{id}/orderR";
  }
}



