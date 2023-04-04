package com.spring.moviebooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.moviebooking.entity.Shows;

public interface IShowsRepository extends JpaRepository<Shows, Integer> {
	
	@Query("SELECT s FROM Shows s WHERE LOWER(s.movie.movieTitle) like %:movieTitle%")
	public List<Shows> searchShowsByTitle(@Param("movieTitle")String movieTitle);

	

}
