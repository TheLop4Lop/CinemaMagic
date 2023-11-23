package com.booster.CineMagic;

import com.booster.CineMagic.Repository.IWorkerRepository;
import com.booster.CineMagic.Service.WorkerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class WorkerServiceTest {

    @Mock
    IWorkerRepository workerRepository;
    @InjectMocks
    WorkerService workerService;



}
