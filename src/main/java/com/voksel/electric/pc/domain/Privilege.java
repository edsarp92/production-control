package com.voksel.electric.pc.domain;

import java.io.Serializable;

/**
 * Created by edsarp on 8/20/16.
 */
public class Privilege implements Serializable{

    private static final long serialVersionUID = 6519187268762899722L;

    private Integer userId;

    private String roleId;

    private String roleName;

    private boolean status;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
