package com.booster.CineMagic.Controller;

import com.booster.CineMagic.Entity.Movie;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.MovieCriteria;
import com.booster.CineMagic.Enum.MovieFormat;
import com.booster.CineMagic.Exception.EmptyDataListException;
import com.booster.CineMagic.Exception.EmptyListException;
import com.booster.CineMagic.Exception.ExistingDataException;
import com.booster.CineMagic.Exception.NotFoundExceptionCinema;
import com.booster.CineMagic.Service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movie/v0")
public class MovieController {
    @Autowired
    IMovieService movieService;

    @RequestMapping("/movies")
    public ResponseEntity<?> getMovies(){
        List<Movie> movies = movieService.getMovies();

        if(movies.isEmpty())
        {
            throw new EmptyListException("Not Found Exception", "Error 404, Movie List is empty",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(movies);
    }

    @RequestMapping("/movies/year/{year}")
    public ResponseEntity<?> getMoviesByYear(@PathVariable int year){
        List<Movie> moviesByYear = movieService.getMoviesByYear(year);

        if(moviesByYear.isEmpty())
        {
            throw new EmptyListException("Not Found Exception", "Error 404, Movie List is empty",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(moviesByYear);
    }

    @RequestMapping("/movies/{criteria}/{value}")
    public ResponseEntity<?> getMoviesByCriteria(@PathVariable MovieCriteria criteria, @PathVariable String value){
        List<Movie> moviesByCriteria = movieService.getMoviesByCriteria(criteria, value);

        if(moviesByCriteria.isEmpty()){
            throw new EmptyListException("Not Found Exception", "Error 404, No movie found with that value",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(moviesByCriteria);
    }

    @RequestMapping("/movies/{format}")
    public ResponseEntity<?> getMoviesByFormat(@PathVariable MovieFormat format){
        List<Movie> moviesByFormat = movieService.getMoviesByFormat(format);

        if(moviesByFormat.isEmpty()){
            throw new EmptyListException("Not Found Exception", "Error 404, No movie found with that format",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(moviesByFormat);
    }

    @RequestMapping("/movies/account/{type}")
    public ResponseEntity<?> getMoviesByType(@PathVariable Account type){
        List<Movie> moviesByType = movieService.getMoviesByType(type);

        if(moviesByType.isEmpty()){
            throw new EmptyListException("Not Found Exception", "Error 404, No movie found with that account type",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(moviesByType);
    }

    @RequestMapping("/movie/{id}")
    ResponseEntity<?> getMovieByID(@PathVariable Integer id){
        Movie movieById = movieService.getMovieByID(id);

        if(movieById == null){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(movieById);
    }

    @RequestMapping("/movie/title/{title}")
    ResponseEntity<?>getMovieByTitle(@PathVariable String title){
        Movie movieByTitle = movieService.getMovieByTitle(title);

        if(movieByTitle == null){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No movie with that title",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(movieByTitle);
    }

    @RequestMapping("/add/movie")
    ResponseEntity<?> addNewMovie(@Valid @RequestBody Movie newMovie, BindingResult result){
        if(result.hasErrors()){
            throw new EmptyDataListException("Empty Data List Exception", "Error 404", HttpStatus.BAD_REQUEST, result);
        }

        Movie movieControl = movieService.getMovieByTitle(newMovie.getTitle());
        if(movieControl != null){
            throw new ExistingDataException("Existing Data Exception: " + movieControl.getTitle() + " already added",
                    "Error 409", HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(movieService.addNewMovie(newMovie));
    }

    @RequestMapping("modify/movie/{id}")
    ResponseEntity<?> modifyMovieByID(@Valid @RequestBody Movie modifyMovie, @PathVariable Integer id, BindingResult result){
        if(result.hasErrors()){
            throw new EmptyDataListException("Empty Data List Exception", "Error 404", HttpStatus.BAD_REQUEST, result);
        }else if(movieService.getMovieByID(id) == null){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(movieService.modifyMovieByID(id, modifyMovie));
    }

    @RequestMapping("delete/movie/{id}")
    ResponseEntity<?> deleteMovie(@PathVariable Integer id){
        boolean deletionResult = movieService.deleteMovie(id);

        if (!deletionResult) {
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(true);
    }

}