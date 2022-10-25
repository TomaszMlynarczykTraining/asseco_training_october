package com.example.workflow.mvc.delegates;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetInvoiceVerifierDelegate implements JavaDelegate {

    @Autowired
    ClientService clientService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<String> listOfUsers = clientService.getClients().stream().map(x->x.getFirstName() + " " + x.getLastName()).collect(Collectors.toList());
        delegateExecution.setVariable("userList", listOfUsers);

    }
}
