package com.example.workflow.mvc.mappers;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.impl.javax.el.VariableMapper;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

import static com.example.workflow.mvc.processes.EmailSenderProcess.EMAIL_ADDRESS_VARIABLE;

@Component
public class EmailSenderVariablesMapper implements DelegateVariableMapping {

    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {
        String emailAddressVariable = (String)delegateExecution.getVariable(EMAIL_ADDRESS_VARIABLE);
        variableMap.put(EMAIL_ADDRESS_VARIABLE, emailAddressVariable);

    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {
        String emailVariableFromSubprocess = (String)variableScope.getVariable(EMAIL_ADDRESS_VARIABLE);
        delegateExecution.setVariable(EMAIL_ADDRESS_VARIABLE, emailVariableFromSubprocess);

    }
}
