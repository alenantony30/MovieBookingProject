package com.spring.moviebooking.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.spring.moviebooking.entity.Theatres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheatresDTO {

	int theatreId;
	String theatreName;
	String location;
	int seatingCapacity;

}
