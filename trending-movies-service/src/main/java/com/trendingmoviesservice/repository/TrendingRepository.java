package com.trendingmoviesservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.trendingmoviesservice.models.Rating;

public interface TrendingRepository extends JpaRepository<Rating, Integer> {
    @Query(value = "SELECT * FROM ratings ORDER BY rating DESC LIMIT 10", nativeQuery = true)
    List<Rating> findTrendingMovies();
}
