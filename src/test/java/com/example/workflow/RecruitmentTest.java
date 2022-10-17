package com.example.workflow;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RecruitmentTest extends AbstractProcessEngineRuleTest {

    @Autowired
    public RuntimeService runtimeService;

    @Autowired
    public TaskService taskService;

    @Test
    public void shouldExecuteHappyPath() {

        // given
        String processDefinitionKey = "Process_Recruitment";

        // when
        Map<String, Object> variables = new HashMap<>();
        variables.put("salary", 5000);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);


        String firstTaskId = taskService
                .createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult()
                .getId();

        Map<String, Object> firstTaskVariables = new HashMap<>();
        firstTaskVariables.put("hrDecision", true);
        taskService.complete(firstTaskId, firstTaskVariables);

        String secondTaskId = taskService
                .createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult()
                .getId();
        taskService.complete(secondTaskId);

        // then
        assertThat(processInstance)
                .isEnded()
                .hasPassed("Event_Hired");
    }
}
