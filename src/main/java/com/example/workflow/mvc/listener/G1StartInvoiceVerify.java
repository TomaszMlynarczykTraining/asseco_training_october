package com.example.workflow.mvc.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class G1StartInvoiceVerify implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        List<Task> tasks = delegateExecution.getProcessEngine().getTaskService().createTaskQuery().active().processInstanceId(delegateExecution.getCurrentActivityId()).list();
        if (tasks != null && !tasks.isEmpty()) {
            tasks.get(0).setDueDate(new Date());
        }
    }
}
