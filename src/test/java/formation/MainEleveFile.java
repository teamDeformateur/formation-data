package formation;

import java.text.ParseException;
import java.util.Date;

import formation.dao.EleveDao;
import formation.dao.EleveDaoFile;
import formation.model.Eleve;

public class MainEleveFile {
	
	public static void main(String[] args) throws ParseException {
		EleveDao eleveDao = new EleveDaoFile("c:/eleves.txt");
		
		Eleve marie = new Eleve("VINH", "Marie");
		marie.setId(15L);
		marie.setDtNais(new Date());
		marie.setNote(12.5F);
		
		eleveDao.create(marie);
	
	
	}
}
