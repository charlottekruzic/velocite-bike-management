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
    Ville(){
        //stations=new HashMap<String,Station>();
        stations = new HashSet<Station>();
    }

    public void initialiser(File f) throws IOException {
        stations.clear();
        System.out.println("s : "+stations);
        StationParser parser = StationParser.getInstance();
        ASTNode n;
        try{
            n=parser.parse(f);
        }catch (StationParserException e){
            throw new IOException();
        }

        ASTStationBuilder builder = new ASTStationBuilder();

        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(builder);

        if(v.getErrors().size()!=0){
            throw new IOException();
        }

        //Ajout des stations à la ville
        int i=0;                                        //enleve
        for (Station s : builder.getStations()) {
            if(i==0){
                setStationPrincipale(s.getNom());       //enleve
            }
            System.out.println("nom : "+s.getNom());
            stations.add(s);
            i++;                                        //enleve
        }

        //Définition de la station principale

        //Enlever quand itérateur marchera              enleve


        //Remettre quand iterateur marchera

       /* if(!stations.isEmpty()) {
            setStationPrincipale(iterator().next().getNom());
        }*/
    }

    public void setStationPrincipale(String st){
        for(Station s : stations){
            if(s.getNom()==st){
                stationPrincipale = s;
                break;
            }
        }
    }

    public Station getStation(String nom){
        for(Station s : stations){
            if(s.getNom().equals(nom)){
                return s;
            }
        }
        return null;
    }

    public Station getStationPlusProche(double lat, double lon){
        return null;
    }

    public Abonne creerAbonne(String nom, String RIB){
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
        //ClosestStationIterator i = new ClosestStationIterator(stations, stationPrincipale);
        ClosestStationIterator i = new ClosestStationIterator(stations, stationPrincipale);
        return null;
    }

    public Map<Abonne, Double> facturation(int mois, int annee){
        return null;
    }



}
