package com.booster.CineMagic.Service;

import com.booster.CineMagic.Entity.Worker;
import com.booster.CineMagic.Enum.ExistingData;
import com.booster.CineMagic.Enum.WorkerType;
import com.booster.CineMagic.Exception.EmptyListException;
import com.booster.CineMagic.Exception.NotFoundExceptionCinema;
import com.booster.CineMagic.Repository.IWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WorkerService implements IWorkerService{
    @Autowired
    IWorkerRepository workerRepository;

    @Override
    public List<Worker> getWorkers() {
        if(workerRepository.findAll().isEmpty())
        {
            throw new EmptyListException("Not Found Exception", "Error 404, Empty List",
                    HttpStatus.NOT_FOUND);
        }

        return workerRepository.findAll();
    }

    @Override
    public List<Worker> getWorkersByAccountType(WorkerType type) {
        List<Worker> allWorkers = workerRepository.findAll();
        List<Worker> WorkersType = new ArrayList<Worker>();

        if(type != WorkerType.REGULAR && type != WorkerType.ADMIN){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404",
                    HttpStatus.NOT_FOUND);
        }

        for(Worker singleWorker: allWorkers){
            if(singleWorker.getType() == type){
                WorkersType.add(singleWorker);
            }
        }

        if(WorkersType.isEmpty()){
            throw new EmptyListException("Not Found Exception", "Error 404, Empty List",
                    HttpStatus.NOT_FOUND);
        }

        return WorkersType;
    }

    @Override
    public List<Worker> getWorkersByYear(int year) {
        List<Worker> allWorkers = workerRepository.findAll();
        List<Worker> workersByYear = new ArrayList<>();

        for(Worker singleWorker : allWorkers){
            if(singleWorker.getRegisterDate() == year){
                workersByYear.add(singleWorker);
            }
        }

        if(workersByYear.isEmpty()){
            throw new EmptyListException("Not Found Exception", "Error 404, Empty List",
                    HttpStatus.NOT_FOUND);
        }

        return workersByYear;
    }

    @Override
    public Worker getWorkerById(Integer id) {
        if(!workerRepository.existsById(id)){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return workerRepository.findById(id).orElse(null);
    }

    @Override
    public ExistingData checkExistingWorker(Worker compareWorker) {
        List<Worker> allWorkers = workerRepository.findAll();

        for (Worker singleWorker : allWorkers){
            if(Objects.equals(singleWorker.getUsername(), compareWorker.getUsername())){
                return ExistingData.USERNAME;
            }else if (Objects.equals(singleWorker.geteMail(), compareWorker.geteMail())){
                return ExistingData.EMAIL;
            }
        }

        return ExistingData.NULL;
    }

    @Override
    public Worker addNewWorker(Worker newWorker) {
        return workerRepository.save(newWorker);
    }

    @Override
    public Worker modifyWorkerById(Integer id, Worker modifyWorker) {
        if(workerRepository.existsById(id)){
            getWorkerById(id).setName(modifyWorker.getName());
            getWorkerById(id).setRegisterDate(modifyWorker.getRegisterDate());
            getWorkerById(id).seteMail(modifyWorker.geteMail());
            getWorkerById(id).setUsername(modifyWorker.getUsername());
            getWorkerById(id).setPassword(modifyWorker.getPassword());
            getWorkerById(id).setType(modifyWorker.getType());
        }

        return workerRepository.save(getWorkerById(id));
    }

    @Override
    public boolean deleteWorkerById(Integer id) {
        if(!workerRepository.existsById(id)){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        workerRepository.delete(getWorkerById(id));
        return true;
    }
    
}
