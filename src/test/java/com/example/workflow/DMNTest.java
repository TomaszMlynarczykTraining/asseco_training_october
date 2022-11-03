package com.example.workflow;

import org.assertj.core.util.Maps;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

@Deployment(resources = {
        "processes/TEST_DMN.dmn"
})
public class DMNTest extends AbstractProcessEngineRuleTest {

    @Test
    public void shouldReturnTrue(){
        Map<String, Object> stringStringMap = Maps.newHashMap("pizzaType", "MARGARITHA");
        DmnDecisionTableResult dmnDecisionRuleResults = processEngine.getDecisionService().evaluateDecisionTableByKey("Decision_0fjsi5x",
                stringStringMap);

        Assert.assertEquals("true", dmnDecisionRuleResults.getSingleResult().getFirstEntry());
    }
}
