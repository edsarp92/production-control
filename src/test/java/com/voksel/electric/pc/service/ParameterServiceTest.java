package com.voksel.electric.pc.service;

import com.voksel.electric.pc.configuration.ServiceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by edsarp on 8/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ServiceConfiguration.class})
public class ParameterServiceTest {

    //@Inject
   // ParameterService parameterService;

    @Test
    public void searchJob() throws Exception{
        String search = "programmer";
        Pageable pageable= new PageRequest(0, 20);

    }
}
