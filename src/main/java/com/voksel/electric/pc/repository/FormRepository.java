package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.Form;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FormRepository extends Repository<Form, Long> {
	Form findByFormId(Long formId);
	List<Form> findAllByOrderByFormIdDesc();


}
