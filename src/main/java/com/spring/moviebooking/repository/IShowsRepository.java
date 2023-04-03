package com.spring.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.moviebooking.entity.Shows;

public interface IShowsRepository extends JpaRepository<Shows, Integer> {
	

}
