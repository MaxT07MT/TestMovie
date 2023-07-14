package com.example.pattern.example3.repository;

import com.example.pattern.example3.model.OrderR;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderR, Long> {
  List<OrderR> findByCustomerId(Long customerId);
}

