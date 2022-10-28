package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BreakVerifyInvoice implements JavaDelegate{

    @Autowired
    RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String messageName = (String) delegateExecution.getVariable("messageName");

        runtimeService.correlateMessage(messageName);

    }
}
