package com.spring.moviebooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="theatres")
public class Theatres {
	

	@Id
	 @Column(name="theatre_id")
	private  int theatreId;
	 @Column(name="theatre_name")
	 private String theatreName;
	 @Column(name="location")
	 private String location;
	 @Column(name="seating_capacity")
	 private int seatingCapacity;
	 @Column(name="ticket_price")
	 private double ticketPrice;
	 

}
