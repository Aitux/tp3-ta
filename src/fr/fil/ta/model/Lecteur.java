package fr.fil.ta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Lecteur
 *
 */
@Entity
public class Lecteur {

	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	private String nom;
	private String prenom;
	
	@OneToMany(mappedBy = "lecteur")
	private List<Livre> livres = new ArrayList<>();
	

	@ManyToOne
	private Bibliotheque biblio;
	
	public Lecteur() {
	}

	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
		
	}
	
	public List<Livre> getLivres() {
		return livres;
	}
	
	public void addLivre(Livre l) {
		livres.add(l);
		l.setAtomicLecteur(this);
	}
	
	public void setBiblio(Bibliotheque biblio) {
		this.biblio = biblio;
		biblio.getLecteurs().add(this);
	}
	
	public void setAtomicBiblio(Bibliotheque biblio) {
		this.biblio = biblio;
	}
	
	@ManyToOne
	public Bibliotheque getBiblio() {
		return biblio;
	}
	   
}
