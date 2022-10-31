package com.example.workflow.mvc.delegates.long_term_loan_process;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.entity.User;
import com.example.workflow.mvc.processes.LongTermLoanProcess;
import com.example.workflow.mvc.repository.ClientRepository;
import com.example.workflow.mvc.repository.DebtRepository;
import com.example.workflow.mvc.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllUsersEMail implements JavaDelegate {
    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<User> userList = userRepository.findAll();
        List<String> emailList = new ArrayList<>();
        userList.stream().forEach(user -> emailList.add(user.getEmailId()));
        delegateExecution.setVariable(LongTermLoanProcess.EMAIL_LIST, emailList);
    }
}
