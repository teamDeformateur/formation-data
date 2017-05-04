/**
 * 
 */
package formation;

import java.util.Date;
import java.util.List;

import formation.dao.EleveDao;
import formation.dao.EleveDaoSql;
import formation.model.Eleve;
import formation.model.Titre;

/**
 * @author Seme
 *
 */
public class MainEleve
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // J'instancie le dao qui va récupérer à ma place les données
        // dans la BDD
        EleveDao eleveDao = new EleveDaoSql();
        // Ma liste d'élèves qui va contenir la liste de résultats
        List<Eleve> listeDeleves;
        // je demande au DAO de me trouver tous les élèves de la BDD
        listeDeleves = eleveDao.findAll();  
        // mettre à jour l'élève n°3, je change sa note
        Eleve eleveAChanger = eleveDao.findById(3L);
        // je mets à jour au niveau de l'OBJET la note
        eleveAChanger.setNote(20.0f);
        // je mets à jour au niveau BDD !!
        Eleve eleveMisAJour = eleveDao.update(eleveAChanger);
        // Je supprime l'élève n°5
        Eleve eleveASupprimer = eleveDao.findById(5L);
        eleveDao.delete(eleveASupprimer);
        

    }

}
