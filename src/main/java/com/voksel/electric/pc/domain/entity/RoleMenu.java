package com.voksel.electric.pc.domain.entity;

import com.voksel.electric.pc.domain.id.RoleMenuId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="r_role_menu")
@IdClass(RoleMenuId.class)
public class RoleMenu implements Serializable{

	private static final long serialVersionUID = 1754927023759959427L;

	@Id
	private String roleId;

	@OneToOne(optional=true)
	@JoinColumn(name = "roleId",referencedColumnName = "role_id", insertable=false, updatable=false)
	private Role role;

	@Id
	private String menuId;

	@OneToOne()
	@JoinColumn(name="menuId",referencedColumnName="menu_id", insertable=false, updatable=false)
	private Menu menu;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Menu getMenu() {
		return menu;
	}

}

