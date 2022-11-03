package com.example.workflow.mvc.external_task;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;


@Component
@ExternalTaskSubscription("getCurrency") // create a subscription for this topic name
public class CurrencyExternalTask implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        System.out.println(externalTask.getBusinessKey());

        VariableMap variables = Variables.createVariables();

        variables.put("currency", "Batman");


        externalTaskService.complete(externalTask, variables);
    }
}
