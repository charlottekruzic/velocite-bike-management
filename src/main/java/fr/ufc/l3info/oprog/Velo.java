package fr.ufc.l3info.oprog;

public class Velo implements IVelo {
    char type;
    double kilometre;
    double kilometre_restant=500;
    double tarif;



    boolean decroche=false;
    boolean abime=false;
    /**
     * Construit un vélo avec un cadre de type mixte.
     */
    public Velo()  {
        this.type='m';
        this.kilometre=0;
        this.decroche=true;
        this.abime=false;
    }

    /**
     * Construit un vélo avec un type de cadre spécifié en paramètre
     */
    public Velo(char t)  {

        if((t=='f'|| t=='F' || t=='h' || t=='H')){
            this.type=t;
        }else{
            this.type='m';
            this.kilometre=0.0;
            this.decroche=true;

        }

        this.kilometre=0;
        this.decroche=true;



    }
    /**
     * Indique le nombre de kilomètres total qu'a déjà parcouru le vélo.
     *
     * @return le kilométrage du vélo.
     */
    public double kilometrage() {
        return this.kilometre;
    }

    /**
     * Indique le nombre de kilomètres qu'il reste au vélo avant la prochaine révision.
     * Une valeur négative ou nulle indiquera qu'il est temps d'effectuer la révision.
     *
     * @return le nombre de kilomètres avant la prochaine révision.
     */
    public double prochaineRevision() {
        double km_restant=kilometre_restant-this.kilometre;
        if(km_restant==0){

            return 0;
        }
        return km_restant;
    }

    /**
     * Fait parcourir au vélo en cours d'emprunt le nombre de kilomètres passé en paramètre.
     * Si le vélo n'est pas emprunté, cette méthode est sans effet.
     *
     * @param km le nombre de kilomètres parcourus.
     */
    public void parcourir(double km) {
        if(km>0) {
            if (this.decroche) {
                this.kilometre = this.kilometre + km;
            }
        }

    }

    /**
     * Tarif horaire pour le vélo.
     *
     * @return Le tarif de location pour une heure de vélo (positif ou nul)
     */
    public double tarif() {
        tarif=2.0;
        return this.tarif;
    }

    /**
     * Permet de décrocher le vélo de sa borne.
     *
     * @return 0 si le vélo a effectivement pu être décroché,
     * -1 si le vélo est déjà décroché.
     */
    public int decrocher() {
        if(this.decroche==true){
            return -1;
        }
        this.decroche=true;
        return 0;
    }

    /**
     * Permet de raccrocher le vélo à une borne.
     *
     * @return 0 si le vélo a effectivement pu être accroché,
     * -1 si le vélo est déjà accroché.
     */
    public int arrimer() {
        if(this.decroche==false){
            return -1;
        }
        this.decroche=false;
        return 0;
    }

    /**
     * Abime le vélo, quelque soit son état d'origine.
     */
    public void abimer() {
        this.abime=true;

    }

    /**
     * Indique si le vélo est en bon état ou abimé.
     *
     * @return true si le vélo est abimé, false sinon.
     */
    public boolean estAbime() {
        if(this.abime==true){
            return true;
        }
        return false;
    }

    /**
     * Permet de réviser le vélo lorsque celui-ci est décroché. Cette action a pour effet
     * de réinitialiser le décompte des kilomètres à parcourir avant la prochaine révision.
     * Si le vélo était abimé, la révision a pour effet de le réparer.
     *
     * @return 0 si la révision a pu être effectuée,
     * -1 sinon (le vélo est encore accroché).
     */
    public int reviser() {
        if(this.decroche==true) {
            this.kilometre_restant=500.0;
            this.kilometre=0;
            if (estAbime() == true){
                reparer();
            }

        return 0;
        }
        return -1;
    }

    /**
     * Permet de réparer un vélo. La réparation s'effectue sur un vélo abimé qui n'est pas accroché.
     *
     * @return 0 si le vélo a pu être réparé,
     * -1 si le vélo est accroché,
     * -2 si le vélo est décroché, mais qu'il n'est pas abimé.
     */
    public int reparer() {
        if(this.decroche==true){
            if(estAbime() == true) {
                this.abime=false;
                return 0;
            }
            return -2;
        }
        return -1;
    }

    /**
     * Génère une chaîne de caractères décrivant le vélo.
     * @return une chaîne décrivant le vélo.
     */
    public String toString(){
        String new_type=" ";
        switch (this.type){
            case 'm':
                new_type="mixte";
                break;
            case 'f':
                new_type="femme";
                break;
            case 'F':
                new_type="femme";
                break;
            case 'h':
                new_type="homme";
                break;
            case 'H':
                new_type="homme";
                break;

        }
        double new_kilometre =(double)Math.round(this.kilometre * 10d) / 10d;
        String res;
        if (prochaineRevision()<=0){
            res="Vélo cadre "+new_type+ " - "+ new_kilometre+" km (révision nécessaire)";
        }else{
           res= "Vélo cadre "+new_type+ " - "+ new_kilometre+" km";
        }
        return res;

    }
}
