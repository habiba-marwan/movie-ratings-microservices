package com.example.movieinfoservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.movieinfoservice.models.MovieSummary;

@Repository
public interface MovieRepo extends MongoRepository<MovieSummary, String> {

}
