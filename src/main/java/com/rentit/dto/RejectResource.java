package com.rentit.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@XmlRootElement(name = "RejectPO")
public class RejectResource {

	private Long poid;
	private String commentt;

}
