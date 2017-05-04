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

        Formateur eric = new Formateur();
        eric.setId(3);
        eric.setNom("SULTAN");
        eric.setPrenom("Eric");
        eric.setTitre(Titre.M);

        // Exemple parse par SimpleDateFormat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dtNaisEric = simpleDateFormat.parse("04/01/1978");

        eric.setDtNais(dtNaisEric);

        // Exemple avec Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(1978, 1, 4);
        eric.setDtDebut(calendar.getTime());

        formateurDao.create(eric);

        String annee = new SimpleDateFormat("yyyy").format(dtNaisEric);

        Eleve emilie = new Eleve("DENG", "Emilie");
        emilie.setId(3);
        emilie.setDtNais(new Date());
        emilie.setNote(14.2F);
        emilie.setTitre(Titre.MLLE);
        emilie.setFormateur(eric);

        eleveDao.create(emilie);

        Eleve ph = new Eleve("LARBAT", "PH");
        ph.setId(5);
        ph.setDtNais(new Date());
        ph.setNote(15.7F);
        ph.setTitre(Titre.M);
        ph.setFormateur(eric);

        eleveDao.create(ph);

        Eleve sabine = new Eleve("FERREIRA", "Sabine");
        sabine.setId(4);
        sabine.setDtNais(new Date());
        sabine.setNote(15.7F);
        sabine.setTitre(Titre.MLLE);
        sabine.setFormateur(eric);

        eleveDao.create(sabine);

    }

}
