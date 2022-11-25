package fr.ufc.l3info.oprog;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class ClosestStationIteratorTest {
    final String path = "./target/classes/data/";
    @Test
    public void testHasNextTrue() {
        Station s1 = new Station("station1",1.5178,2.4523,5);
        Station s2 = new Station("station2",1.5179,2.4523,5);
        Station s3 = new Station("station3",1.5174,2.4523,5);
        Station s4 = new Station("station4",1.5171,2.4523,5);
        Set<Station> stations = new HashSet<>();
        stations.add(s1);
        stations.add(s2);
        stations.add(s3);
        stations.add(s4);
        ClosestStationIterator sI = new ClosestStationIterator(stations,s1);
        assertTrue(sI.hasNext());
    }
    @Test
    public void testHasNextFalse() {
        Station s1 = new Station("station1",1.5178,2.4523,5);
        Station s2 = new Station("station2",1.5179,2.4523,5);
        Station s3 = new Station("station3",1.5174,2.4523,5);
        Station s4 = new Station("station4",1.5171,2.4523,5);
        Set<Station> stations = new HashSet<>();
        stations.add(s1);
        stations.add(s2);
        stations.add(s3);
        stations.add(s4);
        ClosestStationIterator sI = new ClosestStationIterator(stations,s4);
        assertFalse(sI.hasNext());
    }
    @Test
    public void testHasNextStationDepartNull() {
        Station s1 = new Station("station1",1.5178,2.4523,5);
        Station s2 = new Station("station2",1.5179,2.4523,5);
        Station s3 = new Station("station3",1.5174,2.4523,5);
        Station s4 = new Station("station4",1.5171,2.4523,5);
        Set<Station> stations = new HashSet<>();
        stations.add(s2);
        stations.add(s3);
        stations.add(s4);
        ClosestStationIterator sI = new ClosestStationIterator(stations,null);
        assertFalse(sI.hasNext());
    }
    @Test
    public void testHasNextStationDepartPasDansSet() {
        Station s1 = new Station("station1",1.5178,2.4523,5);
        Station s2 = new Station("station2",1.5179,2.4523,5);
        Station s3 = new Station("station3",1.5174,2.4523,5);
        Station s4 = new Station("station4",1.5171,2.4523,5);
        Set<Station> stations = new HashSet<>();
        stations.add(s2);
        stations.add(s3);
        stations.add(s4);
        ClosestStationIterator sI = new ClosestStationIterator(stations,s1);
        assertFalse(sI.hasNext());
    }
    @Test
    public void testHasNextSetNull() {
        Station s1 = new Station("station1",1.5178,2.4523,5);
        ClosestStationIterator sI = new ClosestStationIterator(null,s1);
        assertFalse(sI.hasNext());
    }
    @Test
    public void testHasNextSetVide() {
        Station s1 = new Station("station1",1.5178,2.4523,5);
        Set<Station> stations = new HashSet<>();
        ClosestStationIterator sI = new ClosestStationIterator(stations,s1);
        assertFalse(sI.hasNext());
    }
    @Test
    public void testHasNext1Station() {
        Station s1 = new Station("station1",1.5178,2.4523,5);
        Set<Station> stations = new HashSet<>();
        stations.add(s1);
        ClosestStationIterator sI = new ClosestStationIterator(stations,s1);
        assertFalse(sI.hasNext());
    }
    @Test
    public void testNextTrue() {
        Station s1 = new Station("station1",1.5178,2.4523,5);
        Station s2 = new Station("station2",1.5179,2.4523,5);
        Station s3 = new Station("station3",1.5174,2.4523,5);
        Station s4 = new Station("station4",1.5171,2.4523,5);
        Set<Station> stations = new HashSet<>();
        stations.add(s1);
        stations.add(s2);
        stations.add(s3);
        stations.add(s4);
        ClosestStationIterator sI = new ClosestStationIterator(stations,s1);
        assertEquals("station2",sI.next().getNom());
    }
    @Test
    public void testNextFalse() {
        Station s1 = new Station("station1",1.5178,2.4523,5);
        Station s2 = new Station("station2",1.5179,2.4523,5);
        Station s3 = new Station("station3",1.5174,2.4523,5);
        Station s4 = new Station("station4",1.5171,2.4523,5);
        Set<Station> stations = new HashSet<>();
        stations.add(s1);
        stations.add(s2);
        stations.add(s3);
        stations.add(s4);
        ClosestStationIterator sI = new ClosestStationIterator(stations,s1);
        assertNotEquals("station3",sI.next().getNom());
    }
    @Test
    public void testRemove() {
        Station s1 = new Station("station1",1.5178,2.4523,5);
        Station s2 = new Station("station2",1.5179,2.4523,5);
        Station s3 = new Station("station3",1.5174,2.4523,5);
        Station s4 = new Station("station4",1.5171,2.4523,5);
        Set<Station> stations = new HashSet<>();
        stations.add(s1);
        stations.add(s2);
        stations.add(s3);
        stations.add(s4);
        ClosestStationIterator sI = new ClosestStationIterator(stations,s1);
        sI.remove();
        //censé ne rien changer
    }
}
