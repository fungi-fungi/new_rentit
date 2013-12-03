package com.rentit;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@XmlRootElement(name = "plant")
public class Plant {

	@NotNull
	private String name;

	private String description;

	@NotNull
	private Float price;
}
