package fr.ufc.l3info.oprog;

public class  Option implements IVelo{
    IVelo _originale;
    double tarifOption;

    public Option(IVelo v, double t) {
        _originale = v;
        tarifOption = t;

    }

    /**
     * Indique le nombre de kilomètres total qu'a déjà parcouru le vélo.
     *
     * @return le kilométrage du vélo.
     */
    public double kilometrage() {
        return _originale.kilometrage();
    }

    /**
     * Indique le nombre de kilomètres qu'il reste au vélo avant la prochaine révision.
     * Une valeur négative ou nulle indiquera qu'il est temps d'effectuer la révision.
     *
     * @return le nombre de kilomètres avant la prochaine révision.
     */
    public double prochaineRevision() {
        return _originale.prochaineRevision();
    }

    /**
     * Fait parcourir au vélo en cours d'emprunt le nombre de kilomètres passé en paramètre.
     * Si le vélo n'est pas emprunté, cette méthode est sans effet.
     *
     * @param km le nombre de kilomètres parcourus.
     */
    public void parcourir(double km) {
        _originale.parcourir(km);
    }

    public double tarif() {
        return _originale.tarif() + tarifOption;
    }

    /**
     * Permet de décrocher le vélo de sa borne.
     *
     * @return 0 si le vélo a effectivement pu être décroché,
     * -1 si le vélo est déjà décroché.
     */
    public int decrocher() {
        return _originale.decrocher();
    }

    /**
     * Permet de raccrocher le vélo à une borne.
     *
     * @return 0 si le vélo a effectivement pu être accroché,
     * -1 si le vélo est déjà accroché.
     */
    public int arrimer() {
        return _originale.arrimer();
    }

    /**
     * Abime le vélo, quelque soit son état d'origine.
     */
    public void abimer() {
        _originale.abimer();
    }

    /**
     * Indique si le vélo est en bon état ou abimé.
     *
     * @return true si le vélo est abimé, false sinon.
     */
    public boolean estAbime() {
        return _originale.estAbime();
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
        return _originale.reviser();
    }

    /**
     * Permet de réparer un vélo. La réparation s'effectue sur un vélo abimé qui n'est pas accroché.
     *
     * @return 0 si le vélo a pu être réparé,
     * -1 si le vélo est accroché,
     * -2 si le vélo est décroché, mais qu'il n'est pas abimé.
     */
    public int reparer() {
        return _originale.reparer();
    }

    public String toString(){
        return _originale.toString();
    }


}
