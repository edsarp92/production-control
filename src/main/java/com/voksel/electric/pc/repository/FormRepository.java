package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.entity.Form;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FormRepository extends JpaRepository<Form, String>{
	Form findByFormId(String formId) throws DataAccessException;
	List<Form> findAllByOrderByFormIdDesc() throws DataAccessException;
	Page<Form> findAllByFormIdOrFormNameOrderByFormIdAsc(Pageable pageable,String formId, String formName) throws DataAccessException;

}
