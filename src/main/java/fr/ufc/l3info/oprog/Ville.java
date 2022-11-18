package fr.ufc.l3info.oprog;

import fr.ufc.l3info.oprog.parser.ASTNode;
import fr.ufc.l3info.oprog.parser.ASTStationBuilder;
import fr.ufc.l3info.oprog.parser.StationParser;
import fr.ufc.l3info.oprog.parser.StationParserException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Ville implements Iterable<Station>{
    private List<Station> stations;
    Ville(){
    }

    public void initialiser(File f) throws IOException {
        stations.clear();
        StationParser parser = StationParser.getInstance();
        try{
            ASTNode n = parser.parse(f);
        }catch (StationParserException e){
            throw new IOException();
        }
        ASTStationBuilder builder = new ASTStationBuilder();
        for (Station s : builder.getStations()) {
            stations.add(s);
        }
    }

    public void setStationPrincipale(String st){

    }

    public Station getStation(String nom){
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
