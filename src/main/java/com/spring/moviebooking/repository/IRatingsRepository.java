package com.spring.moviebooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.moviebooking.entity.Ratings;

public interface IRatingsRepository extends JpaRepository<Ratings, Integer> {
	
	@Query("SELECT r FROM Ratings r WHERE LOWER(r.movie.movieTitle) like %:title%")
	public List<Ratings> findRatingsByMovieTitle(@Param("title") String movieName);

	@Query("SELECT AVG(r.rating) FROM Ratings r WHERE r.movie.movieTitle like %:title%")
	public Double getTotalRatingByMovie(@Param("title") String movieName);

	
}
