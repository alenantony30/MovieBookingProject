package com.spring.moviebooking.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "shows")
public class Shows {
	@Id
	@Column(name = "show_id")
	int showId;

	@ManyToOne
	@JoinColumn(name = "theatre_id")
	private Theatres theatre;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movies movie;

	@Column(name = "show_date")
	Date showDate;

	@Column(name = "show_time")
	Time showTime;

	@Column(name = "available_seats")
	int availableSeats;

}
