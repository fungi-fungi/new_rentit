package com.rentit.security;
import org.hibernate.validator.constraints.Email;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class Users {

    @NotNull
    @Size(min = 3)
    @Column(unique = true)
    private String username;
    
    private String name;

    @NotNull
    @Size(min = 3)
    private String password;
    
    @NotNull
    @Size(min = 3)
    @Email
    private String email;

    @Value("true")
    private Boolean enabled;
}
