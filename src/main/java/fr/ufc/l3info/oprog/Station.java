package fr.ufc.l3info.oprog;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import static java.lang.Math.*;

public class Station{
     String nom;
    double latitude;
    double longitude;
    int capacite;

    IRegistre registre;

    IVelo [] bornes;

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
        if(registre==null){
                return null;
        }
        if(registre.nbEmpruntsEnCours(a)!=0){
            return null;
        }
        if(a==null || a.estBloque()==true){
            return null;
        }
        if(b>capacite || b<1){
            return null;
        }
        registre.emprunter(a, bornes[b], maintenant() );
        return bornes[b];
    }

    public int arrimerVelo(IVelo v, int b){
        if(registre==null){
            return -2;
        }
        if(v==null){
            return -1;
        }
        if(b>capacite || b<1){
            return -1;
        }
        if(bornes[b]!=null){
            return -2;
        }

        int retour =this.registre.retourner(v,maintenant());
        bornes[b]=v;
        if(retour!=0){
            return -4;
        }


        return 0;
    }
    public void equilibrer(Set<IVelo> velos) {
        /*int nb=nbBornesLibres();
        double moitier=(capacite/2)+1;

        int i = 0;
        while (nb > moitier && i<capacite) {
            if (bornes[i] != null) {
                bornes[i] = null;
            }

            i++;

        }
        while ( nb < moitier) {
            Iterator<IVelo> it = velos.iterator();

                while (it.hasNext()) {
                    for(int j=0;j<capacite;j++){
                    if (bornes[j] == null) {
                        System.out.println(bornes[i]);
                        bornes[j] = it.next();
                    }
                }

            }
        }*/

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
