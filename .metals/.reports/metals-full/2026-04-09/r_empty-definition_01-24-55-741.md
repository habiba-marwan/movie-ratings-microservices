error id: file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/resources/TrendingRecources.java:_empty_/TrendingRepo#
file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/resources/TrendingRecources.java
empty definition using pc, found symbol in pc: _empty_/TrendingRepo#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 352
uri: file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/resources/TrendingRecources.java
text:
```scala
package com.trendingmoviesservice.resources;

import java.util.ArrayList;
import java.util.List;

import com.trendingmoviesservice.models.Rating;
import com.trendingmoviesservice.repository.TrendingRepo;
import com.trendingmoviesservice.models.Movie;
import com.trendingmoviesservice.models.Rating;

public class TrendingRecources {

    private Trendi@@ngRepo trendingRepo;

    // public List<Movie> getTrendingMovies() {
    // List<Rating> trendingRatings = trendingRepo.findTrendingMovies();
    // List<Movie> trendingMovies = new ArrayList<>();

    // // this needs to be changed either to rest or grpc
    // for (int i = 0; i < trendingRatings.size(); i++) {
    // Rating currentRating = trendingRatings.get(i);
    // String currentMovieId = currentRating.getMovieId();
    // Movie movie = MovieResource.getMovieInfo(currentMovieId);
    // trendingList.add(movie);
    // return trendingList;

    // }
    // }

    public List<Movie> getTrendingMovies() {
        // 1. Fetch top 10 IDs from MySQL (Point A)
        List<Rating> topRatings = ratingRepository.findTop10ByOrderByRatingDesc();

        List<Movie> trendingList = new ArrayList<>();

        // We use RestTemplate to call the other service
        RestTemplate restTemplate = new RestTemplate();

        // 2. Loop through the IDs
        for (int i = 0; i < topRatings.size(); i++) {
            String movieId = topRatings.get(i).getMovieId();

            // 3. Define the URL for your Movie Info Service endpoint (Point B)
            String url = "http://localhost:8082/movies/" + movieId;

            try {
                // 4. Send the GET request to the other service
                // This triggers the "Check Mongo -> if miss -> TMDB API" logic in that service
                Movie movieDetails = restTemplate.getForObject(url, Movie.class);

                if (movieDetails != null) {
                    trendingList.add(movieDetails);
                }
            } catch (Exception e) {
                System.out.println("Error calling Movie Info Service: " + e.getMessage());
            }
        }

        return trendingList;
    }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/TrendingRepo#