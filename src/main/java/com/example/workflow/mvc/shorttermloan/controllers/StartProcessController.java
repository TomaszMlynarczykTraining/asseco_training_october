package com.example.workflow.mvc.shorttermloan.controllers;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.entity.User;
import com.example.workflow.mvc.repository.UserRepository;
import com.example.workflow.mvc.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/process/")
@RequiredArgsConstructor
public class StartProcessController {
    @Autowired
    RuntimeService runtimeService;

    @GetMapping("/start/{key}")
    public String startProcessByKey(@PathVariable String key) {
       ProcessInstance pi =  runtimeService.startProcessInstanceByKey(key);
       return pi.getId();
    }
}
