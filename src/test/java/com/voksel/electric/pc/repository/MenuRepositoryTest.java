package com.voksel.electric.pc.repository;
import com.voksel.electric.pc.configuration.RepositoryConfiguration;
import com.voksel.electric.pc.domain.entity.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;


/**
 * Created by edsarp on 8/13/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void findByMenuId() {
        Menu menu = this.menuRepository.findByMenuId("000101");
        assertEquals(menu.getForm().getFormId(),"0001");
    }


}
