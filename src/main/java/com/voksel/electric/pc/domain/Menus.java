package com.voksel.electric.pc.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Menus implements Serializable {
	private static final long serialVersionUID = 8782021401330853580L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column(name="menu_id")
	public Long menuId;
	@Column(name="menu_parent")
	public Long menuParent;
	@Column(name="name")
	public String menuName;
	@Column(name="form_id")
	public Long formId;
	@Column(name="seq")
	public int sequence;
	@Column(name="param")
	public String parameter;
	@Column(name="role_id")
	public Long roleId;
	@Column(name="zul_file")
	public String url;
	@Column(name="pick")
	public String pick;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(Long menuParent) {
		this.menuParent = menuParent;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPick() {
		return pick;
	}

	public void setPick(String pick) {
		this.pick = pick;
	}
}
