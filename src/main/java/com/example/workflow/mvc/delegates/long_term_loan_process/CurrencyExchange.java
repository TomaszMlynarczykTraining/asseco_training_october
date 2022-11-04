package com.example.workflow.mvc.delegates.long_term_loan_process;


import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("currencyExchange")
public class CurrencyExchange implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        String currency = "PLN";

        VariableMap variables = Variables.createVariables();

        variables.put("currency", currency);

        externalTaskService.complete(externalTask, variables);
    }
}
