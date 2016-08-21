package com.voksel.electric.pc.service;

import com.voksel.electric.pc.domain.Privilege;
import com.voksel.electric.pc.domain.entity.User;
import com.voksel.electric.pc.domain.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by edsarp on 8/20/16.
 */
public interface UserAndPrivilegeService {

    Page<User> findAllUser(Pageable pageable) throws Exception;

    User findOneUser(Integer userId)throws Exception;

    User findOneUserByUserName(String userName) throws Exception;

    boolean existsUserByUserName(String userName) throws Exception;

    List<Privilege> findAllUserPrivilege() throws Exception;

    List<Privilege> findAllUserPrivilegeByUserId(Integer userId) throws Exception;

    User saveUser(User user) throws Exception;

    List<UserRole> savePrivilege(List<UserRole> userRole) throws Exception;

    void deleteUser(Integer userId) throws Exception;

}
