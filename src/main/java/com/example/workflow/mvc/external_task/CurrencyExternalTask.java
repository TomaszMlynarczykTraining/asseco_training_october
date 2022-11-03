package com.example.workflow.mvc.external_task;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@ExternalTaskSubscription("getCurrency") // create a subscription for this topic name
public class CurrencyExternalTask implements ExternalTaskHandler {

    public static final String SERVER_URI = "https://api.nbp.pl/api/exchangerates/rates/a/chf/?format=json";
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        System.out.println(externalTask.getBusinessKey());

        VariableMap variables = Variables.createVariables();

        variables.put("currency", "Batman");

        RestTemplate restTemplate = new RestTemplate();
        Response currencyResp = restTemplate.getForObject(SERVER_URI, Response.class);
        System.out.println(currencyResp);

        variables.put("currencyFromNbp", currencyResp.getCurrency());

        externalTaskService.complete(externalTask, variables);
    }
}
