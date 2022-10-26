package com.example.workflow.mvc.delegates;

import com.example.workflow.mvc.entity.Notification;
import com.example.workflow.mvc.service.NotificationService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllNotificationsDelegate implements JavaDelegate {

    @Autowired
    NotificationService notificationService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<Notification> listOfNotifications = notificationService.getNotification().stream().toList();
        delegateExecution.setVariable("notificationList", listOfNotifications);

    }
}
