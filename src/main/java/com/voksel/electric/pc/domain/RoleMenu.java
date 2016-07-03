package com.voksel.electric.pc.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="role_menu")
public class RoleMenu implements Serializable{
	private static final long serialVersionUID = -7894303615765226496L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_menu_id")
	private Long roleMenuId;
	@Column(name="role_id")
	public Long roleId;
	@Column(name="menu_id")
	public Long menuId;

	public Long getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(Long roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
}
