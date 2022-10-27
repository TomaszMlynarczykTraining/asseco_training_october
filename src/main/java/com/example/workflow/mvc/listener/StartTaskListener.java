package com.example.workflow.mvc.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.task.listener.DelegateExpressionTaskListener;
import org.springframework.stereotype.Component;

@Component
public class StartTaskListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println(delegateExecution.getCurrentActivityId());

    }
}
