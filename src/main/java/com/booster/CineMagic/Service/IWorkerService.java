package com.booster.CineMagic.Service;

import com.booster.CineMagic.Entity.Worker;
import com.booster.CineMagic.Enum.ExistingData;
import com.booster.CineMagic.Enum.WorkerType;

import java.time.Year;
import java.util.Date;
import java.util.List;

public interface IWorkerService {
    List<Worker> getWorkers();
    List<Worker> getWorkersByAccountType(WorkerType type);
    List<Worker> getWorkersByYear(int year);
    Worker getWorkerById(Integer id);
    ExistingData checkExistingWorker(Worker compareWorker);
    Worker addNewWorker(Worker newWorker);
    Worker modifyWorkerById(Integer id, Worker modifyWorker);
    boolean deleteWorkerById(Integer id);

}
