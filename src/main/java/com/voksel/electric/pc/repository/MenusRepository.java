/*
package com.repository;

import com.domain.Menus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MenusRepository extends Repository<Menus, Long> {
	@Query(value="select a.id, a.menu_id, a.name, a.form_id, a.menu_parent, a.seq, a.param, case when a.menu_id = b.menu_id then '1' else '0' end as pick, "
			+ " b.role_id, (select zul_file from sys_form c where a.form_id = c.form_id) as zul_file from cfg_menu a, cfg_role_menu b "
			+ " order by a.menu_id, a.menu_parent, a.seq", nativeQuery=true)
	List<Menus> findItemsMenu();
	
	@Query(value="select a.id, a.menu_id, a.name, a.form_id, a.menu_parent, a.seq, a.param, case when a.menu_id = b.menu_id then '1' else '0' end as pick, "
			+ " b.role_id, (select zul_file from sys_form c where a.form_id = c.form_id) as zul_file from cfg_menu a, cfg_role_menu b "
			+ " where b.menu_id = a.menu_id and b.role_id = :roleId order by a.menu_id, a.menu_parent, a.seq", nativeQuery=true)
	List<Menus> findItemsMenuByRoleId(@Param("roleId") String roleId);
	
	@Query(value="select a.id, a.menu_id, a.name, a.form_id, a.menu_parent, a.seq, a.param, case when a.menu_id = b.menu_id then '1' else '0' end as pick, "
			+ " b.role_id, (select zul_file from sys_form c where a.form_id = c.form_id) as zul_file "
			+ " from cfg_menu a left join cfg_role_menu b on b.menu_id = a.menu_id  and b.role_id = :roleId order by a.menu_id", nativeQuery=true)
	List<Menus> findAllItemsMenuByRoleId(@Param("roleId") String roleId);
}
*/
