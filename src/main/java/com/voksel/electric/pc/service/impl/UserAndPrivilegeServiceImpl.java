package com.voksel.electric.pc.service.impl;

import com.voksel.electric.pc.domain.Privilege;
import com.voksel.electric.pc.domain.entity.Role;
import com.voksel.electric.pc.domain.entity.User;
import com.voksel.electric.pc.domain.entity.UserRole;
import com.voksel.electric.pc.repository.RoleRepository;
import com.voksel.electric.pc.repository.UserRepository;
import com.voksel.electric.pc.repository.UserRoleRepository;
import com.voksel.electric.pc.service.UserAndPrivilegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edsarp on 8/20/16.
 */
@Service
public class UserAndPrivilegeServiceImpl implements UserAndPrivilegeService {

    private final Logger log = LoggerFactory.getLogger(UserAndPrivilegeService.class);

    @Inject
    UserRepository userRepository;

    @Inject
    UserRoleRepository userRoleRepository;

    @Inject
    RoleRepository roleRepository;

    @Override
    public Page<User> findAllUser(Pageable pageable) throws Exception {
        log.debug("Request to find all user");
        return userRepository.findAll(pageable);
    }

    @Override
    public User findOneUser(Integer userId) throws Exception {
        log.debug("Request to find one user: {}",userId);
        return userRepository.findOne(userId);
    }

    @Override
    public User findOneUserByUserName(String userName) throws Exception {
        log.debug("Request to find one by user name: {}",userName);
        return userRepository.findOneByUserName(userName);
    }

    @Override
    public boolean existsUserByUserName(String userName) throws Exception {
        log.debug("Request to exists user by user name: {}",userName);
        return userRepository.countByUserName(userName)>0?true:false;
    }

    @Override
    public List<Privilege> findAllUserPrivilege() throws Exception {
        log.debug("Request to find all user privilege");
        List<Privilege> privileges =new ArrayList<>();
        List<Role> roles=roleRepository.findAll();
        for (Role role:roles) {
            Privilege privilege = new Privilege();
            privilege.setRoleId(role.getRoleId());
            privilege.setRoleName(role.getRoleName());
            privilege.setStatus(false);
            privileges.add(privilege);
        }
        return privileges;
    }

    @Override
    public List<Privilege> findAllUserPrivilegeByUserId(Integer userId) throws Exception {
        log.debug("Request to find all user privilege by user id: {}",userId);
        List<Privilege> privileges =new ArrayList<>();
        List<Role> roles=roleRepository.findAll();
        List<UserRole> userRoles=userRoleRepository.findAllByUserId(userId);
        for (Role role:roles){
            Privilege privilege =new Privilege();
            privilege.setUserId(userId);
            privilege.setRoleId(role.getRoleId());
            privilege.setRoleName(role.getRoleName());
            for (UserRole userRole:userRoles){
                boolean result=userRole.getRoleId().equals(role.getRoleId());
                if(result) {
                    privilege.setStatus(result);
                    break;
                }
            }
            privileges.add(privilege);
        }
        return privileges;
    }

    @Override
    public User saveUser(User user) throws Exception {
        log.debug("Request to save user: {}",user);
        User result=userRepository.save(user);
        return result;
    }

    @Override
    public List<UserRole> savePrivilege(List<UserRole> userRole) throws Exception {
        log.debug("Request to save privilege: {}",userRole);
        userRoleRepository.deleteByUserId(userRole.get(0).getUserId());
        userRoleRepository.save(userRole);
        return null;
    }

    @Override
    public void deleteUser(Integer userId) throws Exception {
        log.debug("Request to delete User and user role: {}",userId);
        userRepository.delete(userId);

    }
}
