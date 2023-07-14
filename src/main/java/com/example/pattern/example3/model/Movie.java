package com.example.pattern.example3.model;

import com.example.pattern.example3.type.MovieType;
import com.example.pattern.example3.type.MovieTypeFactory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "film_table1")
@Data
@NoArgsConstructor
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column
  private String title;
  @Column
  private MovieType movieType;
  @Column
  private String country;
  @Column
  private String director;
  @Column
  private String description;

  public Movie(String title, MovieType movieType, String country, String director, String description) {
    this.title = title;
    this.movieType = movieType;
    this.country = country;
    this.director = director;
    this.description = description;
  }

  public double getAmount() {
    int daysRented = orderRS.size(); // Получаем количество дней аренды фильма
    return MovieTypeFactory.calculateAmount(movieType, daysRented);
  }

  public int getBonus() {
    int daysRented = orderRS.size(); // Получаем количество дней аренды фильма
    return MovieTypeFactory.calculateBonus(movieType, daysRented);
  }
  @ManyToMany(mappedBy = "order")
  private List<Customer> customers = new ArrayList<>();


  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderR> orderRS = new ArrayList<>();



}


