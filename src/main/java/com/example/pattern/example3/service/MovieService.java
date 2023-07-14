package com.example.pattern.example3.service;

import com.example.pattern.example3.model.Movie;

import java.util.List;

public interface MovieService {
  List<Movie> getAllMovies();

  Movie getMovieById(Long id);

  void addMovie(Movie movie);

  void updateMovie(Movie movie);

  void deleteMovie(Long id);

  List<Movie> searchMovies(String keyword);
}

