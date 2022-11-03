package com.example.workflow;

import com.example.workflow.mvc.delegates.Somebean;
import com.example.workflow.mvc.delegates.long_term_loan_process.GetAllUsersEMail;
import com.example.workflow.mvc.delegates.long_term_loan_process.SendMailToAllUsers;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.impl.VariableMapImpl;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.mockito.DelegateExpressions;
import org.camunda.community.mockito.ProcessExpressions;
import org.camunda.community.mockito.process.CallActivityMock;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete;

@Deployment(resources = {
        "processes/long_term_loan_process/LongTermLoanProcess.bpmn",
        "processes/long_term_loan_process/ValidateCreditRatingProcess.bpmn"
})
public class LongTermLoanProcessTest extends AbstractProcessEngineRuleTest {

    @Mock
    GetAllUsersEMail getAllUsersEMail;
    @Mock
    SendMailToAllUsers sendMailToAllUsers;

    @Test
    public void testLTLPHappyPath() {

        Collection emailCollection = Arrays.asList("test@test.pl", "test2@test.pl");
        Map<String, Object> eMailsVariableMap = new HashMap<>();
        eMailsVariableMap.put("emailList", emailCollection);

        DelegateExpressions.registerJavaDelegateMock("sendMailToAllUsers");
        DelegateExpressions.registerJavaDelegateMock("getAllUsersEMail")
                .onExecutionSetVariables(eMailsVariableMap);
        MockitoAnnotations.openMocks(this);

        VariableMap map = new VariableMapImpl().putValue("isApplicableToGetLoan", Boolean.TRUE);
        org.camunda.bpm.engine.repository.Deployment deploy = ProcessExpressions.registerCallActivityMock("ValidateCRProcess")
                .onExecutionSetVariables(map)
                .deploy(processEngine);

        ProcessInstance processInstance = runtimeService().startProcessInstanceByMessage("LTLP_start");
        assertThat(processInstance).isWaitingAt("LTLP_acceptRODO");

        Task task = taskService().createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        complete(task);

        assertThat(processInstance).isWaitingAt("LTLP_confirmUserTask");



        Task confirmTask = taskService().createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        complete(confirmTask);

        assertThat(processInstance).isEnded();


    }

}
