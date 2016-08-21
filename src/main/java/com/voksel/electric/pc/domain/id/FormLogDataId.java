package com.voksel.electric.pc.domain.id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by edsarp on 8/17/16.
 */
public class FormLogDataId implements Serializable {


    private static final long serialVersionUID = -8566213944242581330L;

    private String formId;
    private String field;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
