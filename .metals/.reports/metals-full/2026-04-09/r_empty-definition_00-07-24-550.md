error id: file://<WORKSPACE>/ratings-data-service/src/main/java/com/example/ratingsservice/resources/RatingsResource.java:_empty_/Rating#
file://<WORKSPACE>/ratings-data-service/src/main/java/com/example/ratingsservice/resources/RatingsResource.java
empty definition using pc, found symbol in pc: _empty_/Rating#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 854
uri: file://<WORKSPACE>/ratings-data-service/src/main/java/com/example/ratingsservice/resources/RatingsResource.java
text:
```scala
package com.example.ratingsservice.resources;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.UserRating;
import com.example.ratingsservice.repository.RatingRepository;
    
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //A class that handles HTTP requests and returns JSON
@RequestMapping("/ratings") //Base URL

public class RatingsResource {

    @Autowired
    private RatingRepository ratingRepository; //Spring automatically creates an object of RatingRepository and injects it here (instead of new RatingRepository() ).

    @GetMapping("/{userId}") //HTTP method: GET
    public UserRating getRatingsOfUser(@PathVariable String userId) { //@PathVariable takes value from the URL

        List<Ra@@ting> ratings = ratingRepository.findByUserId(userId);

        UserRating userRating = new UserRating();
        userRating.setRatings(ratings);

        return userRating;
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Rating#