package com.example.workflow.mvc.loanProcess;


import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckCustomerIDProcessDelegate implements JavaDelegate {


    @Autowired
    HistoryService historyService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String id = (String) delegateExecution.getVariable(LoanProcessVariable.ID);
        List processInstanceList = historyService.createHistoricProcessInstanceQuery().processDefinitionId(delegateExecution.getProcessDefinitionId()).variableValueEquals(LoanProcessVariable.ID, id).list();

        Boolean duplicateProcess = processInstanceList.size() > 1;

        delegateExecution.setVariable(LoanProcessVariable.DUPLICATE_CUSTOMER, duplicateProcess);

    }
}