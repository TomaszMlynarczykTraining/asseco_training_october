package com.example.workflow.mvc.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SetDateTaskListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("someDate", LocalDate.now());
//        delegateExecution.getProcessEngine().getTaskService().createTaskQuery().taskId()
    }
}
