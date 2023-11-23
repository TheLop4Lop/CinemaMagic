package com.booster.CineMagic.Service;

import com.booster.CineMagic.Entity.Movie;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.MovieFormat;

import java.util.List;

public class MovieService implements IMovieService{
    @Override
    public List<Movie> getMovies() {
        return null;
    }

    @Override
    public List<Movie> getMoviesByYear(int year) {
        return null;
    }

    @Override
    public List<Movie> getMoviesByCriteria(String criteria, String value) {
        return null;
    }

    @Override
    public List<Movie> getMoviesByFormat(MovieFormat format) {
        return null;
    }

    @Override
    public List<Movie> getMoviesByType(Account type) {
        return null;
    }

    @Override
    public Movie getMovieByID(Integer id) {
        return null;
    }

    @Override
    public Movie getMovieByTitle(String title) {
        return null;
    }

    @Override
    public Movie addNewMovie(Movie newMovie) {
        return null;
    }

    @Override
    public Movie modifyMovieByID(Integer id, Movie modifyMovie) {
        return null;
    }

    @Override
    public boolean deleteMovie(Integer id) {
        return false;
    }

}
