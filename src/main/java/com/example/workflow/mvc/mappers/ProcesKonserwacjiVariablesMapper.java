package com.example.workflow.mvc.mappers;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

import static com.example.workflow.mvc.processes.ProcesKonserwacjiSenderProcess.*;

@Component
public class ProcesKonserwacjiVariablesMapper implements DelegateVariableMapping {

    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {
        Long idPowiadomienia = (Long)delegateExecution.getVariable(ID_POWIADOMIENIA);
        String trescPowiadomienia = (String)delegateExecution.getVariable(TRESC_POWIADOMIENIA);
        variableMap.put(ID_POWIADOMIENIA, idPowiadomienia);
        variableMap.put(TRESC_POWIADOMIENIA,trescPowiadomienia);

    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {
        Long idPowiadomienia = (Long)variableScope.getVariable(ID_POWIADOMIENIA);
        String trescPowiadomienia = (String)variableScope.getVariable(TRESC_POWIADOMIENIA);
        delegateExecution.setVariable(ID_POWIADOMIENIA, idPowiadomienia);
        delegateExecution.setVariable(TRESC_POWIADOMIENIA,trescPowiadomienia);

    }
}
