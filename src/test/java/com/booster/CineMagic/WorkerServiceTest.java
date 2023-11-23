package com.booster.CineMagic;

import com.booster.CineMagic.Entity.Worker;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.ExistingData;
import com.booster.CineMagic.Enum.WorkerType;
import com.booster.CineMagic.Repository.IWorkerRepository;
import com.booster.CineMagic.Service.WorkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WorkerServiceTest {

    @Mock
    IWorkerRepository workerRepository;
    @InjectMocks
    WorkerService workerService;

    private Worker workerTest;
    private List<Worker> workerListTest;

    @BeforeEach()
    void setUp(){
        MockitoAnnotations.initMocks(this);
        workerListTest = new ArrayList<>();
        workerTest = new Worker(1, "Pedro Martinez", 2021, "peter@gmail.com",
                            "PedroW12", "Password123", WorkerType.ADMIN);
        workerListTest.add(workerTest);

        when(workerRepository.findAll()).thenReturn(workerListTest);
        when(workerRepository.findById(1)).thenReturn(Optional.of(workerTest));
        when(workerRepository.existsById(1)).thenReturn(true);
    }

    @Test
    void testGetWorkers(){
        List<Worker> workerList = workerService.getWorkers();

        assertTrue(workerList != null && !workerList.isEmpty());
    }

    @Test
    void testGetWorkersByAccountType(){
        List<Worker> workerListAccount = workerService.getWorkersByAccountType(WorkerType.ADMIN);

        assertTrue(workerListAccount != null && !workerListAccount.isEmpty());
    }

    @Test
    void testGetWorkersByYear(){
        List<Worker> workerList = workerService.getWorkersByYear(2021);

        assertTrue(workerList != null && !workerList.isEmpty());
    }

    @Test
    void testGetWorkerById(){
        Worker WorkerById = workerService.getWorkerById(1);

        assertNotNull(WorkerById);
    }

    @Test
    void testCheckExistingWorker(){
        Worker compareWorker = new Worker(1, "Rodrigo Martinez", 2005, "rodrigo@gmail.com",
                "PedroW12", "Password123", WorkerType.ADMIN);
        ExistingData type = workerService.checkExistingWorker(compareWorker);

        assertNotSame(type, ExistingData.NULL);
    }

    @Test
    void testAddNewWorker(){
        Worker newWorker = new Worker(1, "Rodrigo Martinez", 2005, "rodrigo@gmail.com",
                "PedroW12", "Password123", WorkerType.REGULAR);

        when(workerRepository.save(newWorker)).thenReturn(newWorker);
        Worker actualWorker = workerService.addNewWorker(newWorker);

        assertEquals(newWorker, actualWorker);
    }

    @Test
    void testModifyWorkerById(){
        Worker modifyWorker = new Worker(1, "Rodrigo Martinez", 2005, "rodrigo@gmail.com",
                "PedroW12", "Password123", WorkerType.ADMIN);

        when(workerRepository.save(any(Worker.class))).thenReturn(modifyWorker);
        Worker actualWorker = workerService.modifyWorkerById(1, modifyWorker);

        assertEquals(modifyWorker, actualWorker);
    }

    @Test
    void testDeleteWorkerById(){
        boolean actualBool = workerService.deleteWorkerById(1);

        assertTrue(actualBool);
    } 

}
