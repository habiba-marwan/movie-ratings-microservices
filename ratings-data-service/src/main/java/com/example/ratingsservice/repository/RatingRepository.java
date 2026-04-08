// this connects to the database to handle queries

package com.example.ratingsservice.repository;

import com.example.ratingsservice.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Spring automatically gives findById() , delete(), ...
//JpaRepository<Rating, Integer>: connects the Rating class to the database
//Rating is the table this repository works with, Integer is the type of the primary key (id) in the Rating table

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByUserId(String userId); //Spring auto generates SQL query: SELECT * FROM ratings WHERE user_id = ?
}