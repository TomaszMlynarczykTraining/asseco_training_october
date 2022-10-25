package com.example.workflow.mvc.mappers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

@Component
public class RecruitmentProcessVariableMapper implements DelegateVariableMapping {

    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {
        String imie = (String) delegateExecution.getVariable("imie");
        String nazwisko = (String) delegateExecution.getVariable("nazwisko");
        variableMap.put("imie", imie);
        variableMap.put("nazwisko", nazwisko);
    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {
        String imieZPodprocesu = (String) variableScope.getVariable("imie");
        String nazwiskoZPodprocesu = (String) variableScope.getVariable("nazwisko");
        delegateExecution.setVariable("imie", imieZPodprocesu);
        delegateExecution.setVariable("nazwisko", nazwiskoZPodprocesu);
    }
}
