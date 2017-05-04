package formation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import formation.model.Formateur;
import formation.model.Titre;

public class FormateurDaoSql implements FormateurDao
{

    @Override
    public Formateur findById(Long id)
    {
        Formateur formateur = null;
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/formateur", "user", "");

            PreparedStatement ps = conn
                    .prepareStatement("select * from formateur where id=?");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                formateur = new Formateur();
                formateur.setId(rs.getLong("id"));
                formateur.setNom(rs.getString("nom"));
                formateur.setPrenom(rs.getString("prenom"));
                formateur.setDtNais(rs.getDate("dtnais"));
                formateur.setDtDebut(rs.getDate("dtdebut"));
                formateur.setDtFin(rs.getDate("dtFin"));
                formateur.setTitre(
                        Titre.permissiveValueOf(rs.getString("titre")));
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

        return formateur;
    }

    @Override
    public List<Formateur> findAll()
    {
        List<Formateur> formateurs = new ArrayList<Formateur>();
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/formateur", "user", "");

            PreparedStatement ps = conn
                    .prepareStatement("select * from formateur");

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Formateur formateur = new Formateur();
                formateur.setId(rs.getLong("id"));
                formateur.setNom(rs.getString("nom"));
                formateur.setPrenom(rs.getString("prenom"));
                formateur.setDtNais(rs.getDate("dtnais"));
                formateur.setDtDebut(rs.getDate("dtdebut"));
                formateur.setDtFin(rs.getDate("dtFin"));
                formateur.setTitre(
                        Titre.permissiveValueOf(rs.getString("titre")));
                formateurs.add(formateur);
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

        return formateurs;
    }

    @Override
    public void create(Formateur formateur)
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/formateur", "user", "");

            PreparedStatement ps = conn.prepareStatement(
                    "insert into formateur (id,nom,prenom,dtnais,dtdebut,dtfin,titre) VALUES(?,?,?,?,?,?,?)");
            ps.setLong(1, formateur.getId());
            ps.setString(2, formateur.getNom());
            ps.setString(3, formateur.getPrenom());
            ps.setDate(4, new java.sql.Date(formateur.getDtNais().getTime()));
            if (formateur.getDtDebut() != null)
            {
                ps.setDate(5,
                        new java.sql.Date(formateur.getDtDebut().getTime()));
            }
            else
            {
                ps.setNull(5, Types.DATE);
            }
            if (formateur.getDtFin() != null)
            {
                ps.setDate(6,
                        new java.sql.Date(formateur.getDtFin().getTime()));
            }
            else
            {
                ps.setNull(6, Types.DATE);
            }
            ps.setString(7, formateur.getTitre().getLabel());
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
    public Formateur update(Formateur formateur)
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/formateur", "user", "");

            PreparedStatement ps = conn.prepareStatement(
                    "update formateur set nom=?,prenom=?,dtnais=?,dtdebut=?,dtfin=?,titre=? where id = ?");

            ps.setLong(7, formateur.getId());

            ps.setString(1, formateur.getNom());
            ps.setString(2, formateur.getPrenom());
            ps.setDate(3, new java.sql.Date(formateur.getDtNais().getTime()));
            ps.setDate(4, new java.sql.Date(formateur.getDtDebut().getTime()));
            ps.setDate(5, new java.sql.Date(formateur.getDtFin().getTime()));
            ps.setString(6, formateur.getTitre().getLabel());
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

        return formateur;
    }

    @Override
    public void delete(Formateur eleve)
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/formateur", "user", "");

            PreparedStatement ps = conn
                    .prepareStatement("delete from formateur where id = ?");
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

}
