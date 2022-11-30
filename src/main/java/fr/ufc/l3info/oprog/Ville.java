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
        ASTNode n = null;//premier fils -> premier fils

        String nom_station_1 = null;
        boolean trouve = false;
        try{
            n=parser.parse(f);
        }catch (StationParserException e){
            trouve = true;
            throw new IOException();
        }finally {
            if(trouve==false){
                //Définition de la station principale
                String nom_station_guillemet= String.valueOf(n.getChild(0).getChild(0));
                nom_station_1=nom_station_guillemet.substring(1,nom_station_guillemet.length()-1);
            }
        }

        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        if(v.getErrors().size()!=0){
            throw new IOException();
        }

        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        //Ajout des stations à la ville
        Set<Station> b = builder.getStations();
        for (Station s : b) {
            s.setRegistre(this.registre_ville);
            stations.add(s);
            if(s.getNom().equals(nom_station_1)){
                this.stationPrincipale=s;
            }
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
        Station station_donnee = new Station("Station inconnu", lat, lon, 2);
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

        //Si valeurs mauvaises
        if(mois<1 || mois>12){
            return facturations;
        }

        //Premier jour
        Calendar debut_mois = Calendar.getInstance();
        debut_mois.set(annee, mois, 1, 0,0,0);
        long debut_mois_ms = debut_mois.getTimeInMillis();

        //Dernier jour
        if(mois==12){
            mois=1;
            annee=annee+1;
        }else{
            mois=mois+1;
        }

        Calendar fin_mois = Calendar.getInstance();
        fin_mois.set(annee, mois, 1, 0,0,0);
        long fin_mois_ms = fin_mois.getTimeInMillis()-1;



        //calcule pour tous les abonnés de la ville
        for (Abonne a : abonnes_ville) {
            facturations.put(a, registre_ville.facturation(a, debut_mois_ms, fin_mois_ms));
        }

        return facturations;
    }



}
