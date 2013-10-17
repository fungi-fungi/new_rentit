package com.renit.rest;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@XmlRootElement(name = "customer")
public class CustomerResource {

	Long customerId;

	String name;

}
