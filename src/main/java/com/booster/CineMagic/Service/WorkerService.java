package com.booster.CineMagic.Service;

import com.booster.CineMagic.Repository.IWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    @Autowired
    IWorkerRepository workerRepository;


}
