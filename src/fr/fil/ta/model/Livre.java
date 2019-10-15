package fr.fil.ta.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Livre {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String id;
	
	private String titre;
	private String description;
	
	@ManyToMany
	private List<Auteur> auteurs = new ArrayList<>();
	
	@ManyToOne
	private Bibliotheque biblio;
	
	@ManyToOne
	private Lecteur lecteur;
	
	public Livre() {
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public String getDescription() {
		return description;
	}
   
	public List<Auteur> getAuteurs() {
		return auteurs;
	}
	
	public void setBiblio(Bibliotheque biblio) {
		this.biblio = biblio;
		biblio.getLivres().add(this);
	}
	
	public void addAuteur(Auteur a) {
		auteurs.add(a);
		a.getLivres().add(this);
	}
	
	public void setLecteur(Lecteur lecteur) {
		this.lecteur = lecteur;
	}
	
	public void setAtomicLecteur(Lecteur l)
	{
		this.lecteur = l;
	}
	public void setAtomicBiblio(Bibliotheque b) {
		this.biblio = b;
	}
}
