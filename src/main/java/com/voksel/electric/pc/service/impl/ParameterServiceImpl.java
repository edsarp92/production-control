package com.voksel.electric.pc.service.impl;

import com.voksel.electric.pc.domain.entity.Form;
import com.voksel.electric.pc.domain.entity.Menu;
import com.voksel.electric.pc.domain.entity.Role;
import com.voksel.electric.pc.repository.FormRepository;
import com.voksel.electric.pc.repository.MenuRepository;
import com.voksel.electric.pc.repository.RoleRepository;
import com.voksel.electric.pc.repository.search.FormSearchRepository;
import com.voksel.electric.pc.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.elasticsearch.index.query.QueryBuilders.*;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    FormSearchRepository formSearchRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MenuRepository menuRepository;

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

    @Override
    public List<Menu> findAllMenu() throws Exception {
        log.debug("Request to find all menu");
        return menuRepository.findAllByOrderByMenuIdAsc();
    }

    @Override
    public Menu findOneMenu(String menuId) throws Exception {
        log.debug("Request to find one menu: {}",menuId);
        return menuRepository.findOne(menuId);
    }

    @Override
    public List<Form> searchForm(String query) throws Exception {
        log.debug("Request to search form: {}",query);
        return StreamSupport
                .stream(formSearchRepository.search(queryStringQuery(query)).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMenu(String menuId) throws Exception {
        log.debug("Request to delete menu: {}", menuId);
        menuRepository.delete(menuId);
    }

    @Override
    public Menu saveMenu(Menu menu) throws Exception {
        log.debug("Request to save menu: {}",menu);
        return menuRepository.save(menu);
    }

    @Override
    public boolean existsMenu(String menuId) throws Exception {
        log.debug("Request to check exists menu; {}",menuId);
        return menuRepository.exists(menuId);
    }
}
