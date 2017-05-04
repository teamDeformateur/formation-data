package formation.model;

import java.util.Date;

public abstract class Personne {
	private long id = 0;
	private int version;
	private Titre titre;
	private String nom;
	private String prenom;
	private Date dtNais;

	public Personne() {
		super();
	}

	public Personne(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Titre getTitre() {
		return titre;
	}

	public void setTitre(Titre titre) {
		this.titre = titre;
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

	public Date getDtNais() {
		return dtNais;
	}

	public void setDtNais(Date dtNais) {
		this.dtNais = dtNais;
	}


}
