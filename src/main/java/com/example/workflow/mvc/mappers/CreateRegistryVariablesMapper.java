package com.example.workflow.mvc.mappers;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

import static com.example.workflow.mvc.processes.EmailSenderProcess.EMAIL_ADDRESS_VARIABLE;
import static com.example.workflow.mvc.processes.RegistryAdditionalHoursProcess.APPLICATION_ID;

@Component
public class CreateRegistryVariablesMapper implements DelegateVariableMapping {

    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {
        String applicationIdVariable = (String)delegateExecution.getVariable(APPLICATION_ID);
        variableMap.put(APPLICATION_ID, applicationIdVariable);

    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {
        String applicationIdVariableFromSubprocess = (String)variableScope.getVariable(APPLICATION_ID);
        delegateExecution.setVariable(APPLICATION_ID, applicationIdVariableFromSubprocess);

    }
}
