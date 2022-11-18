package fr.ufc.l3info.oprog;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class VilleTest {
    final String path = "./target/classes/data/";
    @Test
    public void testInitialiserWrongNumberValue() throws IOException {
        File f = new File(path + "stationsWrongNumberValue.txt");
        Ville v = new Ville();
        v.initialiser(f);

    }

    @Test
    public void testInitialiserNomStationsNonUnique() throws IOException {
        File f = new File(path + "nomStationsNonUnique.txt");
        Ville v = new Ville();
        v.initialiser(f);

    }

    @Test
    public void testInitialiserAccoladeFermanteManquante() throws IOException {
        File f = new File(path + "stationsAccoladeFermanteManquante.txt");
        Ville v = new Ville();
        v.initialiser(f);

    }

    @Test
    public void testInitialiser() throws IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        assertEquals("21 - Avenue Fontaine Argent, Boulevard Diderot",getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));
        assertEquals("Avenue du Maréchal Foch",getStation("Avenue du Maréchal Foch"));

    }


}
