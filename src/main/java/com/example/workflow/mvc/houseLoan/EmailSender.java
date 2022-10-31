package com.example.workflow.mvc.houseLoan;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailSender implements JavaDelegate{

    @Autowired
    RuntimeService runtimeService;

    public void execute(DelegateExecution delegateExecution) throws Exception {
        runtimeService.createMessageCorrelation("email").processInstanceBusinessKey("123");
    }
}
