// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.rentit.security;

import com.rentit.security.Users;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

privileged aspect Users_Roo_Jpa_Entity {
    
    declare @type: Users: @Entity;
    
    declare @type: Users: @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS);
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long Users.id;
    
    @Version
    @Column(name = "version")
    private Integer Users.version;
    
    public Long Users.getId() {
        return this.id;
    }
    
    public void Users.setId(Long id) {
        this.id = id;
    }
    
    public Integer Users.getVersion() {
        return this.version;
    }
    
    public void Users.setVersion(Integer version) {
        this.version = version;
    }
    
}
