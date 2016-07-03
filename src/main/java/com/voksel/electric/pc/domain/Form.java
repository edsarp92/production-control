package com.voksel.electric.pc.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_form")
public class Form implements Serializable {
    private static final long serialVersionUID = -7894303615765226496L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "form_id")
    public Long formId;
    @Column(name = "zul_file")
    public String url;
    @Column(name = "nama")
    public String formName;

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
