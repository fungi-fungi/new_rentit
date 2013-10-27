package com.rentit.soap;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@XmlRootElement
public class PlantResource {

    /**
     */
    private long id;

    /**
     */
    private String name;

    /**
     */
    private String description;

    /**
     */
    private float price;
}
