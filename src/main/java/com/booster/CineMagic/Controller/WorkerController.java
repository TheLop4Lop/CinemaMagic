package com.booster.CineMagic.Controller;

import com.booster.CineMagic.Entity.Worker;
import com.booster.CineMagic.Enum.ExistingData;
import com.booster.CineMagic.Enum.WorkerType;
import com.booster.CineMagic.Exception.EmptyDataListException;
import com.booster.CineMagic.Exception.EmptyListException;
import com.booster.CineMagic.Exception.ExistingDataException;
import com.booster.CineMagic.Exception.NotFoundExceptionCinema;
import com.booster.CineMagic.Service.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/worker/v0")
public class WorkerController {
    @Autowired
    IWorkerService workerService;

    @GetMapping("/workers")
    public ResponseEntity<?> getWorkers() {
        List<Worker> workerCinema = workerService.getWorkers();

        if(workerCinema.isEmpty())
        {
            throw new EmptyListException("Not Found Exception", "Error 404, User List is empty",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(workerCinema);
    }

    @GetMapping("/workers/account/{type}")
    public ResponseEntity<?> getWorkersByAccount(@PathVariable WorkerType type) {
        List<Worker> workersByAccount = workerService.getWorkersByAccountType(type);

        if(workersByAccount == null){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, Type not found",
                    HttpStatus.NOT_FOUND);
        }else if(workersByAccount.isEmpty()){
            throw new EmptyListException("Not Found Exception", "Error 404, User List is empty",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(workersByAccount);
    }

    @GetMapping("/workers/year/{year}")
    public ResponseEntity<?> getWorkersByYear(@PathVariable int year){
        List<Worker> workersByYear = workerService.getWorkersByYear(year);

        if(workersByYear.isEmpty())
        {
            throw new EmptyListException("Not Found Exception", "Error 404, User List is empty",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(workersByYear);
    }

    @GetMapping("/worker/{id}")
    public ResponseEntity<?> getWorkerById(@PathVariable Integer id) {
        Worker workerById = workerService.getWorkerById(id);

        if(workerById == null){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(workerById);
    }

    @PostMapping("/add/worker")
    public ResponseEntity<?> addNewWorker(@Valid @RequestBody Worker newWorker, BindingResult result){
        ExistingData existingWorker = workerService.checkExistingWorker(newWorker);

        if(result.hasErrors()){
            throw new EmptyDataListException("Empty Data List Exception", "Error 400", HttpStatus.BAD_REQUEST, result);
        }else if(existingWorker != ExistingData.NULL){
            throw new ExistingDataException("Existing Data Exception: " + existingWorker.toString(),
                    "Error 405", HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(workerService.addNewWorker(newWorker));
    }

    @PutMapping("/modify/worker/{id}")
    public ResponseEntity<?> modifyWorkerById(@PathVariable Integer id, @Valid @RequestBody Worker modifyWorker, BindingResult result){
        if(result.hasErrors()){
            throw new EmptyDataListException("Empty Data List Exception", "Error 404", HttpStatus.BAD_REQUEST, result);
        } else if(workerService.getWorkerById(id) == null){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        ExistingData existingWorker = workerService.checkExistingWorker(modifyWorker);
        if(existingWorker != ExistingData.NULL){
            throw new ExistingDataException("Existing Data Exception: " + existingWorker.toString(),
                    "Error 409", HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(workerService.modifyWorkerById(id, modifyWorker));
    }

    @DeleteMapping("/delete/worker/{id}")
    public ResponseEntity<Boolean> deleteWorkerById(@PathVariable Integer id) {
        boolean deletionResult = workerService.deleteWorkerById(id);

        if (!deletionResult) {
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(true);
    }
}
