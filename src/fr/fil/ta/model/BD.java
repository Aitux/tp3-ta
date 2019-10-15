package fr.fil.ta.model;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: BD
 *
 */
@Entity
public class BD extends Livre {

	private String serie;
	
	public BD() {
		super();
	}
	
	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	public String getSerie() {
		return serie;
	}

   
}
