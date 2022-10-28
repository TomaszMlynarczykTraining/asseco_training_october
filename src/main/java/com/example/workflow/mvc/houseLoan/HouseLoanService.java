package com.example.workflow.mvc.houseLoan;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.entity.Notification;
import com.example.workflow.mvc.repository.ClientRepository;
import com.example.workflow.mvc.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class HouseLoanService implements JavaDelegate{

    @Autowired
    private ClientRepository clientRepository;

    public Debt getClientsDebt(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.map(Client::getDebt).orElse(null);
    }


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long clientId = Long.parseLong((String) delegateExecution.getVariable("clientId"));
        Debt debt = getClientsDebt(clientId);
        delegateExecution.setVariable("debt", Long.parseLong(debt.getAmount()));
    }
}
