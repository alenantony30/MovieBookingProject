/* @Author : Alen Antony
 * 02-04-2023
 */


package com.spring.moviebooking.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="bookings")
public class Bookings {
		
	@Id
	@Column(name="booking_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	
	@Column(name="customer_id")
	private String customerId;
	
	@ManyToOne
	@JoinColumn(name = "show_id")
	private Shows show;
	
	@Column(name="booking_date")
	private Date bookingDate;
	
	@Min(100)@Max(1000)
	@Column(name="total_amount")
	private Double totalAmount;
	
	@Column(name="payment_method")
	private String paymentMethod;
	
	@Column(name="seat_no")
	private int seatNo;
}
