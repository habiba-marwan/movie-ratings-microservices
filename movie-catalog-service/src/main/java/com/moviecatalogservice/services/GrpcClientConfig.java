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
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        return TrendingServiceGrpc.newBlockingStub(channel);
    }
}
