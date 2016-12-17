package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.configuration.RepositoryConfiguration;
import com.voksel.electric.pc.domain.entity.Form;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by edsarp on 8/13/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class FormRepositoryTest {

    @Inject
    private FormRepository formRepository;

    @Test
    public void findByFormId() {
        Form form = this.formRepository.findByFormId("0001");
        assertNotNull(form.getMenus());
    }
}
