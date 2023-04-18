/* @Author : Alen Antony
 * 02-04-2023
 */

package com.spring.moviebooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.moviebooking.entity.Ratings;

@Repository
public interface IRatingsRepository extends JpaRepository<Ratings, Integer> {
	
	@Query("SELECT r FROM Ratings r WHERE LOWER(r.movie.movieTitle) like %:title%")
	public List<Ratings> findRatingsByMovieTitle(@Param("title") String movieName);

	@Query("SELECT AVG(r.rating) FROM Ratings r WHERE r.movie.movieTitle like %:title%")
	public Double getTotalRatingByMovie(@Param("title") String movieName);

	
}
