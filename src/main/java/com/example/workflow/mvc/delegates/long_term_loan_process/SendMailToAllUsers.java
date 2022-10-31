package com.example.workflow.mvc.delegates.long_term_loan_process;

import com.example.workflow.mvc.entity.User;
import com.example.workflow.mvc.processes.LongTermLoanProcess;
import com.example.workflow.mvc.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SendMailToAllUsers implements JavaDelegate {
    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
    }
}
