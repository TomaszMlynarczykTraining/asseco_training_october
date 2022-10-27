package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ChangeDateDelegate implements JavaDelegate {

    @Autowired
    ManagementService managementService;

    @Autowired
    RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Job timerEvent = managementService.createJobQuery().jobDefinitionId("Event_0dvjq3m").singleResult();
        managementService.setJobDuedate(timerEvent.getId(), new Date());


        runtimeService.signal("CANCEL_OFFER_SINGAL");
    }
}
