package com.voksel.electric.pc.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_role")
public class Role implements Serializable {

    @Id
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "name")
    private String roleName;

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
}
