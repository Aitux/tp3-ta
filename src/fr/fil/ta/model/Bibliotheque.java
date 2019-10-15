package fr.fil.ta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bibliotheque {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	@OneToMany(mappedBy = "biblio")
	private List<Lecteur> lecteurs = new ArrayList<>();
	
	@OneToMany(mappedBy = "biblio")
	private List<Livre> livres = new ArrayList<>();

	public Bibliotheque() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
    
	public List<Lecteur> getLecteurs() {
		return lecteurs;
	}
	
	public List<Livre> getLivres() {
		return livres;
	}
	
	public void addLecteur(Lecteur l) {
		l.setAtomicBiblio(this);
		lecteurs.add(l);
	}
	
	public void addLivre(Livre l) {
		livres.add(l);
		l.setAtomicBiblio(this);
	}
	


}
