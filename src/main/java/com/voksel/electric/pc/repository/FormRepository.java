package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FormRepository extends JpaRepository<Form, String> {
	Form findByFormId(String formId);
	List<Form> findAllByOrderByFormIdDesc();


}
