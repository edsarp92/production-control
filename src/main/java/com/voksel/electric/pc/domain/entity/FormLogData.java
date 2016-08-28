package com.voksel.electric.pc.domain.entity;

import com.voksel.electric.pc.domain.id.FormLogDataId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by edsarp on 8/17/16.
 */
@Entity
@Table(name = "form_log_data")
@IdClass(FormLogDataId.class)
public class FormLogData implements Serializable {

    private static final long serialVersionUID = -303584781925763717L;

    @Id
    @Column(name = "form_id")
    private String formId;

    @Id
    @Column(name = "field")
    private String field;

    @Column(name = "old_data")
    private String oldData;

    @Column(name = "new_data")
    private String newData;

    @Id
    @Column(name = "time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    @PrePersist
    void updateDates() {
        timeStamp = new Date();
    }
}
