package com.voksel.electric.pc.domain;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="cfg_menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 8782021401330853580L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
}
