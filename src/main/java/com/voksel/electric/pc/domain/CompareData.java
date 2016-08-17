package com.voksel.electric.pc.domain;

import com.voksel.electric.pc.domain.id.FormLogDataId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by edsarp on 8/17/16.
 */

public class CompareData implements Serializable {

    private String field;

    private String oldData;

    private String newData;

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
}
