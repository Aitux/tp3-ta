package fr.fil.ta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Auteur {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	private String nom;
	private String prenom;
	
	@ManyToMany
	List<Livre> livres = new ArrayList<>();

	public Auteur() {
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public List<Livre> getLivres() {
		return livres;
	}
	
	public void addLivre(Livre l) {
		livres.add(l);
		System.out.println("addLivre: " + l.getTitre());
		l.getAuteurs().add(this);
	}
}
