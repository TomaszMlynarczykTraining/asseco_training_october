package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.example.workflow.mvc.processes.RegistryAdditionalHoursProcess.REGISTRY_LIST;

@Component
public class GetRegistryList  implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<String> registryList= Arrays.asList("Gieniek Kowalski", "Zenon Nowak");
        delegateExecution.setVariable(REGISTRY_LIST, registryList);

    }
}
