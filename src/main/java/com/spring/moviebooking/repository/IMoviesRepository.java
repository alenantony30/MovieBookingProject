/* @Author : Alen Antony
 * 02-04-2023
 */

package com.spring.moviebooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.moviebooking.entity.Movies;

public interface IMoviesRepository extends JpaRepository<Movies, Integer> {

	@Query("SELECT m FROM Movies m WHERE LOWER(m.genre) like %:key% OR LOWER(m.movieTitle) like %:key%")
	public List<Movies> searchMoviesByKeyword(@Param("key") String keyword);

}
