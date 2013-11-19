package com.rentit.security;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class Users {

    /**
     */
    @NotNull
    @Size(min = 3)
    private String username;

    /**
     */
    @NotNull
    @Size(min = 3)
    private String password;

    /**
     */
    @Value("true")
    private Boolean enabled;
}
