package com.voksel.electric.pc.domain.entity;

import com.voksel.electric.pc.domain.id.UserRoleId;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by edsarp on 8/13/16.
 */
@Entity
@Table(name="r_user_role")
@IdClass(UserRoleId.class)
public class UserRole implements Serializable {

    @Id
    private Integer userId;

    @ManyToOne(optional = true)
    @JoinColumn(name = "userId",referencedColumnName = "user_id",insertable = false,updatable = false)
    private User user;

    @Id
    private String roleId;

    @ManyToOne(optional = true)
    @JoinColumn(name = "roleId",referencedColumnName = "role_id",insertable = false,updatable = false)
    private Role role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

}
