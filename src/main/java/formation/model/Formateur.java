package formation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Formateur extends Personne {
	private Date dtDebut;
	private Date dtFin;
	private List<Eleve> eleves = new ArrayList<Eleve>();

	public Date getDtDebut() {
		return dtDebut;
	}

	public void setDtDebut(Date dtDebut) {
		this.dtDebut = dtDebut;
	}

	public Date getDtFin() {
		return dtFin;
	}

	public void setDtFin(Date dtFin) {
		this.dtFin = dtFin;
	}

	public List<Eleve> getEleves() {
		return eleves;
	}

	public void setEleves(List<Eleve> eleves) {
		this.eleves = eleves;
	}

	public boolean addEleve(Eleve eleve) {
		return eleves.add(eleve);
	}
}
