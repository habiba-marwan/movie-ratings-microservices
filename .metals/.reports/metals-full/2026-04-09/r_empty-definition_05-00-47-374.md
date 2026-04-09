error id: file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/repository/TrendingRepository.java:_empty_/Query#
file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/repository/TrendingRepository.java
empty definition using pc, found symbol in pc: _empty_/Query#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 323
uri: file://<WORKSPACE>/trending-movies-service/src/main/java/com/trendingmoviesservice/repository/TrendingRepository.java
text:
```scala
package com.trendingmoviesservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.trendingmoviesservice.models.Rating;

public interface TrendingRepository extends JpaRepository<Rating, Integer> {
    @Query@@(value = "SELECT * FROM ratings ORDER BY rating DESC LIMIT 10", nativeQuery = true)
    List<Rating> findTrendingMovies();
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Query#