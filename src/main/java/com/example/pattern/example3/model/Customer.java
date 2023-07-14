package com.example.pattern.example3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client_table")
@Data
@NoArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column
  private String name;
  @Column
  private String phone;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
  private List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
    rental.setCustomer(this);
  }

  @ManyToMany
  @JoinTable(
      name = "customer_movie",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "movie_id")
  )
  private List<Movie> order = new ArrayList<>();

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderR> orderRS = new ArrayList<>();

  public Customer(String name, String phone) {
    this.name = name;
    this.phone = phone;
  }

  public void addToOrder(Movie movie) {
    OrderR orderR = new OrderR(this, movie);
    orderRS.add(orderR);
    movie.getOrderRS().add(orderR);
  }

  public void removeFromOrder(Movie movie) {
    OrderR orderR = new OrderR(this, movie);
    movie.getOrderRS().remove(orderR);
    orderRS.remove(orderR);
    orderR.setCustomer(null);
    orderR.setMovie(null);
  }
  @ManyToMany
  @JoinTable(
      name = "customer_movie",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "movie_id")
  )
  private List<Movie> movies = new ArrayList<>();
}
