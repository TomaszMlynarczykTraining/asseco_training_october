package com.example.workflow.mvc.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.workflow.mvc.processes.RegistryAdditionalHoursProcess.ADDITIONAL_HOURS;
import static com.example.workflow.mvc.processes.RegistryAdditionalHoursProcess.DAY_OF_MONTH;

@Service
public class PrepareAdditionalHours implements JavaDelegate{

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer date = LocalDate.now().getDayOfMonth();
        Integer numberOfHours = 30;

        delegateExecution.setVariable(ADDITIONAL_HOURS, numberOfHours);
        delegateExecution.setVariable(DAY_OF_MONTH, date);

    }
}
