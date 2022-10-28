package com.example.workflow.mvc.shorttermloan.delegates;


import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShortTermLoanMockDelegate implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        System.out.println("ShortTermLoanMockDelegate!");
        String idKlienta = (String) delegateExecution.getVariable("clientId");

        Optional<Client> client = clientService.getClientById(idKlienta);
        client.ifPresent(c ->
                delegateExecution.setVariable("debtAmount", c.getDebt().getAmount()));

    }
}