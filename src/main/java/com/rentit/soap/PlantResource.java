package com.rentit.soap;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@XmlRootElement(name = "plant")
public class PlantResource {

	Long plantId;

	Float price;

	String name;

	String description;
}
