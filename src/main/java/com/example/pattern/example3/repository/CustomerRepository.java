package com.example.pattern.example3.repository;




import com.example.pattern.example3.model.Customer;
import com.example.pattern.example3.model.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByNameContainingIgnoreCase(String keyword);
  }


