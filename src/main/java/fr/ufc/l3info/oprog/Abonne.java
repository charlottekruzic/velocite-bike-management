package fr.ufc.l3info.oprog;

/**
 * Classe représentant un abonné au service VéloCité.
 */
public class Abonne {
    String nom;
    String rib;
    static Integer count = 0;
    Integer Id;
    boolean bloque;
    /**
     * Créé un abonné dont le nom est passé en paramètre, sans informations bancaires.
     *  Si le nom de l'abonné n'est pas correct (vide ou ne contenant pas des lettres éventuellementséparées par des espaces ou des traits d'union), le constructeur déclenchera l'exception IncorrectNameException.
     *  On notera que le nom peut contenir des espaces inutiles en début et en fin, mais ceux-ci seront retirés pour enregistrer cette donnée.
     * @param nom le nom du nouvel abonné.
     * @throws IncorrectNameException si le nom de l'abonné n'est pas correct.
     */
    public Abonne(String nom) throws IncorrectNameException {
        bloque=true;
        if(nom==null|| nom.length()==0){
            throw new IncorrectNameException();
        }
        nom = nom.trim();
        for (int i = 0; i < nom.length(); i++) {
            if ((!Character.isLetter(nom.charAt(i)) && !Character.isWhitespace(nom.charAt(i)) && nom.charAt(i)!='-')
                    || (nom.charAt(i)=='-' && i==nom.length()-1)
                    || (nom.charAt(i)=='-' && i==0)
                    || ((nom.charAt(i)==' ') && (nom.charAt(i+1)==' ') && i!=0)){
                throw new IncorrectNameException();
            }
        }

        this.nom=nom;
        count++;
        Id=count;
    }


    private boolean ribEstCorrect(String rib){
        //Vérifier qu'il n'y a que des chiffres et des tirets
        for(int i=0;i<rib.length();i++){
            if(rib.charAt(i)!='-' && rib.charAt(i)!='0' && rib.charAt(i)!='1' && rib.charAt(i)!='2' && rib.charAt(i)!='3' && rib.charAt(i)!='4'
                    && rib.charAt(i)!='5' && rib.charAt(i)!='6' && rib.charAt(i)!='7' && rib.charAt(i)!='8' && rib.charAt(i)!='9'){
                return false;
            }
        }

        String[] split = rib.split("-", 0);

        if(split.length!=4) {
            return false;
        }
        long codeBanque=Long.parseLong(split[0]);
        long codeGuichet=Long.parseLong(split[1]);
        long numCompte=Long.parseLong(split[2]);
        long cle=Long.parseLong(split[3]);
        long cleRib=97-((89*codeBanque+15*codeGuichet+3*numCompte) % 97);

        return cleRib==cle;
    }

    /**
     * Créé un abonné dont le nom est passé en paramètre, avec les informations bancaires spécifiées dans le second paramètre.
     *  Le comportement attendu est le même que celui du constructeur précédent. Le RIB n'est enregistré que si celui-ci est valide.
     * @param nom le nom du nouvel abonné.
     * @param rib le RIB
     * @throws IncorrectNameException si le nom de l'abonné n'est pas correct.
     */
    public Abonne(String nom, String rib) throws IncorrectNameException {
        this(nom);

        if(ribEstCorrect(rib)){
            bloque=false;
            this.rib=rib;
        }

    }


    /**
     * Renvoie l'identifiant de l'abonné, généré autoamtiquement à sa création.
     * @return l'identifiant de l'abonné.
     */
    public int getID() {
        return Id;
    }

    /**
     * Renvoie le nom de l'abonné.
     * @return le nom de l'abonné, sans les éventuels espace en début et en fin de chaîne.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met à jour l'ancien RIB pour un nouveau. Si le nouveau RIB n'est pas valide, l'abonné conserve ses anciennes coordonnées bancaires.
     * @param rib nouveau RIB pour la mise à jour.
     */
    public void miseAJourRIB(String rib) {
        if(ribEstCorrect(rib)){
            this.bloque=false;
            this.rib = rib;
        }
    }

    /**
     * Permet de bloquer volontairement un abonné.
     */
    public void bloquer() {
        this.bloque=true;
    }

    /**
     * Permet de débloquer un abonné.
     */
    public void debloquer() {
        if(rib!=null){
            this.bloque=false;
        }

    }

    /**
     * Vérifie si un abonné est bloqué. Celui-ci peut être bloqué volontairement ou parce que ses coordonnées bancaires sont invalides.
     * @return true si l'abonné est considéré comme bloqué, false sinon.
     */
    public boolean estBloque() {
        return this.bloque;
    }

    /**
     * permet de tester si deux abonnés sont identiques. Pour cela, on vérifiera si leur identifiant est le même.
     * @param a l'abonné avec lequel est comparé l'instance courante.
     * @return true si les deux objets ont le même ID, false sinon.
     */
    public boolean equals(Object a) {
        if(!(a instanceof Abonne)){
            return false;
        }
        Abonne other = (Abonne) a;
        return (this.getID()==other.getID());
    }

    /**
     * Utilisée en interne par Java pour obtenir un hash de l'objet. Cette méthode est utilisée pour les structures de collection de type HashSet ou HashMap.
     * @return le hash de l'instance courante.
     */
    public int hashCode() {
        int hash = 0;
        if (Id != 0) {
            hash = hash + Id.hashCode();
        }
        return hash;
    }

}

class IncorrectNameException extends Exception {
    public IncorrectNameException() {
        super("Le nom fourni n'est pas correct.");
    }
}

