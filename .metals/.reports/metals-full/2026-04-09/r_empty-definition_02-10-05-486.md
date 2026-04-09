error id: file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/resources/TrendingRecources.java:TrendingServiceGrpc/TrendingServiceImplBase#
file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/resources/TrendingRecources.java
empty definition using pc, found symbol in pc: TrendingServiceGrpc/TrendingServiceImplBase#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 714
uri: file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/resources/TrendingRecources.java
text:
```scala
package com.trendingmoviesservice.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.trendingmoviesservice.models.Rating;
import com.trendingmoviesservice.repository.TrendingRepository;

import io.grpc.stub.StreamObserver;

import com.example.trending.MovieInfo;
import com.example.trending.TrendingRequest;
import com.example.trending.TrendingResponse;
import com.example.trending.TrendingServiceGrpc;
import com.trendingmoviesservice.models.Movie;


// TrendingServiceGrpc --> general class for grpc protocol

public class TrendingRecources extends TrendingServiceGrpc.Tre@@ndingServiceImplBase {

    private TrendingRepository trendingRepo;

    @Override
    public void getTrendingMovies(TrendingRequest request, StreamObserver<TrendingResponse> responseObserver) {
        List<Rating> trendingRatings = trendingRepo.findTrendingMovies();
        // We use RestTemplate to call the other service
        RestTemplate restTemplate = new RestTemplate();

        // Start building the gRPC Response
        TrendingResponse.Builder responseBuilder = TrendingResponse.newBuilder();

        for (Rating rating : trendingRatings) {

            String currentMovieId = rating.getMovieId();

            // the URL for your Movie Info Service
            String url = "http://localhost:8082/movies/" + currentMovieId;

            try {
                // 4. Send the GET request to the other service
                // This triggers the "Check Mongo -> if miss -> TMDB API" logic in that service
                Movie movieDetails = restTemplate.getForObject(url, Movie.class);

                if (movieDetails != null) {
                    // 5. Convert your Java object into a gRPC message
                    MovieInfo info = MovieInfo.newBuilder()
                            .setMovieId(movieDetails.getMovieId())
                            .setName(movieDetails.getName())
                            .setDescription(movieDetails.getDescription())
                            .build();

                    // Add this movie to the list in the response
                    responseBuilder.addMovies(info);
                }
            } catch (Exception e) {
                System.out.println("Error calling Movie Info Service: " + e.getMessage());
            }

            // 6. Send the response back to the client (Catalog Service)
            responseObserver.onNext(responseBuilder.build());

            // 7. Tell gRPC the call is finished
            responseObserver.onCompleted();

        }
    }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: TrendingServiceGrpc/TrendingServiceImplBase#