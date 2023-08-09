package com.exam.model;

import javax.persistence.*;

//this table will have user and roles

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long userRoleId;

    //takes single user.
    //eager- when userrole is fetched, it wl show to whch user it belongs
    @ManyToOne(fetch = FetchType.EAGER )
    private  User user;

    @ManyToOne
    private  Role role;

    public UserRole() {
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
