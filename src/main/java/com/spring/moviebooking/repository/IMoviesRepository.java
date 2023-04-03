package com.spring.moviebooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.moviebooking.entity.Movies;

public interface IMoviesRepository extends JpaRepository<Movies, Integer> {
	
	
	@Query("SELECT m FROM Movies m WHERE LOWER(m.genre) like %:key% OR LOWER(m.movieTitle) like %:key%")
	public List<Movies> searchMoviesByKeyword(@Param("key")String keyword);
	
	
	 @Query("SELECT m.movieId, m.movieTitle, m.genre, m.director, m.duration, m.rating, " +
	           "t.theatreId, t.theatreName, t.location, t.seatingCapacity, " +
	           "s.showId, s.showDate, s.showTime, s.availableSeats " +
	           "FROM Shows s " +
	           "JOIN s.movie m " +
	           "JOIN s.theatre t " +
	           "WHERE LOWER(m.movieTitle) like %:movieTitle%")
	   public List<Object[]> findMovieDetailsByTitle(@Param("movieTitle")String movieTitle);
}
	
	 
