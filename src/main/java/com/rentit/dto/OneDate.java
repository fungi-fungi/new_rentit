package com.rentit.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean
public class OneDate {
	
	@DateTimeFormat(style = "MM")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

}
