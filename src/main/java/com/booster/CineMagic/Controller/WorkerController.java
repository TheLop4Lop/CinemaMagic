package com.booster.CineMagic.Controller;

import com.booster.CineMagic.Service.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worker/v0")
public class WorkerController {
    @Autowired
    IWorkerService workerService;

    
}
