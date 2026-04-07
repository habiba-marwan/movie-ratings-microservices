package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import com.example.movieinfoservice.repository.MovieRepo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    private RestTemplate restTemplate;
    private MovieRepo movieRepo;

    public MovieResource(RestTemplate restTemplate, MovieRepo movieRepo) {
        this.restTemplate = restTemplate;
        this.movieRepo = movieRepo;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        // Get the movie info from TMDB
        // final String url = "https://api.themoviedb.org/3/movie/" + movieId +
        // "?api_key=" + apiKey;
        // MovieSummary movieSummary = restTemplate.getForObject(url,
        // MovieSummary.class);

        // return new Movie(movieId, movieSummary.getTitle(),
        // movieSummary.getOverview());

        // check MongoDB first
        Optional<MovieSummary> cachedMovie = movieRepo.findById(movieId);

        if (cachedMovie.isPresent()) {
            System.out.println("Fetching from MongoDB Cache...");
            MovieSummary summary = cachedMovie.get();
            return new Movie(movieId, summary.getTitle(), summary.getOverview());
        }

        // if not in cache, Get the movie info from TMDB
        System.out.println("Cache Miss. Calling TMDB API...");
        final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;

        try {
            MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);

            // Save the result to MongoDB for next time and create movie object to display
            if (movieSummary != null) {
                movieRepo.save(movieSummary);
                return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
            }
        } catch (Exception e) {
            // Handle API errors (e.g., movie not found)
            System.out.println(e);
            return new Movie(movieId, "Movie not found", "");
        }

        return new Movie(movieId, "Summary unavailable", "");
    }

}
