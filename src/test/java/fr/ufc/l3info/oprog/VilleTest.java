package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class VilleTest {
    final String path = "./target/classes/data/";
    @Test (expected= IOException.class)
    public void testInitialiserWrongNumberValue() throws IOException {
        File f = new File(path + "stationsWrongNumberValue.txt");
        Ville v = new Ville();
        v.initialiser(f);
    }

    @Test (expected= IOException.class)
    public void testInitialiserNomStationsNonUnique() throws IOException {
        File f = new File(path + "nomStationsNonUnique.txt");
        Ville v = new Ville();
        v.initialiser(f);

    }

    @Test (expected= IOException.class)
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
        assertEquals("21 - Avenue Fontaine Argent, Boulevard Diderot",v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot").getNom());
        assertEquals("Avenue du Maréchal Foch",v.getStation("Avenue du Maréchal Foch").getNom());

    }

    @Test
    public void testInitialiserDoubleAppel() throws IOException {
        File f1 = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f1);
        assertEquals("21 - Avenue Fontaine Argent, Boulevard Diderot",v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot").getNom());
        assertEquals("Avenue du Maréchal Foch",v.getStation("Avenue du Maréchal Foch").getNom());
        File f2 = new File(path + "stationsOK_2.txt");
        v.initialiser(f2);
        assertEquals(null,v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));
        assertEquals(null,v.getStation("Avenue du Maréchal Foch"));
        assertEquals("09 - Station 1",v.getStation("09 - Station 1").getNom());
        assertEquals("10 - Station 2",v.getStation("10 - Station 2").getNom());
    }

    @Test
    public void testsetStationPrincipaleSuccess() throws IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        v.setStationPrincipale("Avenue du Maréchal Foch");
        Iterator<Station> i=v.iterator();
        assertEquals(v.getStation("Avenue du Maréchal Foch"),i.next());
    }

    /*@Test
    public void testsetStationPrincipaleParDefaut() throws IOException {
        File f = new File(path + "stations_OK_v3.txt");
        Ville v = new Ville();
        v.initialiser(f);
        Iterator<Station> i=v.iterator();
        System.out.println(i.next().getNom());
        assertEquals(v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot").getNom(),i.next().getNom());
    }*/

    /*@Test
    public void testsetStationPrincipalepaschoisi() throws IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        Iterator<Station> i=v.iterator();
        assertEquals(v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot").getNom(),i.next().getNom());
    }*/
    /*@Test
    public void testsetStationPrincipaleNexistePas() throws IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        v.setStationPrincipale("Avenue de la fin");
        Iterator<Station> i=v.iterator();
        assertEquals(v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"),i.next());
    }*/
    @Test
    public void testgetStationNull() throws IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        assertEquals(null,v.getStation("Avenue de la fin"));
    }
    @Test
    public void testgetStationSuccess() throws IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        Iterator<Station> i=v.iterator();
        int cpt=0;
        while (i.hasNext()) {
            if(v.getStation("Avenue du Maréchal Foch")==i.next()){
                cpt++;
            }
        }
        assertEquals(1,cpt);
    }
    @Test
    public void testgetStationPlusProche() throws IncorrectNameException, IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        assertEquals("Avenue du Maréchal Foch",v.getStationPlusProche(47.24,6.02).getNom());
    }
    @Test
    public void testgetStationPlusProche2() throws IncorrectNameException, IOException {
        File f = new File(path + "ville_test.txt");
        Ville v = new Ville();
        v.initialiser(f);
        assertEquals("Vers l'infini et au dela",v.getStationPlusProche(49.0,6.02).getNom());
    }
    @Test
    public void testcreerAbonne() throws IncorrectNameException, IOException {
        Ville ville = new Ville();
        Assert.assertEquals("Nemo",ville.creerAbonne("Nemo","19372-10383-09976354833-37").getNom());
    }

    @Test
    public void testcreerAbonnenull() throws IncorrectNameException, IOException {
        Ville ville = new Ville();
        Assert.assertEquals(null,ville.creerAbonne("Nemo 35","19372-10383-09976354833-37"));
    }
    @Test
    public void testcreerAbonneMauvaisRib() {
        Ville ville = new Ville();
        Assert.assertTrue(ville.creerAbonne("Nemo","12345-12345-12345678912-15").estBloque());
    }
    @Test
    public void testIteratorNbStations() throws IncorrectNameException, IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        int cpt = 0;
        Iterator<Station> i = v.iterator();
        while (i.hasNext()) {
            i.next();
            cpt++;
        }
        assertEquals(2, cpt);
    }
    
    @Test
    public void Test_Iterator() throws IncorrectNameException, IOException{
        File f = new File(path + "ville_test.txt");
        Ville v = new Ville();
        v.initialiser(f);
        v.setStationPrincipale("Avenue de la fin");
        Iterator<Station> i = v.iterator();
        assertEquals(v.getStation("l'aventure c'est extra"),i.next());
    }

    @Test
    public void Testfacturation() throws IncorrectNameException, IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        Station s = v.getStation("Avenue du Maréchal Foch");

        Abonne a = new Abonne("Nemo", "19372-10383-09976354833-37");
        Abonne b= new Abonne("Marin","13341-89317-13746913443-92");
        IRegistre r = new JRegistre();
        s.setRegistre(r);

        IVelo ve = new Velo();
        IVelo ve2=new Velo();
        s.arrimerVelo(ve, 1);
        s.arrimerVelo(ve2,2);

        r.emprunter(a, ve, 1);
        r.emprunter(b,ve2,2);

        r.retourner(ve, 10000);
        r.retourner(ve2,10000);

        assertEquals(3,v.facturation(11,2022));

    }

    @Test
    public void TestDatefacturation() throws IncorrectNameException, IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        v.facturation(12, 2021);


    }




}
