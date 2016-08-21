package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.entity.RoleMenu;
import com.voksel.electric.pc.domain.id.RoleMenuId;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by edsarp on 8/13/16.
 */
@Component
public interface RoleMenuRepository extends JpaRepository<RoleMenu, RoleMenuId> {

    @Query("SELECT a FROM RoleMenu a JOIN a.menu b ORDER BY b.menuId, b.sequence")
    List<RoleMenu> findAllJoinMenu();

    @Query("SELECT a FROM RoleMenu a JOIN a.menu b WHERE a.roleId=:roleId ORDER BY b.menuId, b.sequence")
    List<RoleMenu> findAllJoinMenuRoleId(@Param("roleId") String roleId)  throws DataAccessException;

    List<RoleMenu> findAllBymenuId(String menuId)  throws DataAccessException;


}
