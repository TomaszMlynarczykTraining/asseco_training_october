package com.example.workflow.mvc.loanProcess;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

import static com.example.workflow.mvc.loanProcess.HouseLoanSenderProcess.*;


@Component
public class HouseLoanVariablesMapper implements DelegateVariableMapping {

    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {
        Long clientId = (Long)delegateExecution.getVariable(CLIENT_ID);
        String debt = (String)delegateExecution.getVariable(DEBT);
        variableMap.put(CLIENT_ID, clientId);
        variableMap.put(DEBT,debt);

    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {
        Long clientId = (Long)variableScope.getVariable(CLIENT_ID);
        String debt = (String)variableScope.getVariable(DEBT);
        delegateExecution.setVariable(CLIENT_ID, clientId);
        delegateExecution.setVariable(DEBT,debt);

    }
}
