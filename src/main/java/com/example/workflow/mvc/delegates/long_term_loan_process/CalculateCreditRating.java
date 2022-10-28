package com.example.workflow.mvc.delegates.long_term_loan_process;

import com.example.workflow.mvc.processes.LongTermLoanProcess;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CalculateCreditRating implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        boolean isApplicableToGetLoan = delegateExecution.getVariable(LongTermLoanProcess.DEBT_AMOUNT) == null;
        delegateExecution.setVariable(LongTermLoanProcess.IS_APPLICABLE_TO_GET_LOAN, isApplicableToGetLoan);
    }
}
