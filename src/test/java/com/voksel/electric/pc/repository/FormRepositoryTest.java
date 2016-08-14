package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.configuration.RepositoryConfiguration;
import com.voksel.electric.pc.domain.entity.Form;
import com.voksel.electric.pc.domain.entity.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;


/**
 * Created by edsarp on 8/13/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class FormRepositoryTest {

    @Autowired
    private FormRepository formRepository;

    @Test
    public void findByFormId() {
        Form form = this.formRepository.findByFormId("0001");
        assertEquals(form.getMenus(),null);
    }
}
