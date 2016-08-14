package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface RoleRepository extends JpaRepository<Role, String> {
	
	/*@Query("select a.role from Role a, Users b where b.userName=?1 and a.userId=b.userId")
    List<String> findRoleByUserName(String username);

    @Query("select a from Roles a, Users b where b.userName=?1 and a.userId=b.userId")
    List<Role> findAllRoleByUserName(String username);*/

	
}