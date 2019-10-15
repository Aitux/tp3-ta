package fr.fil.ta.main;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import fr.fil.ta.model.Auteur;
import fr.fil.ta.model.BD;
import fr.fil.ta.model.Bibliotheque;
import fr.fil.ta.model.Lecteur;
import fr.fil.ta.model.Livre;
import fr.fil.ta.model.Roman;

public class JpaTest {

	private static final String PERSISTENCE_UNIT_NAME = "biblio";
	private EntityManagerFactory factory;

	private Livre li;
	
	@Before
	public void setUp() throws Exception {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("select l from Lecteur l");
		boolean createNewEntries = (q.getResultList().size() == 0);
		Bibliotheque bib = new Bibliotheque();
		if (createNewEntries) {
			assertTrue(q.getResultList().size() == 0);

			for (int i = 0; i < 40; i++) {
				Lecteur l = new Lecteur();
				l.setNom("Pruvost_" + i);
				l.setPrenom("Oceane_" + i);
				bib.addLecteur(l);
				em.persist(l);
				em.persist(bib);
			}

			for (int i = 0; i < 40; i++) {
				Livre livre = new Livre();
				livre.setDescription("Pruvost_" + i);
				livre.setTitre("Oceane_" + i);
				livre.setLecteur(bib.getLecteurs().get(0));
				bib.addLivre(livre);
				em.persist(livre);
				em.persist(bib);
			}
			for (int i = 0; i < 40; i++) {
				Roman roman = new Roman();
				roman.setDescription("Pruvost_" + i +"Roman");
				roman.setTitre("Oceane_" + i + "Roman");
				em.persist(roman);
			}
			for (int i = 0; i < 40; i++) {
				BD bd = new BD();
				bd.setDescription("Pruvost_" + i +"BD");
				bd.setTitre("Oceane_" + i + "BD");
				bd.setSerie("Tite Frappe");
				em.persist(bd);
			}

			for (int i = 0; i < 40; i++) {
				Auteur auteur= new Auteur();
				auteur.setNom("Oceane_" + i);
				auteur.setPrenom("Tite Frappe");
				auteur.addLivre(bib.getLivres().get(0));
				em.persist(auteur);
				em.persist(bib.getLivres().get(0));
				em.persist(bib);
				li = bib.getLivres().get(0);
			}
			System.out.println("Bib nb lecteur " + bib.getLecteurs().size());
		}
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void test() {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select l from Lecteur l");
		assertTrue(q.getResultList().size() == 40);
		em.close();
	}

	@Test
	public void checkLivre() {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select l from Livre l");
		assertTrue(q.getResultList().size() == 120);
		em.close();
	}

	@Test
	public void checkBdAndRoman() {
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("select l from Roman l");
		assertTrue(q.getResultList().size() == 40);
		em.close();

		em = factory.createEntityManager();
		q = em.createQuery("select l from BD l");
		assertTrue(q.getResultList().size() == 40);
	}

	@Test
	public void checkBib() {
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("select b from Bibliotheque b");

		assertTrue(q.getResultList().size() == 1);
		System.out.println("Nb Lecteur: "+((Bibliotheque)q.getSingleResult()).getLecteurs().size());
		assertTrue(((Bibliotheque) q.getSingleResult()).getLecteurs().size() == 40);
		assertTrue(((Bibliotheque) q.getSingleResult()).getLivres().size() == 40);
		em.close();
	}

	@Test
	public void checkAuteur() {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select b from Auteur b");
		System.out.println("Nb auteur total " + q.getResultList().size());
		assertTrue(q.getResultList().size() == 40);
		assertTrue(((Auteur)q.getResultList().get(0)).getLivres().size() == 1);
		q = em.createQuery("select b from Bibliotheque b");
		Bibliotheque b = (Bibliotheque) q.getSingleResult();
		System.out.println("Auteur Nb dans livre 0: "+(b.getLivres().get(0)).getAuteurs().size());
		System.out.println("Titre livre 0: "+(b.getLivres().get(0)).getTitre());
		assertTrue((b.getLivres().get(0)).getAuteurs().size() == 40);
		em.close();
		em = factory.createEntityManager();

	}

}
