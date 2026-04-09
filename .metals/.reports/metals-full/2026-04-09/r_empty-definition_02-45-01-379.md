error id: file://<WORKSPACE>/movie-catalog-service/src/main/java/com/moviecatalogservice/services/GrpcClientConfig.java:_empty_/ManagedChannel#
file://<WORKSPACE>/movie-catalog-service/src/main/java/com/moviecatalogservice/services/GrpcClientConfig.java
empty definition using pc, found symbol in pc: _empty_/ManagedChannel#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 429
uri: file://<WORKSPACE>/movie-catalog-service/src/main/java/com/moviecatalogservice/services/GrpcClientConfig.java
text:
```scala
package com.moviecatalogservice.services; 

import com.example.trending.TrendingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Bean
    public TrendingServiceGrpc.TrendingServiceBlockingStub trendingStub() {
        ManagedChan@@nel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        return TrendingServiceGrpc.newBlockingStub(channel);
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/ManagedChannel#