package com.voksel.electric.pc.domain.id;

import java.io.Serializable;

/**
 * Created by edsarp on 8/14/16.
 */
public class UserRoleId implements Serializable {


    private static final long serialVersionUID = -7504633461069327147L;

    private Integer userId;
    private String roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
