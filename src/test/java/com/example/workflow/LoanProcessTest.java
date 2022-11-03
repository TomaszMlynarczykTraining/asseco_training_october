package com.example.workflow;

import com.example.workflow.mvc.delegates.Somebean;
import com.example.workflow.mvc.loanProcess.CheckCustomerIDProcessDelegate;
import com.example.workflow.mvc.loanProcess.LoanProcessValidateCustomerDataDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.mockito.DelegateExpressions;
import org.camunda.community.mockito.ProcessExpressions;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.engine.variable.Variables.createVariables;

@Deployment(resources = {
        "processes/LoanProcess.bpmn"
        ,
        "processes/long_term_loan_process/LongTermLoanProcess.bpmn"
})
public class LoanProcessTest extends  AbstractProcessEngineRuleTest {

    @Mock
    Somebean somebean;
    CheckCustomerIDProcessDelegate checkCustomerIDProcessDelegate;
    LoanProcessValidateCustomerDataDelegate loanProcessValidateCustomerDataDelegate;

    @Test
    public void testHappyPath(){
        DelegateExpressions.registerJavaDelegateMock("checkCustomerIDProcessDelegate").onExecutionSetVariables(createVariables()
                .putValue("duplicateCustomer", Boolean.FALSE));
        DelegateExpressions.registerJavaDelegateMock("loanProcessValidateCustomerDataDelegate").onExecutionSetVariables(createVariables()
                .putValue("isCorrect", Boolean.TRUE)
                .putValue("isLongLoanTerm", Boolean.TRUE)
                .putValue("isShortLoanTerm", Boolean.FALSE)
                .putValue("isHouseLoanTerm", Boolean.FALSE)
        );
        MockitoAnnotations.openMocks(this);

// umiescic implementacje dla procesu podrzednego
       //ProcessExpressions.registerCallActivityMock("LongTermLoanProcess").deploy(processEngine);
        ProcessExpressions.registerCallActivityMock("LongTermLoanProcess");


        ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("Loan_Process");
        assertThat(processInstance).isWaitingAt("LP_030");

        Task task = taskService().createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        complete(task);

        assertThat(processInstance).isWaitingAt("LP_040");

        task = taskService().createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        complete(task);

//        assertThat(processInstance).isWaitingAt("LTLP_acceptRODO");


//        execute(job("Event_0qdik5i"));
//
//        assertThat(processInstance).isEnded();
    }
}
