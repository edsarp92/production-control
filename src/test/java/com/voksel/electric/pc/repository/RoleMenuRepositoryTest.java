package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.configuration.RepositoryConfiguration;
import com.voksel.electric.pc.domain.entity.Role;
import com.voksel.electric.pc.domain.entity.RoleMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import static org.junit.Assert.*;
/**
 * Created by edsarp on 8/13/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class RoleMenuRepositoryTest {

    @Inject
    RoleMenuRepository roleMenuRepository;

    @Inject
    RoleRepository roleRepository;


    @Test
    public void insertRoleMenu(){
        RoleMenu roleMenu=new RoleMenu();
        roleMenu.setRoleId("01");
        roleMenu.setMenuId("000101");
        roleMenuRepository.save(roleMenu);
        assertNotNull(roleMenu.getRoleId());

    }

    @Test
    public void findAllJoinMenu(){
        List<RoleMenu> roleMenus=this.roleMenuRepository.findAllJoinMenu();
        assertTrue(roleMenus.size()>0);
    }

    @Test
    public void findAllJoinMenuRoleId(){
        List<RoleMenu> roleMenus=this.roleMenuRepository.findAllJoinMenuRoleId("XX");
        assertTrue(roleMenus.size()>0);

    }
}
