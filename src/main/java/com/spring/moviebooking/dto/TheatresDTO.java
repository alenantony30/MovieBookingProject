/* @Author : Alen Antony
 * 02-04-2023
 */


package com.spring.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheatresDTO {

	private int theatreId;
	private String theatreName;
	private String location;
	private int seatingCapacity;

}
