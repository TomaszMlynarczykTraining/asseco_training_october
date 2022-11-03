package com.example.workflow;

import com.example.workflow.mvc.delegates.Somebean;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.mockito.DelegateExpressions;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@Deployment(resources = {
        "processes/incident_test.bpmn"
})
public class IncidentProcessTest  extends  AbstractProcessEngineRuleTest {


    @Mock
    Somebean somebean;

    @Test
    public void testHappyPath(){
        DelegateExpressions.registerJavaDelegateMock("somebean");
        MockitoAnnotations.openMocks(this);

// umiescic implementacje dla procesu podrzednego
       /* ProcessExpressions.registerSubProcessMock(SUB_PROCESS_ID)
                .onExecutionWaitForMessage("SomeMessage")
                .onExecutionWaitForTimerWithDate(waitUntilDate)
                .onExecutionSetVariables(createVariables().putValue("foo", "bar"))
                .deploy(rule);*/

        ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("Process_10n1nyt");
        assertThat(processInstance).isWaitingAt("Activity_0eyz6ov");

        Task task = taskService().createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        complete(task );

        execute(job("Event_0qdik5i"));

        assertThat(processInstance).isEnded();
    }
}
