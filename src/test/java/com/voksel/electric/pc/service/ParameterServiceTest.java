package com.voksel.electric.pc.service;

import com.voksel.electric.pc.configuration.ServiceConfiguration;
import com.voksel.electric.pc.domain.entity.Form;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by edsarp on 8/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ServiceConfiguration.class})
public class ParameterServiceTest {

    @Autowired
    ParameterService parameterService;

    @Test
    public void searchForm() throws Exception{
        String expectedDate = "2014-10-29";
        String expectedWord = "java";
        CriteriaQuery query = new CriteriaQuery(
                new Criteria("_all").contains(expectedWord).and(
                        new Criteria("date").greaterThanEqual(expectedDate)));


        List<Form>lis=parameterService.searchForm("user");
    }
}
