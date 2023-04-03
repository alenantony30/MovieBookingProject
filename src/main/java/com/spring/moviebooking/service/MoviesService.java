package com.spring.moviebooking.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.moviebooking.dto.MoviesDTO;
import com.spring.moviebooking.dto.MoviesShowsTheatres;
import com.spring.moviebooking.dto.ShowsDTO;
import com.spring.moviebooking.dto.TheatresDTO;
import com.spring.moviebooking.entity.Movies;
import com.spring.moviebooking.repository.IMoviesRepository;

@Service
public class MoviesService implements IMoviesServices {

	@Autowired
	IMoviesRepository repo;

	@Override
	public List<Movies> getMovies() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<Movies> searchMoviesByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchMoviesByKeyword(keyword.toLowerCase());
	}

	@Override
	public List<MoviesShowsTheatres> findMovieDetailsByTitle(String movieTitle) {
		// TODO Auto-generated method stub
		List<Object[]> movieList = repo.findMovieDetailsByTitle(movieTitle.toLowerCase());

		if (!movieList.isEmpty()) {
			List<MoviesShowsTheatres> list = new ArrayList();
			for (int i = 0; i < movieList.size(); i++) {
				MoviesShowsTheatres ob = new MoviesShowsTheatres();
				MoviesDTO movie = new MoviesDTO();
				ShowsDTO show = new ShowsDTO();
				TheatresDTO theatre = new TheatresDTO();

				movie.setMovieId((int) movieList.get(i)[0]);
				movie.setMovieTitle((movieList.get(i)[1]).toString());
				movie.setGenre((movieList.get(i)[2]).toString());
				movie.setDirector((movieList.get(i)[3]).toString());
				movie.setDuration((int) (movieList.get(i)[4]));
				movie.setRating((double) (movieList.get(i)[5]));

				theatre.setTheatreId((int) movieList.get(i)[6]);
				theatre.setTheatreName((movieList.get(i)[7]).toString());
				theatre.setLocation((movieList.get(i)[8]).toString());
				theatre.setSeatingCapacity((int) movieList.get(i)[9]);

				show.setShowId((int) movieList.get(i)[10]);
				show.setShowDate((Date) (movieList.get(i)[11]));
				show.setShowTime((Time) (movieList.get(i)[12]));
				show.setAvailableSeats((int) (movieList.get(i)[13]));

				ob.setMovie(movie);
				ob.setTheatre(theatre);
				ob.setShow(show);
				list.add(ob);

			}

			System.out.println(movieList.get(0)[0]);
			return list;
		}
		else
			return null;

	}

}
