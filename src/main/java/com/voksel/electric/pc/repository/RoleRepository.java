package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface RoleRepository extends JpaRepository<Role, String> {
	


	
}