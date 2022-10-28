package com.example.workflow.mvc.houseLoan;

import com.example.workflow.mvc.entity.Notification;
import com.example.workflow.mvc.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HouseLoanService implements JavaDelegate {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("psmgpkdfs");
    }
}
