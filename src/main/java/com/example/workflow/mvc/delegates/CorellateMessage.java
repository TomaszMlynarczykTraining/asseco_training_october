package com.example.workflow.mvc.delegates;


import com.example.workflow.mvc.service.ClientService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorellateMessage implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        MessageCorrelationResult someMessage = delegateExecution.getProcessEngineServices()
                .getRuntimeService()
                .createMessageCorrelation("otherMessageName").correlateWithResult();
        //podobnie jak powyzej, mozemy uzyc tego kodu w sekcji Implementation -> Expression
        // .correlate() - nie zwraca wyniku
               // .correlateWithResult(); - zwraca nam wynik, wyrzuca incydent
        System.out.println(someMessage);
       // runtimeService.correlateMessage("MSG_START_PR02");
    }
}