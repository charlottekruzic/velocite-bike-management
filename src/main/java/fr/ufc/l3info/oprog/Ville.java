package fr.ufc.l3info.oprog;

import fr.ufc.l3info.oprog.parser.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Ville implements Iterable<Station>{
    //private Map<String,Station> stations;
    private Set<Station> stations;
    private Station stationPrincipale;
    private List<Abonne> abonnes_ville;

    private IRegistre registre_ville;

    Ville(){
        stations = new HashSet<>();
        registre_ville=new JRegistre();
    }

    public void initialiser(File f) throws IOException {
        stations.clear();
        StationParser parser = StationParser.getInstance();
        ASTNode n;
        try{
            n=parser.parse(f);
        }catch (StationParserException e){
            throw new IOException();
        }

        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        if(v.getErrors().size()!=0){
            throw new IOException();
        }

        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        //Ajout des stations à la ville
        int i=0;
        for (Station s : builder.getStations()) {
            //Définition de la station principale
            System.out.println("aa "+s.getNom());
            if(i==0){
                stationPrincipale=s;
            }
            stations.add(s);
            i++;
        }
    }

    public void setStationPrincipale(String st){
        for(Station s : stations){
            if(s.getNom().equals(st)){
                stationPrincipale = s;
                break;
            }
        }
    }

    public Station getStation(String nom){
        for(Station s : stations){

            if(s.getNom().equals(nom)){
                System.out.println(s.getNom());
                return s;
            }
        }
        return null;
    }

    public Station getStationPlusProche(double lat, double lon){
        Station station_donnee = new Station("Station inconnu", lat, lon, 0);
        Station station_plus_proche=stationPrincipale;
        System.out.println(stationPrincipale.getNom());
        for(Station s : stations){
            if(s.distance(station_donnee)<station_plus_proche.distance(station_donnee)){
                station_plus_proche=s;
            }
        }
        return station_plus_proche;
    }

    public Abonne creerAbonne(String nom, String RIB) {
        boolean err = false;
        Abonne new_abonne=null;
        try {
            new_abonne = new Abonne(nom, RIB);
        } catch (IncorrectNameException e) {
            err = true;
            throw new RuntimeException(e);
        }finally {
            if (err) {
                return null;
            }
            return new_abonne;
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<Station> iterator() {
        return new ClosestStationIterator(stations, stationPrincipale);
    }

    public Map<Abonne, Double> facturation(int mois, int annee){
       /* Map<Abonne, Double> facturation_abo = new HashMap<>();
        double deb_mois_ms;
        double fin_mois_ms;

        for (int i = 0; i < abonnes_ville.size(); i++) {
            Abonne abonne = abonnes_ville.get(i);
            double facture = 0.0;
            registre_ville.facturation(abonne,)




            facturation_abo.put(abonne, abonne.a);
        }*/
        return null;
    }



}
