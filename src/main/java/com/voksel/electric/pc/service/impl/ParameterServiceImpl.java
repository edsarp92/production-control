package com.voksel.electric.pc.service.impl;

import com.voksel.electric.pc.domain.entity.Form;
import com.voksel.electric.pc.domain.entity.Menu;
import com.voksel.electric.pc.domain.entity.Role;
import com.voksel.electric.pc.repository.FormRepository;
import com.voksel.electric.pc.repository.MenuRepository;
import com.voksel.electric.pc.repository.RoleRepository;
import com.voksel.electric.pc.service.ParameterService;
import javax.inject.Inject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by edsarp on 8/17/16.
 */
@Service
@Transactional
public class ParameterServiceImpl implements ParameterService {

    private final Logger log = LoggerFactory.getLogger(ParameterService.class);

    @Inject
    FormRepository formRepository;

    @Inject
    RoleRepository roleRepository;

    @Inject
    MenuRepository menuRepository;


    public Form saveForm(Form form) throws Exception {
        log.debug("Request to save form : {}", form);
        Form result= formRepository.save(form);
        return result;
    }



    public void deleteForm(String formId) throws Exception {
        log.debug("Request to delete form: {}", formId);
        formRepository.delete(formId);
    }


    public Page<Form> findAllForm(Pageable pageable) throws Exception {
        log.debug("Request to get all Form");
        return formRepository.findAll(pageable);
    }


    public Form findOneForm(String formId) throws Exception {
        log.debug("Request to get one form: {}",formId);
        return formRepository.findOne(formId);
    }


    public boolean existsForm(String formId) throws Exception {
        log.debug("Request to check exists form: {},",formId);
        return formRepository.exists(formId);
    }


    public Role saveRole(Role role) throws Exception {
        log.debug("Request to save role: {}",role);
        return roleRepository.save(role);
    }


    public void deleteRole(String roleId) throws Exception {
        log.debug("Request to delete role: {}",roleId);
        roleRepository.delete(roleId);
    }


    public Page<Role> findAllRole(Pageable pageable) throws Exception {
        log.debug("Request to find all role");
        return roleRepository.findAll(pageable);
    }


    public Role findOneRole(String roleId) throws Exception {
        log.debug("Request to find one role: {}",roleId);
        return roleRepository.findOne(roleId);
    }


    public List<Menu> findAllMenu() throws Exception {
        log.debug("Request to find all menu");
        return menuRepository.findAllByOrderByMenuIdAsc();
    }


    public Menu findOneMenu(String menuId) throws Exception {
        log.debug("Request to find one menu: {}",menuId);
        return menuRepository.findOne(menuId);
    }



    @Transactional(readOnly = true)
    public Page<Form> searchForm(String query, Pageable pageable) {
        log.debug("Request to search for a page of Jobs for query {}", query);
        // Page<Form> result = formSearchRepository.search(queryStringQuery(query), pageable);
        return null;
    }


    public void deleteMenu(String menuId) throws Exception {
        log.debug("Request to delete menu: {}", menuId);
        menuRepository.delete(menuId);
    }


    public Menu saveMenu(Menu menu) throws Exception {
        log.debug("Request to save menu: {}",menu);
        return menuRepository.save(menu);
    }

    public boolean existsMenu(String menuId) throws Exception {
        log.debug("Request to check exists menu; {}",menuId);
        return menuRepository.exists(menuId);
    }

}
