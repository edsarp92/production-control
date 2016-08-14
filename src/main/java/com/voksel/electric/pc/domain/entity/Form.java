package com.voksel.electric.pc.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_form")
public class Form implements Serializable {

    @Id
    @Column(name = "form_id")
    private String formId;
    @Column(name = "zul_file")
    private String url;
    @Column(name = "name")
    private String formName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "form", fetch = FetchType.EAGER)
    private Set<Menu> menus = new HashSet<>();

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
