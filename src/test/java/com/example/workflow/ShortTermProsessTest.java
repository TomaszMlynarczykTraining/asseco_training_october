package com.example.workflow;

import com.example.workflow.mvc.shorttermloan.delegates.CalculationDelegate;
import com.example.workflow.mvc.shorttermloan.delegates.ShortTermLoanMockDelegate;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.mockito.DelegateExpressions;
import org.camunda.community.mockito.ProcessExpressions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete;
import static org.camunda.bpm.engine.variable.Variables.createVariables;

@Deployment(resources = {
        "processes/shortTermLoanProcess.bpmn",
        "processes/short_loan_process_calculate_decision.bpmn"
})
public class ShortTermProsessTest extends AbstractProcessEngineRuleTest {

    @Mock
    ShortTermLoanMockDelegate shortTermLoanMockDelegate;
    @Mock
    CalculationDelegate calculationDelegate;

    @Test
    public void shouldExecuteHappyPath() {
        String processDefinitionKey = "ShortTermLoanProcess";

        Map<String, Object> variables = new HashMap<>();
        variables.put("clientId", 1);
//        variables.put("debtAmount", 5000);
        variables.put("userDecision", "yes");


        DelegateExpressions.registerJavaDelegateMock("shortTermLoanMockDelegate")
                .onExecutionSetVariables(createVariables()
                        .putValue("debtAmount", 5000)
                );
        DelegateExpressions.registerJavaDelegateMock("calculationDelegate")
                .onExecutionSetVariables(createVariables()
                        .putValue("positivelyVerified", "Yes")
                        .putValue("userDecision", "yes")
                        .putValue("debtAmount", 5000)
                );

        MockitoAnnotations.openMocks(this);
        ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(processDefinitionKey, variables);
        assertThat(processInstance).isWaitingAt("Activity_1fy9chy");


        Task task = taskService().createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        complete(task );



        ProcessExpressions.registerCallActivityMock("Process_1jodzqq")

                .onExecutionAddVariable("positivelyVerified", "Yes");

    }
    }
