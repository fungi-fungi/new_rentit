package com.rentit.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;


@XmlRootElement
@RooJavaBean
public class ErrorResource {

	int status;
	String message;
	
}
