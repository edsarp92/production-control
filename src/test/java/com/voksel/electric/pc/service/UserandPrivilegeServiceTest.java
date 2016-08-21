package com.voksel.electric.pc.service;

import com.voksel.electric.pc.component.MenuTreeItem;
import com.voksel.electric.pc.configuration.ServiceConfiguration;
import com.voksel.electric.pc.domain.entity.Role;
import com.voksel.electric.pc.domain.entity.User;
import com.voksel.electric.pc.domain.entity.UserRole;
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
public class UserandPrivilegeServiceTest {

    @Autowired
    UserAndPrivilegeService userAndPrivilegeService;


    @Test
    public void saveUserAndPrivilege() throws Exception{
        User user=new User();
        UserRole userRole=new UserRole();
        user.setUserName("edwars");
        user.setEmail("edwar@email.com");
        user.setEnabled(1);
        user.setPassword("edwar");
   /*     userRole.setRoleId("XX");
        userRole.setUserId(2);
        List<UserRole> userRoles=new ArrayList<UserRole>();
        userRoles.add(userRole);
        user.setRoles(userRoles);*/
        user=userAndPrivilegeService.saveUser(user);
        assertTrue(user.getUserId()!=null);
    }

    @Test
    public void deleteuser() throws Exception{
        userAndPrivilegeService.deleteUser(2);
        //assertTrue(user.getUserid()!=null);
    }
}
