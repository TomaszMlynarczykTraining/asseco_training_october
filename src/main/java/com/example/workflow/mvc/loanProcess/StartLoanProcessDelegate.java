package com.example.workflow.mvc.loanProcess;


import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StartLoanProcessDelegate implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(LoanProcessVariable.IS_CORRECT, true);
        parameters.put(LoanProcessVariable.LOAN_PROCESS_TYPE, "LONG");

        runtimeService.startProcessInstanceByKey("Loan_Process");

    }
}