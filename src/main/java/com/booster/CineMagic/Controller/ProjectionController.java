package com.booster.CineMagic.Controller;

import com.booster.CineMagic.Entity.Projection;
import com.booster.CineMagic.Enum.ProjectionCriteria;
import com.booster.CineMagic.Exception.EmptyDataListException;
import com.booster.CineMagic.Exception.EmptyListException;
import com.booster.CineMagic.Exception.ExistingDataException;
import com.booster.CineMagic.Exception.NotFoundExceptionCinema;
import com.booster.CineMagic.Service.IProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projection/v0")
public class ProjectionController {
    @Autowired
    IProjectionService projectionService;

    @GetMapping("/projections")
    public ResponseEntity<?> getProjections(){
        List<Projection> projections = projectionService.getProjections();

        if(projections.isEmpty()){
            throw new EmptyListException("Not Found Exception", "Error 404, Projection List is empty",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(projections);
    }

    @GetMapping("/projections/{criteria}/{value}")
    public ResponseEntity<?> getProjectionsByCriteria(@PathVariable ProjectionCriteria criteria, @PathVariable String value){
        List<Projection> projectionsByCriteria = projectionService.getProjectionsByCriteria(criteria, value);

        if(projectionsByCriteria.isEmpty()){
            throw new EmptyListException("Not Found Exception", "Error 404, No projections found with that value",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(projectionsByCriteria);
    }

    @GetMapping("/projection/{id}")
    public ResponseEntity<?> getProjectionById(@PathVariable Integer id){
        Projection projectionById = projectionService.getProjectionById(id);

        if(projectionById == null){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(projectionById);
    }

    @PostMapping("/add/projection")
    public ResponseEntity<?> addNewProjection(@Valid @RequestBody Projection newProjection, BindingResult result){
        if(result.hasErrors()){
            throw new EmptyDataListException("Empty Data List Exception", "Error 404", HttpStatus.BAD_REQUEST, result);
        }

        boolean projectionControl = projectionService.checkUsageOfRoomHour(newProjection);
        if(projectionControl){
            throw new ExistingDataException("Existing Data Exception: " + newProjection.getRoom() + " already register at "
                    + newProjection.getHour() + " by movie: " + newProjection.getMovie().getTitle(), "Error 409", HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(projectionService.addNewProjection(newProjection));
    }

    @PutMapping("/modify/projection/{id}")
    public ResponseEntity<?> modifyProjectionById(@Valid @RequestBody Projection modifyProjection, @PathVariable Integer id, BindingResult result){
        if(result.hasErrors()){
            throw new EmptyDataListException("Empty Data List Exception", "Error 404", HttpStatus.BAD_REQUEST, result);
        }else if(projectionService.getProjectionById(id) == null){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(projectionService.modifyProjectionById(id, modifyProjection));
    }

    @DeleteMapping("/delete/projection/{id}")
    public ResponseEntity<?> deleteProjectionById(@PathVariable Integer id){
        boolean deletionResult = projectionService.deleteProjectionById(id);

        if(!deletionResult){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(true);
    }

}
