package com.example.workflow.mvc.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RecruitmentProcessStartTaskListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        //mozna to zrobic na sposoby
        //1 - przekazanie zmiennej, 2 - skorzystac z odpowiedniej klasy
        String processId = delegateExecution.getProcessInstance().getProcessInstanceId();
        List<Task> taskList = delegateExecution.getProcessEngineServices().getTaskService().createTaskQuery()
                .processInstanceId(processId).list();
        for (Task task: taskList) {
            task.setDueDate(new Date());
        }
    }
}
