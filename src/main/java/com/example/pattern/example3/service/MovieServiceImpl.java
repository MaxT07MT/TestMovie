package com.example.pattern.example3.service;

import com.example.pattern.example3.model.Movie;
import com.example.pattern.example3.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
  private final MovieRepository movieRepository;

  @Autowired
  public MovieServiceImpl(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  @Override
  public Movie getMovieById(Long id) {
    return movieRepository.findById(id).orElse(null);
  }

  @Override
  public void addMovie(Movie movie) {
    movieRepository.save(movie);
  }

  @Override
  public void updateMovie(Movie movie) {
    movieRepository.save(movie);
  }

  @Override
  public void deleteMovie(Long id) {
    movieRepository.deleteById(id);
  }

  @Override
  public List<Movie> searchMovies(String keyword) {
    return movieRepository.findByTitleContainingIgnoreCase(keyword);
  }
}
