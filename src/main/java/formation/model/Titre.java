package formation.model;

/**
 * Type Titre énuméré qui peut prendre trois valeurs :
 * - M
 * - MME
 * - MLLE
 * A chaque occurrence correspond une valeur de l'attribut label.
 * A M correspond "Monsieur"
 * A MME correspond "Madame"
 * A MLLE correspond "Mademoiselle"
 * 
 * @author Eric Sultan (sauf les commentaires)
 */
public enum Titre
{
    /*
     * appels de constructeurs avec un paramètre :
     * la valeur correspondante au code
     * M() => appel au constructeur
     * M("valeur") => appel le constructeur avec comme
     * paramètre la valeur "valeur"
     * Ca me permet de mettre en corrélation un code et une valeur
     */
    M("Monsieur"), MME("Madame"), MLLE("Mademoiselle");

    /**
     * la valeur correspondante au code. Elle est 'final' car elle
     * est en lecture-seule. Elle ne peut être intialisée qu'une seule
     * fois => à la construction de l'objet
     */
    private final String label;

    /**
     * Constructeur privé qui ne peut être appelé qu'à l'intérieur de
     * lui-même
     * 
     * @param label
     *            La valeur correspondante au code
     */
    private Titre(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }

    /**
     * Permet d'utiliser les labels correspondants aux valeurs constantes
     * 
     * @param name
     *            Le label à trouver
     * @return La constante correspondante au label
     */
    public static Titre permissiveValueOf(String name)
    {
        for (Titre e : values())
        {
            if (e.getLabel().equals(name))
            {
                return e;
            }
        }
        return null;
    }

}
