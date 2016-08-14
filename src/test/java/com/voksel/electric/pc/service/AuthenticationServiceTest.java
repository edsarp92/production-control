package com.voksel.electric.pc.service;

import com.voksel.electric.pc.component.MenuTreeItem;
import com.voksel.electric.pc.configuration.RepositoryConfiguration;
import com.voksel.electric.pc.configuration.ServiceConfiguration;
import com.voksel.electric.pc.domain.MenuItem;
import com.voksel.electric.pc.security.AuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by edsarp on 8/14/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ServiceConfiguration.class})
public class AuthenticationServiceTest {

    @Autowired
    AuthenticationService authenticationService;

    @Test
    public void getMenuItems(){
        Collection<MenuTreeItem> menuItems= this.authenticationService.loadMenuItems();
        assertTrue(menuItems.size()>0);
    }

    @Test
    public void loadUserByUserByUserName(){
        UserDetails userDetails=this.authenticationService.loadUserByUsername("admin");
        assertTrue(new ArrayList<>(userDetails.getAuthorities()).size() >0);
    }
}
