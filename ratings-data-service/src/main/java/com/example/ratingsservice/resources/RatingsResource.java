package com.example.ratingsservice.resources;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.UserRating;
import com.example.ratingsservice.repository.RatingRepository;
    
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping("/{userId}")
    public UserRating getRatingsOfUser(@PathVariable String userId) {

        List<Rating> ratings = ratingRepository.findByUserId(userId);

        UserRating userRating = new UserRating();
        userRating.setRatings(ratings);

        return userRating;
    }
}