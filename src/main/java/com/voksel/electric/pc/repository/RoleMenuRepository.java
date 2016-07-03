package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.RoleMenu;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface RoleMenuRepository extends Repository<RoleMenu, Long> {
	RoleMenu findByRoleId(Long roleId);
	List<RoleMenu> findAll();
	List<RoleMenu> findAllByRoleId(Long roleId);
}
