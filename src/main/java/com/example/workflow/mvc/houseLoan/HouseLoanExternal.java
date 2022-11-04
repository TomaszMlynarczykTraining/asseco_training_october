package com.example.workflow.mvc.houseLoan;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ExternalTaskSubscription("houseLoanExternal")
public class HouseLoanExternal implements ExternalTaskHandler {


    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        RestTemplate restTemplate = new RestTemplate();
        String currency = externalTask.getVariable("currency").toString();
        String fooResourceUrl
                = "http://api.nbp.pl/api/exchangerates/rates/A/".concat(currency);
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response.getBody());
    }
}
