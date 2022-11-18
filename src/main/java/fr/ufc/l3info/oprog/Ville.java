package fr.ufc.l3info.oprog;

import fr.ufc.l3info.oprog.parser.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Ville implements Iterable<Station>{
    private Map<String,Station> stations;
    private String stationPrinsipale;
    Ville(){
        stations=new HashMap<String,Station>();
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
        for (Station s : builder.getStations()) {
            stations.put(s.nom,s);
        }
        Iterator<String> i = stations.keySet().iterator();
        String key = null;
        if(i.hasNext()){
            key = i.next();
        }
        setStationPrincipale(key);
    }

    public void setStationPrincipale(String st){
        if(getStation(st)!=null){
            stationPrinsipale = st;
        }
    }

    public Station getStation(String nom){
        Iterator<String> i = stations.keySet().iterator();
        String key = null;
        if(i.hasNext()){
            key = i.next();
            Station station = stations.get(key);
            return station;
        }
        return null;
    }

    public Station getStationPlusProche(double lat, double lon){
        return null;
    }

    public Abonne creerAbonne(String nom, String RIB){
        return null;
    }

    public Map<Abonne, Double> facturation(int mois, int annee){
        return null;
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<Station> iterator() {
        return null;
    }
}
