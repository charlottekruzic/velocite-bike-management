package fr.ufc.l3info.oprog;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClosestStationIterator implements Iterator {
    Set<Station> stations;
    Station initial;
    Station current;
    Set<Station> stations_non_visite;


    public ClosestStationIterator(Set<Station> stations, Station s){
        // si tous les attributs sont corrects
        if(stations!=null && s != null && stations.contains(s)){
            this.stations=stations;
            this.initial=s;
            this.current=null;
            this.stations_non_visite=stations;
        }else{
            this.stations=new HashSet<>();
            this.initial=null;
            this.current=null;
            this.stations_non_visite=new HashSet<>();
        }
    }

    public boolean hasNext(){
        if(next()==null) {
            return false;
        }else{
            return true;
        }
    }

    public Station next() {
        if (stations_non_visite.isEmpty()){
            return null;//exception
        }
        if (this.current == null) {
            this.current = initial;
        } else {
            double min = -1;
            double distance;
            current = this.initial;
            for (Station s : stations) {
                distance = current.distance(s);
                if (min == -1 && distance != 0) {
                    current = s;
                    min = distance;
                } else if (distance != 0 && distance < min) {
                    min = distance;
                    current = s;
                }
            }
        }
        stations_non_visite.remove(current);
        return current;
    }

    public void remove(){
        return;
    }
}
