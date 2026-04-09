error id: file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/TrendingMoviesServiceApplication.java:io/grpc/ServerBuilder#forPort#addService#
file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/TrendingMoviesServiceApplication.java
empty definition using pc, found symbol in pc: io/grpc/ServerBuilder#forPort#addService#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 641
uri: file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/TrendingMoviesServiceApplication.java
text:
```scala
package com.trendingmoviesservice;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.trendingmoviesservice.resources.TrendingRecources;

@SpringBootApplication
public class TrendingMoviesServiceApplication {

    @Bean
    public io.grpc.Server grpcServer(TrendingRecources trendingService) throws IOException {
        // This physically opens port 9090 on your Mac
        io.grpc.Server server = io.grpc.ServerBuilder
                .forPort(9090)
                .addServi@@ce(trendingService) // This is your implementation of TrendingServiceImplBase
                .build();

        System.out.println("Starting gRPC Server on port 9090...");
        server.start();

        // This ensures the server shuts down when the app stops
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server...");
            server.shutdown();
        }));

        return server;
    }

    public static void main(String[] args) {
        SpringApplication.run(TrendingMoviesServiceApplication.class, args);
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: io/grpc/ServerBuilder#forPort#addService#