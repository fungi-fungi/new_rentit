package com.rentit.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

@XmlRootElement
@RooJavaBean
public class DateRangeResource {
	
	Date start;
	
	Date end;

}
