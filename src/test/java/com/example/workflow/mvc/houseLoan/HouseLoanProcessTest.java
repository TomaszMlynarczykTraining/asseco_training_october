package com.example.workflow.mvc.houseLoan;

import com.example.workflow.mvc.entity.Debt;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.mockito.DelegateExpressions;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.junit.jupiter.api.Assertions.*;

@Deployment(resources = {
        "processes/houseLoan/houseLoan.bpmn"
})
public class HouseLoanProcessTest extends AbstractProcessEngineRuleTest {

    @Test
    public void shouldReturnDebt(){
        Debt debt = new Debt();
        DelegateExpressions
                .registerJavaDelegateMock("houseLoanService");
        MockitoAnnotations.openMocks(this);

        ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(
                "Process_houseLoan",
                new HashMap<>(){{put("clientId", 1); }}
        );

        assertThat(processInstance).hasPassed("ST_001");
        assertThat(processInstance).isWaitingAt("TIM_001");
        // execute(job("TIM_001"));
        // DelegateExpressions.registerJavaDelegateMock("mockDelegate");

    }
}