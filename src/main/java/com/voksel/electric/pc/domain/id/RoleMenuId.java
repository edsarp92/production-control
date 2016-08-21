package com.voksel.electric.pc.domain.id;

import com.voksel.electric.pc.domain.entity.Menu;
import com.voksel.electric.pc.domain.entity.Role;

import java.io.Serializable;

/**
 * Created by edsarp on 8/13/16.
 */
public class RoleMenuId implements Serializable {

    private static final long serialVersionUID = 4793736746777455343L;

    private String roleId;

    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
