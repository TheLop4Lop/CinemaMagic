package com.booster.CineMagic.Service;

import com.booster.CineMagic.Entity.Movie;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.MovieCriteria;
import com.booster.CineMagic.Enum.MovieFormat;

import java.util.List;

public interface IMovieService {
    List<Movie> getMovies();
    List<Movie> getMoviesByYear(int year);
    List<Movie> getMoviesByCriteria(MovieCriteria criteria, String value);
    List<Movie> getMoviesByFormat(MovieFormat format);
    List<Movie> getMoviesByType(Account type);
    List<Movie> getMoviesWithProjection();
    Movie getMovieByID(Integer id);
    Movie getMovieByTitle(String title);
    boolean hasProjections(Integer id);
    Movie addNewMovie(Movie newMovie);
    Movie modifyMovieByID(Integer id, Movie modifyMovie);
    boolean deleteMovie(Integer id);

}
