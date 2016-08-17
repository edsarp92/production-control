package com.voksel.electric.pc.service;

import com.voksel.electric.pc.domain.entity.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by edsarp on 8/17/16.
 */
public interface SetupSystemService {

    Form saveForm(Form form) throws Exception;
    void deleteForm(String formId) throws Exception;
    Page<Form> findAllForm(Pageable pageable) throws Exception;
    Form findOneForm(String formId)throws Exception;
    Page<Form> searchForm(String query)throws Exception;
    boolean existsForm(String formId) throws Exception;
}
