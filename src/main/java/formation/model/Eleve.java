package formation.model;

public class Eleve extends Personne
{
    private float note;
    private Formateur formateur;

    public Eleve(String nom, String prenom)
    {
        super();
        setNom(nom);
        setPrenom(prenom);
    }

    public Eleve()
    {
        super();
    }

    public float getNote()
    {
        return note;
    }

    public void setNote(float note)
    {
        this.note = note;
    }

    public Formateur getFormateur()
    {
        return formateur;
    }

    public void setFormateur(Formateur formateur)
    {
        this.formateur = formateur;
    }

}
