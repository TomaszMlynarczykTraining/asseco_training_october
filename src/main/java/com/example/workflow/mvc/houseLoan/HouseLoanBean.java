package com.example.workflow.mvc.houseLoan;

import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HouseLoanBean{

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ClientService clientService;

    @GetMapping("/start")
    public void startProcess(){
        runtimeService.startProcessInstanceByKey("Process_houseLoan", new HashMap<>(){{
            put("debt", 400); }});

    }

}
