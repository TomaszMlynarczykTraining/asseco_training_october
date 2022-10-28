package com.example.workflow.mvc.shorttermloan.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CalculationDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String amount = (String) delegateExecution.getVariable("debtAmount");
        String userDecision = (String) delegateExecution.getVariable("userDecision");
        if (Long.valueOf(amount) > 50 && userDecision.equals("yes")) {
            delegateExecution.setVariable("positivelyVerified", "Yes");
        } else {
            delegateExecution.setVariable("positivelyVerified", "No");
        }
    }
}
