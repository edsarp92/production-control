package com.voksel.electric.pc.domain.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="sys_menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 5860342063567874062L;

	@Id
	@Column(name="menu_id")
	private String menuId;
	@Column(name="name")
	private String menuName;
	@OneToOne()
	@JoinColumn(name = "form_id")
	private Form form;
	@Column(name="parent_id")
	@JoinColumn(name="parent_id",referencedColumnName="menu_id")
	private Menu parent;
	@Column(name="sequence")
	private Integer sequence;
	@Column(name="param")
	private String parameter;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
}
