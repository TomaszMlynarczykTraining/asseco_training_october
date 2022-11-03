package com.example.workflow.mvc.houseLoan;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

import static com.example.workflow.mvc.houseLoan.HouseLoanSenderProcess.*;


@Component
public class HouseLoanVariablesMapper implements DelegateVariableMapping {

    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {
        String clientId = delegateExecution.getVariable(CLIENT_ID).toString();
        String debt = (String)delegateExecution.getVariable(DEBT);
        variableMap.put(CLIENT_ID, clientId);
        variableMap.put(DEBT,debt);

    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {
        String clientId = variableScope.getVariable(CLIENT_ID).toString();
        String debt = (String)variableScope.getVariable(DEBT);
        delegateExecution.setVariable(CLIENT_ID, clientId);
        delegateExecution.setVariable(DEBT,debt);

    }
}
