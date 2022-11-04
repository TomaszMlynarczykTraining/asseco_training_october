package com.example.workflow;

import org.assertj.core.util.Maps;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

@Deployment(resources = {
        "processes/IsApplicationAccepted.dmn"
})
public class IsAppAcceptedDmnTest extends AbstractProcessEngineRuleTest {

    @Test
    public void shouldReturnRejested(){
        Map<String, Object> stringStringMap = Maps.newHashMap("additionalHours", 45);
        stringStringMap.put("dayOfMonth", 15);

        DmnDecisionTableResult dmnDecisionRuleResults = processEngine.getDecisionService().evaluateDecisionTableByKey("Decision_1yy1adi",
                stringStringMap);

        Assert.assertEquals("rejested", dmnDecisionRuleResults.getSingleResult().getFirstEntry());
    }

    @Test
    public void shouldReturnAccepted(){
        Map<String, Object> stringStringMap = Maps.newHashMap("additionalHours", 20);
        stringStringMap.put("dayOfMonth", 15);
        DmnDecisionTableResult dmnDecisionRuleResults = processEngine.getDecisionService().evaluateDecisionTableByKey("Decision_1yy1adi",
                stringStringMap);

        Assert.assertEquals("accepted", dmnDecisionRuleResults.getSingleResult().getFirstEntry());
    }

}
