package formation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import formation.model.Eleve;
import formation.model.Formateur;
import formation.model.Titre;

/**
 * Implémentation de l'interface EleveDao Son rôle : de persister les objets
 * métiers Eleve à l'aide du SQL et au moyen d'une BDD
 * 
 * @author Eric Sultan
 */
public class EleveDaoSql implements EleveDao
{
    /*
     * (non-Javadoc)
     * @see formation.dao.Dao#findById(java.lang.Object)
     */
    @Override
    public Eleve findById(Long id)
    {
        Eleve eleve = null;
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/formateur", "user", "password");

            PreparedStatement ps = conn
                    .prepareStatement("select * from eleve where id=?");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                // MAJ de ses attributs propres
                eleve = new Eleve(rs.getString("NOM"), rs.getString("PRENOM"));
                eleve.setId(rs.getLong("ID"));
                eleve.setDtNais(rs.getDate("DTNAIS"));
                eleve.setNote(rs.getFloat("NOTE"));
                eleve.setTitre(Titre.permissiveValueOf(rs.getString("TITRE")));
                // MAJ le lien entre l'élève et le formateur
                // on va chercher le formateur grâce à son DAO
                FormateurDao formateurDao = new FormateurDaoSql();
                Formateur formateur = formateurDao
                        .findById(rs.getLong("formateur_id"));
                // liaison à l'élève
                eleve.setFormateur(formateur);
            }

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return eleve;
    }

    /*
     * (non-Javadoc)
     * @see formation.dao.Dao#findAll()
     */
    @Override
    public List<Eleve> findAll()
    {
        // La liste des élèves que l'on va retourner
        List<Eleve> listeEleves = new ArrayList<Eleve>();
        // Connexion à la BDD
        Connection connexion = null;
        /*
         * ------------------------- + Connexion à la BDD +
         * -------------------------
         */
        try
        {
            // 1. Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            // 2. Créer la connexion à la BDD
            connexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/formateur", "user", "password");
            // 3. Création de la requête (statement)
            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * FROM eleve");
            // 4. On exécute la requête
            ResultSet tuple = ps.executeQuery();
            // 5. Parcours de l'ensemble des résultats (ResultSet)
            while (tuple.next())
            {
                // je récupère les valeurs des colonnes qui correspondent
                // aux valeurs des attributs de l'objet
                String nom = tuple.getString("nom");
                String prenom = tuple.getString("prenom");
                // Création d'un objet Elève
                Eleve eleve = new Eleve(nom, prenom);
                // MAJ des attributs propres à l'élève
                eleve.setId(tuple.getLong("id"));
                eleve.setDtNais(tuple.getDate("dtnais"));
                eleve.setNote(tuple.getFloat("note"));
                // Pour récupérer la référence du type énuméré correspondant
                // à la chaîne de caractères, on utilise Titre.valueOf
                eleve.setTitre(
                        Titre.permissiveValueOf(tuple.getString("TITRE")));

                // MAJ le lien entre l'élève et le formateur
                FormateurDao formateurDao = new FormateurDaoSql();
                // je récupère l'objet métier correspondant, en l'occurrence, le
                // Formateur
                Formateur formateur = formateurDao
                        .findById(tuple.getLong("formateur_id"));
                // je lie le formateur à l'élève
                eleve.setFormateur(formateur);
                // on est content ! ^^ :D =) :) (rofl)

                // je l'ajoute à la liste des élèves
                listeEleves.add(eleve);
            } // fin de la boucle de parcours de l'ensemble des résultats
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // this block will be executed in any case
        finally
        {
            try
            {
                // we're closing the database connection because if we're not,
                // resource leak and potetial security disaster
                connexion.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // we return the student list : alleluia
        return listeEleves;
    }

    /*
     * (non-Javadoc)
     * @see formation.dao.Dao#create(java.lang.Object)
     */
    @Override
    public void create(Eleve eleve)
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/formateur", "user", "password");
            // Créer ma requête d'insertion INSERT INTO
            PreparedStatement requete;
            // je teste si l'élève est lié à un formateur
            if (eleve.getFormateur() == null)
            {
                requete = conn.prepareStatement(
                        "insert into eleve (nom,prenom,dtnais,note,titre)"
                                + " VALUES(?,?,?,?,?)");

            }
            else
            {
                requete = conn.prepareStatement(
                        "insert into eleve (nom,prenom,dtnais,note,titre,formateur_id)"
                                + " VALUES(?,?,?,?,?,?)");
                requete.setLong(6, eleve.getFormateur().getId());
            }

            // requete.setLong(1, eleve.getId());
            requete.setString(1, eleve.getNom());
            requete.setString(2, eleve.getPrenom());
            // Je convertis une java.util.Date en java.sql.Date
            requete.setDate(3, new java.sql.Date(eleve.getDtNais().getTime()));
            requete.setFloat(4, eleve.getNote());
            // on insère le label du type énuméré
            requete.setString(5, eleve.getTitre().getLabel());

            // je l'exécute
            requete.executeUpdate();

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see formation.dao.Dao#update(java.lang.Object)
     */
    @Override
    public Eleve update(Eleve eleve)
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/formateur", "user", "password");

            PreparedStatement ps = conn.prepareStatement(
                    "update eleve set nom=?,prenom=?,dtnais=?,note=?,titre=? where id = ?");

            ps.setLong(6, eleve.getId());

            ps.setString(1, eleve.getNom());
            ps.setString(2, eleve.getPrenom());
            ps.setDate(3, new java.sql.Date(eleve.getDtNais().getTime()));
            ps.setFloat(4, eleve.getNote());
            ps.setString(5, eleve.getTitre().getLabel());

            ps.executeUpdate();

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return eleve;
    }

    @Override
    public void delete(Eleve eleve)
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/formateur", "user", "password");

            PreparedStatement ps = conn
                    .prepareStatement("delete from eleve where id = ?");
            ps.setLong(1, eleve.getId());

            ps.executeUpdate();

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
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
