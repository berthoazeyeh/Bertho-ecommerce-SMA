package models;

import jade.util.leap.Serializable;

public class Livre implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Attributs
	private String titre;
	private String auteur;
	private int anneePublication;
	private String isbn;

	// Constructeur
	public Livre(String titre, String auteur, int anneePublication, String isbn) {
		this.titre = titre;
		this.auteur = auteur;
		this.anneePublication = anneePublication;
		this.isbn = isbn;
	}

	// Getters et Setters
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public int getAnneePublication() {
		return anneePublication;
	}

	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	// Méthode pour afficher les informations du livre
	public void afficherInfo() {
		System.out.println("Titre: " + titre);
		System.out.println("Auteur: " + auteur);
		System.out.println("Année de publication: " + anneePublication);
		System.out.println("ISBN: " + isbn);
	}

	// Exemple d'utilisation
	public static void main(String[] args) {
		// Créer une instance de Livre
		Livre livre = new Livre("Harry Potter", "J.K. Rowling", 1997, "9780545582889");

		// Afficher les informations du livre
		livre.afficherInfo();
	}
}
