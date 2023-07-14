package com.example.pattern.example3.controller;

import com.example.pattern.example3.model.Customer;
import com.example.pattern.example3.model.Movie;
import com.example.pattern.example3.service.CustomerService;
import com.example.pattern.example3.service.MovieService;
import com.example.pattern.example3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

  private final CustomerService customerService;
  private final MovieService movieService;
  private final OrderService orderService;

  @Autowired
  public CustomerController(CustomerService customerService, MovieService movieService,
      OrderService orderService) {
    this.customerService = customerService;
    this.movieService = movieService;
    this.orderService = orderService;
  }

  @GetMapping("/customers")
  public String getAllCustomers(Model model) {
    List<Customer> customers = customerService.getAllCustomers();
    model.addAttribute("customers", customers);
    return "customer/customerList";
  }

  //  @GetMapping("/info/{id}")
//  public String getCustomerById(@PathVariable("id") Long id, Model model) {
//    Customer customer = customerService.getCustomerById(id);
//    List<OrderR> orders = orderService.getOrdersByCustomerId(id);
//    model.addAttribute("customer", customer);
  //model.addAttribute("orders", orders);
  //   return "customer-details";
  // }
  @GetMapping("/customers/info/{id}")
  public String showCustomerInfo(@PathVariable("id") Long id, Model model) {
    Customer customer = customerService.getCustomerById(id);
    model.addAttribute("customer", customer);
    return "customer/customer-details";
  }

  @GetMapping("/customers/add")
  public String showAddCustomerForm(Model model) {
    model.addAttribute("customer", new Customer());
    return "customer/addCustomer";
  }

  @PostMapping("/customers/add")
  public String addCustomer(@ModelAttribute("customer") Customer customer) {
    customerService.addCustomer(customer);
    return "redirect:/customers";
  }

  @GetMapping("/customers/edit/{id}")
  public String showEditCustomerForm(@PathVariable("id") Long id, Model model) {
    Customer customer = customerService.getCustomerById(id);
    List<Movie> movies = movieService.getAllMovies();
    model.addAttribute("customer", customer);
    model.addAttribute("movies", movies);
    return "customer/editCustomer";
  }

  @PostMapping("/customers/edit/{id}")
  public String updateCustomer(@PathVariable("id") Long id,
      @ModelAttribute("customer") Customer customer) {
    customer.setId(id);
    customerService.updateCustomer(customer);
    return "redirect:/customers";
  }

  @GetMapping("/customers/delete/{id}")
  public String deleteCustomer(@PathVariable("id") Long id) {
    customerService.deleteCustomer(id);
    return "redirect:/customers";
  }

  @PostMapping("/customers/addToOrder/{customerId}")
  public String addToOrder(@PathVariable("customerId") Long customerId,
      @RequestParam("movieId") Long movieId) {
    Customer customer = customerService.getCustomerById(customerId);
    Movie movie = movieService.getMovieById(movieId);
    customer.addToOrder(movie);
    customerService.updateCustomer(customer);
    return "redirect:/customers/edit/" + customerId;
  }

  @PostMapping("/customers/removeFromOrder/{customerId}")
  public String removeFromOrder(@PathVariable("customerId") Long customerId,
      @RequestParam("movieId") Long movieId) {
    Customer customer = customerService.getCustomerById(customerId);
    Movie movie = movieService.getMovieById(movieId);
    customer.removeFromOrder(movie);
    customerService.updateCustomer(customer);
    return "redirect:/customers/edit/" + customerId;
  }

  @GetMapping("/customers/search")
  public String showSearchCustomerPage() {
    return "customer/searchCustomer";
  }

  @GetMapping("/customers/filter")
  public String searchCustomers(@RequestParam("keyword") String keyword, Model model) {
    List<Customer> customers = customerService.searchCustomers(keyword);
    model.addAttribute("customers", customers);
    return "customer/customerList";
  }
}
