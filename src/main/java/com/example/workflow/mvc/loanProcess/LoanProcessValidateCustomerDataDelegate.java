package com.example.workflow.mvc.loanProcess;


import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.repository.ClientRepository;
import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanProcessValidateCustomerDataDelegate implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String id = (String) delegateExecution.getVariable(LoanProcessVariable.ID);
        String street = (String) delegateExecution.getVariable(LoanProcessVariable.STREET);
        String phoneNumber = (String) delegateExecution.getVariable(LoanProcessVariable.PHONE_NUMBER);
        String declaredIncome = (String) delegateExecution.getVariable(LoanProcessVariable.DECLARED_INCOME);
        String currency = (String) delegateExecution.getVariable(LoanProcessVariable.CURRENCY);

        delegateExecution.setVariable(LoanProcessVariable.IS_CORRECT, true);

        Optional<Client> optionalClient = clientRepository.findById(Long.parseLong(id));

        if (optionalClient.isPresent()) {
            if (!optionalClient.get().getStreet().equals(street) ||
                    !optionalClient.get().getPhoneNumber().equals(phoneNumber) ||
                    !optionalClient.get().getDeclaredIncome().equals(declaredIncome) ||
                    !optionalClient.get().getCurrency().equals(currency)
            ) {
                delegateExecution.setVariable(LoanProcessVariable.IS_CORRECT, false);
            }
        } else {
            delegateExecution.setVariable(LoanProcessVariable.IS_CORRECT, false);
        }

    }
}