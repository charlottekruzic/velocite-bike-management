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
        this.stations = new HashSet<>();
        this.registre_ville=new JRegistre();
        this.abonnes_ville = new ArrayList<>();
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
        Set<Station> b = builder.getStations();
        for (Station s : b) {
            //Définition de la station principale
            if(i==0){
                this.stationPrincipale=s;
            }
            stations.add(s);
            i++;
        }
    }

    public void setStationPrincipale(String st){
        for(Station s : stations){
            if(s.getNom().equals(st)){
                this.stationPrincipale = s;
                break;
            }
        }
    }

    public Station getStation(String nom){
        for(Station s : this.stations){

            if(s.getNom().equals(nom)){
                return s;
            }
        }
        return null;
    }

    public Station getStationPlusProche(double lat, double lon){
        Station station_donnee = new Station("Station inconnu", lat, lon, 0);
        Station station_plus_proche=stationPrincipale;
        for(Station s : this.stations){
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
            this.abonnes_ville.add(new_abonne);
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
        return new ClosestStationIterator(this.stations, this.stationPrincipale);
    }

    public Map<Abonne, Double> facturation(int mois, int annee){
        Map<Abonne, Double> facturations = new HashMap<>();
      /*  long debut_mois_depuis_1970;
        long fin_mois_depuis_1970;
        long aujourdhui_depuis_1970;


        Date d1 = new Date(annee, mois, 1, 0, 0, 0);
        System.out.println("Date = " + d1);
        System.out.println("Milliseconds since January 1, 1970, 00:00:00 GMT = " + d1.getTime());

        Date d2 = new Date(annee, mois, 31, 23, 59, 59);
        System.out.println("Date = " + d2);
        System.out.println("Milliseconds since January 1, 1970, 00:00:00 GMT = " + d2.getTime());


        Date d3 = new Date();
        aujourdhui_depuis_1970=d3.getTime();
        System.out.println("Date = " + d3);
        System.out.println("Milliseconds since January 1, 1970, 00:00:00 GMT = " + d3.getTime());





        //Si valeurs mauvaises
        if(mois<1 || mois>12){
            return facturations;
        }

        //calcule pour tous les abonnés de la ville
        for (Abonne a : abonnes_ville) {
        }




*/

        return facturations;
    }



}
