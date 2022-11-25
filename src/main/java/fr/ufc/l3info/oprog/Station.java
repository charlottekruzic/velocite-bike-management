package fr.ufc.l3info.oprog;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import static java.lang.Math.*;

public class Station{
    private String nom;
    private double latitude;
    private double longitude;
    private int capacite;
    private IRegistre registre;
    private IVelo [] bornes;

    public Station(String nom, double latitude, double longitude, int capacite){
        this.nom=nom;
        this.latitude=latitude;
        this.longitude=longitude;
        if(capacite<0){
            this.capacite=0;
            this.bornes=new IVelo[0];
        }else {
            this.capacite=capacite;
            this.bornes = new IVelo[capacite];
        }
    }

    public void setRegistre(IRegistre registre) {
        this.registre=registre;

    }

    public String getNom(){
        return nom;
    }
    public int capacite(){
        return capacite;
    }
    public int nbBornesLibres(){
        int count=0;

        for (int i=0; i <bornes.length; i++) {
            if(bornes[i]==null){
                count+=1;
            }
        }
        return count;
    }

    public IVelo veloALaBorne(int b){
        if(b>capacite || b<1){
            return null;
        }
        if(bornes[b]==null){
            return null;
        }
        return  bornes[b];
    }

    public IVelo emprunterVelo(Abonne a, int b){
        if(b>capacite || b<1){
            return null;
        }
        if(bornes[b]==null){
            return null;
        }
        if(bornes[b].estAbime()){
            return null;
        }
        if(a==null || a.estBloque()==true){
            return null;
        }
        if(registre==null){
            return null;
        }
        if(registre.nbEmpruntsEnCours(a)!=0){
            return null;
        }
        IVelo v=bornes[b];
        registre.emprunter(a, v, maintenant() );
        v.decrocher();
        bornes[b]=null;
        return v;
    }

    public int arrimerVelo(IVelo v, int b){
        if(b>capacite || b<1){
            return -1;
        }
        if(v==null){
            return -1;
        }
        if(bornes[b]!=null){
            return -2;
        }
        if(registre==null){
            return -2;
        }
        if (v.arrimer()==-1){
            return -3;
        }
        bornes[b]=v;
        int retour =this.registre.retourner(v,maintenant());

        if(retour!=0){
            return -4;
        }
        return 0;
    }

    public void equilibrer(Set<IVelo> velos) {
        Object[] velos_remplacement= velos.toArray();
        int nb_velo_remplacement=velos_remplacement.length;

        //Ajouter des vélos pour compléter les bornes libres
        int nb_velo_max=this.capacite/2;
        //Cherche un vélo disponible dans le stock
        for(int i=0;i<nb_velo_remplacement;i++) {
            IVelo v = (IVelo) velos_remplacement[i];
            //Vérifie que la station n'est pas déjà assez remplie
            if (nbBornesLibres() == capacite-nb_velo_max) {
                break;
            }
            // Cherche une borne disponible pour accrocher le vélo
            int j;
            for (j = 0; this.bornes[j] != null && j < nb_velo_max; j++) ;
            if (i < this.capacite) {
                v.arrimer();
                this.bornes[j] = v;
                velos.remove(v);
                velos_remplacement[i]=null;
            }
        }

        //Retirer les vélos abimés et les remplacer si possible
        //Parcours des bornes pour trouver les vélos abimés
        for(int i=0;i<this.capacite;i++) {
            if (this.bornes[i] != null && this.bornes[i].estAbime()) {
                IVelo velo_abime=this.bornes[i];
                //Recherche d'un vélo disponible dans le stock
                for(int j=0;j<nb_velo_remplacement;j++) {
                    IVelo v = (IVelo) velos_remplacement[j];
                    //Remplacement
                    if (v!=null && !v.estAbime()) {
                        this.bornes[i] = v;
                        velos.remove(v);
                        velos_remplacement[j]=null;
                        break;
                    }
                }
                if(this.bornes[i].estAbime()){
                    this.bornes[i]=null;
                }
                velo_abime.decrocher();
                velos.add(velo_abime);
            }
        }

        //Retirer et remplacer les vélos à réviser si possible
        //Parcours des bornes pour trouver les vélos à réviser
        for(int i=0;i<this.capacite;i++) {
            if (this.bornes[i] != null && this.bornes[i].kilometrage() >= 500) {
                IVelo velo_a_reviser=this.bornes[i];
                //Recherche s'il y a des vélos en stocks
                for(int j=0;j<nb_velo_remplacement;j++) {
                    IVelo v = (IVelo) velos_remplacement[j];
                    if (v!=null && v.kilometrage() < 500) {
                        this.bornes[i] = v;
                        velos.remove(v);
                        velos_remplacement[j]=null;
                        velo_a_reviser.decrocher();
                        velos.add(velo_a_reviser);
                        break;
                    }
                }
            }
        }
    }

    public double distance(Station s){
        double a=sin((s.latitude-latitude)/2)*sin((s.latitude-latitude)/2) + cos(latitude)*cos(s.latitude)*sin((s.longitude-longitude)/2)*sin((s.longitude-longitude)/2);
        double c=2*atan2(sqrt(a),sqrt(1-a));
        double d= 6371 * c;
        return d;
    }

    public long maintenant(){
        return System.currentTimeMillis();
    }
}
