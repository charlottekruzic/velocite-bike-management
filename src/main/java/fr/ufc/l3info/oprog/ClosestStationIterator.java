package fr.ufc.l3info.oprog;

import java.util.Iterator;
import java.util.Set;

public class ClosestStationIterator implements Iterator {
    Set<Station>stations;
    Station initial;
    Station current;


    public ClosestStationIterator(Set<Station> stations, Station s){
        this.stations=stations;
        this.initial=s;
        this.current=null;
    }

    public boolean hasNext(){
        return getNext() != null;
    }

    public Station next(){
        return this.current = getNext();
    }

    public void remove(){
        return;
    }

    private Station getNext() {
        Station current=this.current;
        if(this.initial==null){
            return null;
        }
        if(this.stations==null){
            return null;
        }
        if(current==null){
            return this.initial;
        }
        if(stations.size()==0){
            return null;
        }
        int cpt=0;
        for(Station s: this.stations){
            while(s!=current){
                cpt++;
            }

        }
        return current;
    }

}
