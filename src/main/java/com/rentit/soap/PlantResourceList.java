package com.rentit.soap;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@RooJavaBean
@RooToString
//@RooJpaActiveRecord
@XmlRootElement
public class PlantResourceList {

    /**
     */
    //@ManyToMany(cascade = CascadeType.ALL)
	@XmlElement
    private List<PlantResource> plantResources = new ArrayList<PlantResource>();

	public void addPlant(PlantResource resource) {
		this.plantResources.add(resource);
		
	}

	
}
