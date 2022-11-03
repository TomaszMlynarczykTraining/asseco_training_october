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
import static org.mockito.Mockito.mock;

@Deployment(resources = {
        "processes/houseLoan/houseLoan.bpmn"
})
public class HouseLoanServiceTest extends AbstractProcessEngineRuleTest {
    public final HouseLoanService houseLoanService = mock(HouseLoanService.class);

    @Test
    public void shouldReturnDebt(){
        Debt debt = new Debt();
        debt.setId(1);
        debt.setAmount("100");
        DelegateExpressions
                .registerJavaDelegateMock("houseLoanService")
                .onExecutionSetVariables(Variables.createVariables().putValue("debt", debt).putValue("client", 1L));
        MockitoAnnotations.openMocks(this);

        ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(
                "Process_houseLoan",
                new HashMap<>(){{put("clientId", 1L); }}
        );

        // assertTrue(houseLoanService.getClientsDebt(1L).getAmount().equals("100"));

    }
}
