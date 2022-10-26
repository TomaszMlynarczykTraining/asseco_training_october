package com.example.workflow.mvc.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class G1StartInvoiceVerify implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setDueDate(new Date());
    }
}
