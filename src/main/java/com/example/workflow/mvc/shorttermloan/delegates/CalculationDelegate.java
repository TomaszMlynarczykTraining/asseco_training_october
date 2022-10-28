package com.example.workflow.mvc.shorttermloan.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CalculationDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String amount = (String) delegateExecution.getVariable("debtAmount");
        String verified = (String) delegateExecution.getVariable("verified");
        if (Long.valueOf(amount) > 500 && verified == "yes") {
            delegateExecution.setVariable("positivelyVerified", "Yes");
        } else {
            delegateExecution.setVariable("positivelyVerified", "No");
        }
    }
}
