package com.example.workflow.mvc.mappers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

import static com.example.workflow.mvc.processes.EmailSenderProcess.EMAIL_ADDRESS_VARIABLE;
import static com.example.workflow.mvc.processes.InvoiceVariableProcess.*;

@Component
public class InvoiceVariableMapper implements DelegateVariableMapping {
    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {
        Boolean orderOK = (Boolean) delegateExecution.getVariable(ORDER_OK);
        String userName = (String) delegateExecution.getVariable(USER_NAME);
        variableMap.put(USER_NAME, userName);
        variableMap.put(ORDER_OK, orderOK);
    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {
        Boolean invoiceOK = (Boolean) variableScope.getVariable(INVOICE_OK);
        if (invoiceOK == null){
            invoiceOK = true;
        }
    delegateExecution.setVariable(INVOICE_OK, invoiceOK);


    }
}
