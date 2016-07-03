package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.Roles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface RolesRepository extends Repository<Roles, Long> {
	
	@Query("select a.role from Roles a, Users b where b.userName=?1 and a.userId=b.userId")
    List<String> findRoleByUserName(String username);

    @Query("select a from Roles a, Users b where b.userName=?1 and a.userId=b.userId")
    List<Roles> findAllRoleByUserName(String username);

	
}