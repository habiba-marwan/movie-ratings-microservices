error id: file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/resources/TrendingRecources.java:com/example/trending/TrendingServiceGrpc#
file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/resources/TrendingRecources.java
empty definition using pc, found symbol in pc: com/example/trending/TrendingServiceGrpc#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 558
uri: file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/resources/TrendingRecources.java
text:
```scala
package com.trendingmoviesservice.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.trendingmoviesservice.models.Rating;
import com.trendingmoviesservice.repository.TrendingRepository;

import io.grpc.stub.StreamObserver;

import com.example.trending.MovieInfo;
import com.example.trending.TrendingRequest;
import com.example.trending.TrendingResponse;
import com.example.trending.TrendingServiceGr@@pc;
import com.trendingmoviesservice.models.Movie;

@Service
public class TrendingRecources extends TrendingServiceGrpc.TrendingServiceImplBase {

    @Autowired

    private TrendingRepository trendingRepo;

    @Override
    public void getTrendingMovies(TrendingRequest request, StreamObserver<TrendingResponse> responseObserver) {
        List<Rating> trendingRatings = trendingRepo.findTrendingMovies();
        // We use RestTemplate to call the other service
        RestTemplate restTemplate = new RestTemplate();

        // Start building the gRPC Response
        // it uses builder design pattrn so we create new instance like this
        TrendingResponse.Builder responseBuilder = TrendingResponse.newBuilder();
        // responseBuilder --> build the actual object in the response
        // responseObserver is used to send the obj over the network
        for (Rating rating : trendingRatings) {

            String currentMovieId = rating.getMovieId();

            // the URL for your Movie Info Service
            String url = "http://localhost:8082/movies/" + currentMovieId;

            try {

                Movie movieDetails = restTemplate.getForObject(url, Movie.class);

                if (movieDetails != null) {
                    // Convert Java object into a gRPC message
                    // .set are generated automatically for every field defined in schema
                    // .build converts this to the immutable obj that will be sent
                    MovieInfo info = MovieInfo.newBuilder()
                            .setMovieId(movieDetails.getMovieId())
                            .setName(movieDetails.getName())
                            .setDescription(movieDetails.getDescription())
                            .setRating(rating.getRating())
                            .build();

                    // Add this movie to the list in the response
                    // the add method was generated because we declared the response as repeated
                    // (list)
                    responseBuilder.addMovies(info);
                }
            } catch (Exception e) {
                System.out.println("Error calling Movie Info Service: " + e.getMessage());
            }

            // Send the response back to the client (Catalog Service)
            responseObserver.onNext(responseBuilder.build());

            // Tell gRPC the call is finished
            responseObserver.onCompleted();

        }
    }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: com/example/trending/TrendingServiceGrpc#