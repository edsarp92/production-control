package com.voksel.electric.pc.service.impl;

import com.voksel.electric.pc.domain.entity.Form;
import com.voksel.electric.pc.domain.entity.Role;
import com.voksel.electric.pc.repository.FormRepository;
import com.voksel.electric.pc.repository.RoleRepository;
import com.voksel.electric.pc.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by edsarp on 8/17/16.
 */
@Service
@Transactional
public class ParameterServiceImpl implements ParameterService {

    private final Logger log = LoggerFactory.getLogger(ParameterService.class);

    @Autowired
    FormRepository formRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Form saveForm(Form form) throws Exception {
        log.debug("Request to save form : {}", form);
        return formRepository.save(form);
    }


    @Override
    public void deleteForm(String formId) throws Exception {
        log.debug("Request to delete form: {}", formId);
        formRepository.delete(formId);
    }

    @Override
    public Page<Form> findAllForm(Pageable pageable) throws Exception {
        log.debug("Request to get all Form");
        return formRepository.findAll(pageable);
    }

    @Override
    public Form findOneForm(String formId) throws Exception {
        log.debug("Request to get one form: {}",formId);
        return formRepository.findOne(formId);
    }

    @Override
    public boolean existsForm(String formId) throws Exception {
        log.debug("Request to check exists form: {},",formId);
        return formRepository.exists(formId);
    }

    @Override
    public Role saveRole(Role role) throws Exception {
        log.debug("Request to save role: {}",role);
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(String roleId) throws Exception {
        log.debug("Request to delete role: {}",roleId);
        roleRepository.delete(roleId);
    }

    @Override
    public Page<Role> findAllRole(Pageable pageable) throws Exception {
        log.debug("Request to find all role");
        return roleRepository.findAll(pageable);
    }

    @Override
    public Role findOneRole(String roleId) throws Exception {
        log.debug("Request to find one role: {}",roleId);
        return roleRepository.findOne(roleId);
    }
}
