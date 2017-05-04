package formation.dao;

import java.util.List;

import formation.model.Eleve;
import formation.model.Formateur;

/**
 * Contrat que les DAOs de l'objet métier Eleve vont devoir
 * respecter
 * Contexte :
 * - BO = Eleve
 * - PK = Long
 * @author Eric Sultan
 *
 */
public interface EleveDao extends Dao<Eleve, Long>{
	/**
	 * Retourne une liste d'élèves en fonction de leur note
	 * @param note Note de l'élève
	 * @return La liste des élèves obtenue grâce à leur note
	 */
	public List<Eleve> findByNote(float note);
	/**
	 * Retourne une liste d'élèves en fonction de leur formateur
	 * @param formateur Le formateur
	 * @return La liste des élèves de ce formateur
	 */
	public List<Eleve> findByFormateur(Formateur formateur);
}
