package com.example.pattern.example3.controller;

import com.example.pattern.example3.model.Movie;
import com.example.pattern.example3.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping("/movies")
  public String getMovies(Model model) {
    List<Movie> movies = movieService.getAllMovies();
    model.addAttribute("movies", movies);
    return "movie/movieList";
  }

  @GetMapping("/movies/add")
  public String showAddMovieForm(Model model) {
    model.addAttribute("movie", new Movie());
    return "movie/addMovie";
  }

  @PostMapping("/movies/add")
  public String addMovie(@ModelAttribute("movie") Movie movie) {
    movieService.addMovie(movie);
    return "redirect:/movies";
  }

  @GetMapping("/movies/edit/{id}")
  public String showEditMovieForm(@PathVariable("id") Long id, Model model) {
    Movie movie = movieService.getMovieById(id);
    model.addAttribute("movie", movie);
    return "movie/editMovie";
  }

  @PostMapping("/movies/update/{id}")
  public String updateMovie(@PathVariable("id") Long id, @ModelAttribute("movie") Movie movie) {
    movie.setId(id);
    movieService.updateMovie(movie);
    return "redirect:/movies";
  }

  @GetMapping("/movies/delete/{id}")
  public String deleteMovie(@PathVariable("id") Long id) {
    movieService.deleteMovie(id);
    return "redirect:/movies";
  }

  @GetMapping("/movies/search")
  public String showSearchMoviePage() {
    return "movie/searchMovie";
  }

  @GetMapping("/movies/filter")
  public String filterMovies(@RequestParam("keyword") String keyword, Model model) {
    List<Movie> movies = movieService.searchMovies(keyword);
    model.addAttribute("movies", movies);
    return "movie/movieList";
  }


}
