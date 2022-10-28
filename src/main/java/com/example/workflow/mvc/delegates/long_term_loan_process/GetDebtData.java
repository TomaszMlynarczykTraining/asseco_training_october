package com.example.workflow.mvc.delegates.long_term_loan_process;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.processes.LongTermLoanProcess;
import com.example.workflow.mvc.repository.ClientRepository;
import com.example.workflow.mvc.repository.DebtRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDebtData implements JavaDelegate {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DebtRepository debtRepository;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String clientId = (String)delegateExecution.getVariable(LongTermLoanProcess.CLIENT_ID);
        Client client = clientRepository.getReferenceById(Long.parseLong(clientId));
        Debt debt = client.getDebt();
        if(debt!=null){
            delegateExecution.setVariable(debt.getAmount(), LongTermLoanProcess.DEBT_AMOUNT);
            delegateExecution.setVariable(debt.getIsOverdue(), LongTermLoanProcess.DEBT_IS_OVERDUE);
            delegateExecution.setVariable(debt.getCurrency(), LongTermLoanProcess.DEBT_CURRENCY);
        }
    }
}
