package com.voksel.electric.pc.service;

import com.voksel.electric.pc.domain.entity.Form;
import com.voksel.electric.pc.domain.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by edsarp on 8/17/16.
 */
public interface ParameterService {

    Form saveForm(Form form) throws Exception;

    void deleteForm(String formId) throws Exception;

    Page<Form> findAllForm(Pageable pageable) throws Exception;

    Form findOneForm(String formId)throws Exception;

    boolean existsForm(String formId) throws Exception;

    Role saveRole(Role role) throws Exception;

    void deleteRole(String roleId)throws  Exception;

    Page<Role> findAllRole(Pageable pageable) throws Exception;

    Role findOneRole(String roleId) throws Exception;

}