package formation.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import formation.model.Eleve;
import formation.model.Formateur;

public class EleveDaoFile implements EleveDao
{

    private final File path;

    public EleveDaoFile(String path)
    {
        super();
        this.path = new File(path);
    }

    public File getPath()
    {
        return path;
    }

    @Override
    public Eleve findById(Long id)
    {
        Eleve eleve = null;

        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String lineEleve;

            while ((lineEleve = br.readLine()) != null)
            {
                eleve = stringToEleve(lineEleve);

                if (id.equals(eleve.getId()))
                {
                    break;
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
                fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return eleve;
    }

    @Override
    public List<Eleve> findAll()
    {
        List<Eleve> eleves = new ArrayList<Eleve>();

        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String lineEleve;

            while ((lineEleve = br.readLine()) != null)
            {

                eleves.add(stringToEleve(lineEleve));

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
                fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return eleves;
    }

    @Override
    public void create(Eleve obj)
    {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try
        {
            fw = new FileWriter(path, true);
            bw = new BufferedWriter(fw);

            bw.write(eleveToString(obj));
            bw.newLine();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bw.close();
                fw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Eleve update(Eleve obj)
    {
        List<Eleve> eleves = findAll();
        int i;
        for (i = 0; i < eleves.size(); i++)
        {
            if (obj.getId() == eleves.get(i).getId())
            {
                break;
            }
        }

        eleves.set(i, obj);

        writeEleves(eleves);

        return obj;
    }

    @Override
    public void delete(Eleve obj)
    {
        List<Eleve> eleves = findAll();

        eleves.remove(obj);

        writeEleves(eleves);
    }

    private void writeEleves(List<Eleve> eleves)
    {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try
        {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();

            fw = new FileWriter(path);
            bw = new BufferedWriter(fw);

            for (Eleve eleve : eleves)
            {

                bw.write(eleveToString(eleve));
                bw.newLine();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bw.close();
                fw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private String eleveToString(Eleve eleve)
    {
        String lineEleve = eleve.getId() + ";";
        lineEleve += eleve.getNom() + ";";
        lineEleve += eleve.getPrenom() + ";";
        lineEleve += new SimpleDateFormat("dd/MM/yyyy")
                .format(eleve.getDtNais()) + ";";
        lineEleve += eleve.getNote();

        return lineEleve;
    }

    private Eleve stringToEleve(String line) throws ParseException
    {
        String[] tabEleve = line.split(";");

        long id = Long.parseLong(tabEleve[0]);

        String nom = tabEleve[1];
        String prenom = tabEleve[2];
        Date dtNais = new SimpleDateFormat("dd/MM/yyyy").parse(tabEleve[3]);
        float note = Float.parseFloat(tabEleve[4]);

        Eleve eleve = new Eleve(nom, prenom);
        eleve.setId(id);
        eleve.setDtNais(dtNais);
        eleve.setNote(note);

        return eleve;
    }

    @Override
    public List<Eleve> findByNote(float note)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Eleve> findByFormateur(Formateur formateur)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
