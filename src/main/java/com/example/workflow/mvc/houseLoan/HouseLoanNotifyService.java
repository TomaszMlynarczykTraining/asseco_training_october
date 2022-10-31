package com.example.workflow.mvc.houseLoan;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class HouseLoanNotifyService implements JavaDelegate{

    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("Send an E-mail you dumb idiot!");
    }
}
