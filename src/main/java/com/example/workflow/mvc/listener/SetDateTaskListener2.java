package com.example.workflow.mvc.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class SetDateTaskListener2 implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setDueDate(new Date());
    }
}
