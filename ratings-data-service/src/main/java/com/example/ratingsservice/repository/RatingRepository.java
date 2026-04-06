package com.example.ratingsservice.repository;

import com.example.ratingsservice.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByUserId(String userId);
}