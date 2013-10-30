package com.rentit.soap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
//@RooJpaActiveRecord
@XmlRootElement
public class PlantResource {

    /**
     */
	@XmlElement
    private long id;

    /**
     */
	@XmlElement
    private String name;

    /**
     */
	@XmlElement
    private String description;

    /**
     */
	@XmlElement
    private float price;
}
