package com.booster.CineMagic;

import com.booster.CineMagic.Entity.Movie;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.MovieCriteria;
import com.booster.CineMagic.Enum.MovieFormat;
import com.booster.CineMagic.Repository.IMovieRepository;
import com.booster.CineMagic.Service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MovieServiceTest {
    public MovieServiceTest() {

    }

    @Mock
    private IMovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        List<Movie> moviesTestList = new ArrayList<>();
        Movie movieTest = new Movie(1, "Paddington", "95 minutes", "UK", "Adventure",
                "PG", 4, "And adventure with a Bear", "English", 2014,
                "Paul King", MovieFormat.SUBTITLE, Account.REGULAR, 42, true);
        moviesTestList.add(movieTest);

        when(movieRepository.findAll()).thenReturn(moviesTestList);
        when(movieRepository.findById(1)).thenReturn(Optional.of(movieTest));
        when(movieRepository.existsById(1)).thenReturn(true);
    }

    @Test
    void testGetMovies(){
        List<Movie> movieList = movieService.getMovies();

        assertTrue(movieList != null && !movieList.isEmpty());
    }

    @Test
    void testGetMoviesByYear(){
        List<Movie> movieList = movieService.getMoviesByYear(2014);

        assertTrue(movieList != null && !movieList.isEmpty());
    }

    @Test
    void testGetMoviesByCriteria(){
        List<Movie> movieByCriteria = movieService.getMoviesByCriteria(MovieCriteria.CLASSIFICATION, "PG");

        assertTrue(movieByCriteria != null && !movieByCriteria.isEmpty());
    }

    @Test
    void testGetMoviesByFormat(){
        List<Movie> movieListFormat = movieService.getMoviesByFormat(MovieFormat.SUBTITLE);

        assertTrue(movieListFormat != null && !movieListFormat.isEmpty());
    }

    @Test
    void testGetMoviesByType(){
        List<Movie> movieListAccount = movieService.getMoviesByType(Account.REGULAR);

        assertTrue(movieListAccount != null && !movieListAccount.isEmpty());
    }

    @Test
    void testGetMoviesWithProjection(){
        List<Movie> movieProjections = movieService.getMoviesWithProjection();

        assertTrue(movieProjections != null && !movieProjections.isEmpty());
    }

    @Test
    void testGetMovieByID(){
        Movie movieById = movieService.getMovieByID(1);

        assertNotNull(movieById);
    }

    @Test
    void testGetMovieByTitle(){
        Movie movieByTitle = movieService.getMovieByTitle("Paddington");

        assertNotNull(movieByTitle);
    }

    @Test
    void testHasProjections(){
        boolean projected = movieService.hasProjections(1);

        assertTrue(projected);
    }

    @Test
    void testAddNewMovie(){
        Movie newMovie = new Movie(1, "Paddington 2", "97 minutes", "UK", "Adventure",
                "PG", 4, "And adventure with a Bear 2", "English", 2014,
                "Paul King", MovieFormat.SUBTITLE, Account.REGULAR, 78, true);

        when(movieRepository.save(newMovie)).thenReturn(newMovie);
        Movie actualMovie = movieService.addNewMovie(newMovie);

        assertEquals(newMovie, actualMovie);
    }

    @Test
    void testModifyMovieByID(){
        Movie modifyMovie = new Movie(1, "Paddington 2", "97 minutes", "UK", "Adventure",
                "PG", 4, "And adventure with a Bear 2", "English", 2014,
                "Paul King", MovieFormat.SUBTITLE, Account.REGULAR, 78, true);

        when(movieRepository.save(any(Movie.class))).thenReturn(modifyMovie);
        Movie actualMovie = movieService.modifyMovieByID(1, modifyMovie);

        assertEquals(modifyMovie, actualMovie);
    }

    @Test
    void testDeleteMovie(){
        boolean actualBool = movieService.deleteMovie(1);

        assertTrue(actualBool);
    }

}
