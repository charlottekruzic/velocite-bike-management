package fr.ufc.l3info.oprog;



import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class ClosestStationIterator implements Iterator {
    private Set<Station> stations;
    private Station initial;
    private Station current;
    private Set<Station> stations_non_visite;


    public ClosestStationIterator(Set<Station> stations, Station s){
        // si tous les attributs sont corrects
        if(stations!=null && s != null && stations.contains(s)){
            this.stations=new HashSet<>(stations);
            this.initial=s;
            this.current=null;
            this.stations_non_visite=new HashSet<>(stations);
        }else{
            this.stations=new HashSet<>();
            this.initial=null;
            this.current=null;
            this.stations_non_visite=new HashSet<>();
        }
    }

    public boolean hasNext(){
        if(this.stations_non_visite.isEmpty()) {
            return false;
        }else{
            return true;
        }
    }

    public Station next() {
        if (stations_non_visite.isEmpty()){
            //throw new ArrayIndexOutOfBoundsException("No more stations");
            return null;//exception
        }
        if (this.current == null) {
            this.current = initial;
        } else {
            double min = -1;
            double distance;
            Station s_plus_proche = current;
            for (Station s : this.stations_non_visite) {
                distance = current.distance(s);
                if ((min == -1 && distance != 0) || (distance != 0 && distance < min)) {
                    s_plus_proche = s;
                    min = distance;
                }

            }
            current = s_plus_proche;
        }

        this.stations_non_visite.remove(current);
        return current;
    }

    public void remove(){
        return;
    }
}
