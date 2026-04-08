# spring-boot-microservices
In this project, multiple microservices communicate with each other to provide the backend services of a minimalistic/sample movie rating application similar to IMDB.

## Technologies

- Spring Boot
- Spring Cloud Eureka (Service Discovery)
- Spring Cloud Hystrix (Dashboard, Circuit Breaker pattern, Bulkhead pattern)

## Summary

*  __MovieInfoService__ provides the movie info by sending requests to __TheMovieDB API__. 
* __RatingsDataService__ provides the user's ratings for movies.
* __MovieCatalogService__ acts as an accumulator that gets data from __RatingsDataService__ and __MovieInfoService__ to present it.
* __DiscoveryServer__ is the Eureka server for service discovery.

![Screen Shot 2021-09-23 at 16 48 57](https://user-images.githubusercontent.com/22833948/134519062-0013cbf9-8a5f-4a43-ba14-635ccdbab04b.png)

## Running

You can run each project either using your IDE or *mvn spring-boot:run* starting from __DiscoveryServer__. Projects will run on the following endpoints:

* Discovery Server - http://localhost:8761
* Movie Catalog - http://localhost:8081/catalog/{userId}
* Movie Info - http://localhost:8082/movies/{movieId}
* Ratings Data - http://localhost:8083/ratings/{userId}
* Hystrix Dashboard - Go to http://localhost:8081/hystrix. Then enter *https://localhost:8081/actuator/hystrix.stream* to the inputbox.




#  Task A - Ratings Data Service(MySQL Integration)

## Overview

This service is part of a microservices system. It is responsible for:

* Storing user ratings in a database
* Providing ratings through REST API
* Registering itself with Eureka (service discovery)

In this task, the storage was changed from **in-memory data to MySQL database**.

---

# Database Setup

## Database Name

```sql
ratings_db
```

##  Create Database

```sql
CREATE DATABASE ratings_db;
USE ratings_db;
```

##  Table Schema

```sql
CREATE TABLE ratings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50),
    movie_id VARCHAR(50),
    rating INT
);
```


#  Project Structure
```
1. models
2. repository
3. resources
4. RatingsDataServiceApplication.java
```
---

#  Classes and Their Responsibilities

##  1. Rating.java (Entity)

* Represents a row in the database
* Maps Java object to MySQL table

Key features:

* `@Entity` → marks it as DB table
* `@Table(name = "ratings")` → links to table
* Fields mapped to columns (`user_id`, `movie_id`)
---

##  2. UserRating.java

* Wraps a list of ratings
* Used for API response

Example response:

```json
{
  "ratings": [...]
}
```

---

##  3. RatingRepository.java

* Interface for database operations

Key method:

```java
List<Rating> findByUserId(String userId);
```

This automatically generates SQL query:

```sql
SELECT * FROM ratings WHERE user_id = ?
```

---

##  4. RatingsResource.java (Controller)

* Handles HTTP requests

Endpoint:

```text
GET /ratings/{userId}
```

##  5. RatingsDataServiceApplication.java

* Main class (entry point)
* Starts Spring Boot application
* Registers service with Eureka

---

#  Application Workflow

1. Client sends request:

```text
GET /ratings/1
```
2. Controller receives request
3. Repository executes query:
```sql
SELECT * FROM ratings WHERE user_id = '1';
```
4. Data converted into `Rating` objects
5. Wrapped into `UserRating`
6. Returned as JSON response
---

#  How to Run

1. Run the discovery microservice
2. Run the ratings microservice
3. Test API
Open browser:

```text
http://localhost:8083/ratings/1
```

---

#  Expected Output

```json
{
  "ratings": [
    { "movieId": "100", "rating": 4 },
    { "movieId": "200", "rating": 3 },
    { "movieId": "300", "rating": 5 }
  ]
}
```

---

#  Performance & Stress Testing using JMeter

## Overview

Apache JMeter was used to evaluate system performance under different load conditions:

* **Performance Test:** Measures behavior under normal load  
* **Stress Test:** Pushes system to its limits  

---

##  Performance Test Configuration

Number of Threads (users): 100
Ramp-up period (seconds): 30
Loop Count: 10

# without MySQL:

![performance_test_1_without_mysql](imgs\performance test 1 without .png)

# witH MySQL:
![performance_test_1_with_mysql](imgs\performance test 1 with .png)


---

Number of Threads (users): 500
Ramp-up period (seconds): 20
Loop Count: 20

# without MySQL:
![performance_test_2_without_mysql](imgs\performance test 2 without.png)
# witH MySQL:
![performance_test_2_with_mysql](imgs\performance test 2 with.png)



### Observation

* System performs efficiently under moderate load  
* No failures observed  
* Minimal difference between MySQL and in-memory database under low load  

---

## Stress Test Configuration

Number of Threads (users): 8000
Ramp-up period (seconds): 5
Loop Count: 10

![stress_test](imgs\stress test.png)

## Observations

* Response time increased under heavy load  
* Throughput reached a peak then stabilized  
* System remained stable initially  
* MySQL showed slightly higher latency than in-memory  

---

  