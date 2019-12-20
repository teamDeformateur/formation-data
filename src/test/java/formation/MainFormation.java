package formation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import formation.dao.EleveDao;
import formation.dao.EleveDaoSql;
import formation.dao.FormateurDao;
import formation.dao.FormateurDaoSql;
import formation.model.Eleve;
import formation.model.Formateur;
import formation.model.Titre;

public class MainFormation
{

    public static void main(String[] args) throws ParseException
    {
        EleveDao eleveDao = new EleveDaoSql();
        FormateurDao formateurDao = new FormateurDaoSql();

        Formateur adolf = new Formateur();
        adolf.setId(3);
        adolf.setNom("BRUEL");
        adolf.setPrenom("Adolf");
        adolf.setTitre(Titre.M);

        // Exemple parse par SimpleDateFormat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dtNais = simpleDateFormat.parse("04/01/1978");

        adolf.setDtNais(dtNais);

        // Exemple avec Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(1978, 1, 4);
        adolf.setDtDebut(calendar.getTime());

        formateurDao.create(adolf);

        String annee = new SimpleDateFormat("yyyy").format(dtNais);

        Eleve emilie = new Eleve("DENG", "Emilie");
        emilie.setId(3);
        emilie.setDtNais(new Date());
        emilie.setNote(14.2F);
        emilie.setTitre(Titre.MLLE);
        emilie.setFormateur(adolf);

        eleveDao.create(emilie);

        Eleve ph = new Eleve("LARBAT", "PH");
        ph.setId(5);
        ph.setDtNais(new Date());
        ph.setNote(15.7F);
        ph.setTitre(Titre.M);
        ph.setFormateur(adolf);

        eleveDao.create(ph);

        Eleve sabine = new Eleve("FERREIRA", "Sabine");
        sabine.setId(4);
        sabine.setDtNais(new Date());
        sabine.setNote(15.7F);
        sabine.setTitre(Titre.MLLE);
        sabine.setFormateur(adolf);

        eleveDao.create(sabine);

    }

}
