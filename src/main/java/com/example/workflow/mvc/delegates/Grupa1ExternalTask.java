package com.example.workflow.mvc.delegates;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ExternalTaskSubscription("getCurrency") // create a subscription for this topic name
public class Grupa1ExternalTask implements ExternalTaskHandler {


    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://api.nbp.pl/api/exchangerates/rates/a/chf/";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);

//        System.out.println(response.getBody());

        String result = response.getBody().substring(response.getBody().length()-9,response.getBody().length()-3);

        VariableMap variables = Variables.createVariables();

        variables.put("exchangeRate", result);

        //externalTaskService.setVariables(externalTask, variables);


//        format JSON: nagłówek Accept: application/json lub parameter ?format=json
//        format XML: nagłówek Accept: application/xml lub parameter ?format=xml

        //http://api.nbp.pl/api/exchangerates/rates/a/chf/
//        System.out.println(externalTask.getBusinessKey());
////        VariableMap variables = Variables.createVariables();
////        variables.put("customerSurname", "Batman");
//
        externalTaskService.complete(externalTask, variables);

    }
}
