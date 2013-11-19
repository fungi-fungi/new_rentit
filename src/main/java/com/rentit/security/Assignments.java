package com.rentit.security;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class Assignments {

    /**
     */
	@ManyToOne
    private Users userRentIt;

    /**
     */
	@ManyToOne
    private Authorities authority;
}
