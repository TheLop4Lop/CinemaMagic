package com.booster.CineMagic.Service;

import com.booster.CineMagic.Entity.Movie;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.MovieCriteria;
import com.booster.CineMagic.Enum.MovieFormat;
import com.booster.CineMagic.Repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MovieService implements IMovieService{
    @Autowired
    IMovieRepository movieRepository;

    @Override
    public List<Movie> getMovies() {

        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByYear(int year) {
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> moviesByYear = new ArrayList<>();

        for(Movie singleMovie : allMovies){
            if(singleMovie.getYear() == year){
                moviesByYear.add(singleMovie);
            }
        }

        return moviesByYear;
    }

    @Override
    public List<Movie> getMoviesByCriteria(MovieCriteria criteria, String value) {
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> moviesByCriteria = new ArrayList<>();

        for(Movie singleMovie : allMovies){
            switch(criteria) {
                case CLASSIFICATION:
                    if (Objects.equals(singleMovie.getClassification(), value)) {
                        moviesByCriteria.add(singleMovie);
                    }
                    break;
                case CATEGORY:
                    if (Objects.equals(singleMovie.getCategory(), value)) {
                        moviesByCriteria.add(singleMovie);
                    }
                    break;
                case COUNTRY:
                    if (Objects.equals(singleMovie.getCountry(), value)) {
                        moviesByCriteria.add(singleMovie);
                    }
                    break;
                case LANGUAGE:
                    if (Objects.equals(singleMovie.getLanguage(), value)) {
                        moviesByCriteria.add(singleMovie);
                    }
                    break;
                case DIRECTOR:
                    if (Objects.equals(singleMovie.getDirector(), value)) {
                        moviesByCriteria.add(singleMovie);
                    }
                    break;
            }
        }

        return moviesByCriteria;
    }

    @Override
    public List<Movie> getMoviesByFormat(MovieFormat format) {
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> moviesByFormat = new ArrayList<>();

        for(Movie singleMovie : allMovies){
            if(singleMovie.getFormat() == format){
                moviesByFormat.add(singleMovie);
            }
        }

        return moviesByFormat;
    }

    @Override
    public List<Movie> getMoviesByType(Account type) {
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> moviesByType = new ArrayList<>();

        for(Movie singleMovie : allMovies){
            if(singleMovie.getType() == type){
                moviesByType.add(singleMovie);
            }
        }

        return moviesByType;
    }

    @Override
    public List<Movie> getMoviesWithProjection() {
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> moviesProjection = new ArrayList<>();

        for(Movie singleMovie : allMovies){
            if(singleMovie.isAvailable()){
                moviesProjection.add(singleMovie);
            }
        }

        return moviesProjection;
    }

    @Override
    public Movie getMovieByID(Integer id) {

        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movie getMovieByTitle(String title) {
        List<Movie> allMovies = movieRepository.findAll();

        for(Movie singleMovie : allMovies){
            if(Objects.equals(singleMovie.getTitle(), title)){
                return singleMovie;
            }
        }

        return null;
    }

    @Override
    public boolean hasProjections(Integer id) {
        List<Movie> movieProjections =  getMoviesWithProjection();

        for(Movie singleMovie : movieProjections){
            if(singleMovie.isAvailable() == getMovieByID(id).isAvailable()){
                return true;
            }
        }

        return false;
    }

    @Override
    public Movie addNewMovie(Movie newMovie) {

        return movieRepository.save(newMovie);
    }

    @Override
    public Movie modifyMovieByID(Integer id, Movie modifyMovie) {
        if(movieRepository.existsById(id)){
            getMovieByID(id).setTitle(modifyMovie.getTitle());
            getMovieByID(id).setDuration(modifyMovie.getDuration());
            getMovieByID(id).setCountry(modifyMovie.getCountry());
            getMovieByID(id).setCategory(modifyMovie.getCategory());
            getMovieByID(id).setClassification(modifyMovie.getClassification());
            getMovieByID(id).setRating(modifyMovie.getRating());
            getMovieByID(id).setSynopsis(modifyMovie.getSynopsis());
            getMovieByID(id).setLanguage(modifyMovie.getLanguage());
            getMovieByID(id).setYear(modifyMovie.getYear());
            getMovieByID(id).setDirector(modifyMovie.getDirector());
            getMovieByID(id).setFormat(modifyMovie.getFormat());
            getMovieByID(id).setType(modifyMovie.getType());
            getMovieByID(id).setWatched(modifyMovie.getWatched());

            return movieRepository.save(getMovieByID(id));
        }

        return null;
    }

    @Override
    public boolean deleteMovie(Integer id) {
        Movie movieToDelete = movieRepository.findById(id).orElse(null);
        if (movieToDelete != null) {
            movieRepository.delete(movieToDelete);
        }else{
            return false;
        }

        return true;
    }

}