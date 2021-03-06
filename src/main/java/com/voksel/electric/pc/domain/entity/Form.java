package com.voksel.electric.pc.domain.entity;


import org.springframework.data.annotation.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Document(indexName = "sys_form",type = "Form")
@Table(name = "sys_form")
public class Form implements Serializable {

    private static final long serialVersionUID = -3074899015464984786L;

    @javax.persistence.Id
    @org.springframework.data.annotation.Id
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

    @Override
    public int hashCode() {
        return Objects.hashCode(formId);
    }

    @Override
    public String toString() {
        return "Form{" +
                "formName='" + formName + '\'' +
                ", url='" + url + '\'' +
                ", formId='" + formId + '\'' +
                '}';
    }
}
