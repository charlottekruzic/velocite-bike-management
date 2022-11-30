package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

    @Test
    public void testsetStationPrincipaleParDefaut() throws IOException {
        File f = new File(path + "stations_OK_v3.txt");
        Ville v = new Ville();
        v.initialiser(f);
        Iterator<Station> i=v.iterator();
        assertEquals(v.getStation("Station 1").getNom(),i.next().getNom());
    }

    @Test
    public void testsetStationPrincipalepaschoisi() throws IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        Iterator<Station> i=v.iterator();
        assertEquals(v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot").getNom(),i.next().getNom());
    }
    @Test
    public void testsetStationPrincipaleNexistePas() throws IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);
        v.setStationPrincipale("Avenue de la fin");
        Iterator<Station> i=v.iterator();
        assertEquals(v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"),i.next());
    }
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
        assertEquals("Avenue du Maréchal Foch",v.getStationPlusProche(49.0,6.02).getNom());
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
        i.next();
        assertEquals(v.getStation("l'aventure c'est extra").getNom(),i.next().getNom());
    }

    @Test
    public void Testfacturation() throws IncorrectNameException, IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);

        //creation de 2 abonnes
        Abonne a1 = v.creerAbonne("Remi", "18331-13940-94873749273-90");
        Abonne a2 = v.creerAbonne("George","18331-13940-94873749273-90");
        //creation d'un exploitant
        Exploitant e = new Exploitant();
        //les exploitants acquerissent 22 velos
        for(int i=0; i<22; i++){
            e.acquerirVelo(new Velo());
        }
        //il ravitaille la ville
        e.ravitailler(v);

        Calendar date_emprunt = Calendar.getInstance();
        date_emprunt.set(2022, 4, 3, 10,0,0);
        long date_emprunt_ms = date_emprunt.getTimeInMillis();

        Calendar date_retour = Calendar.getInstance();
        date_retour.set(2022, 4, 3, 15,0,0);
        long date_retour_ms = date_retour.getTimeInMillis();

        Station s=Mockito.spy(v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));
        //Station s = v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");

        Mockito.when(s.maintenant()).thenReturn(date_emprunt_ms);

        IVelo ve=null;
        //l'abonne Remi emprunte les velos de la sation fontaine argent
        for(int i=1;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                ve=s.emprunterVelo(a1,i);
                break;
            }
        }
        Mockito.when(s.maintenant()).thenReturn(date_retour_ms);

        //repose les velos sur les bornes
        for (int i=1; i<s.capacite(); i++){
            if(s.veloALaBorne(i)==null){
                s.arrimerVelo(ve,i);
                break;
            }

        }

        double payer=0.0;
        for (HashMap.Entry<Abonne, Double> entry : v.facturation(4,2022).entrySet()) {
            payer+=entry.getValue();
        }

        assertEquals(10.0,payer,0.1);


    }
    @Test
    public void TestfacturationDateMauvaise() throws IncorrectNameException, IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);

        //creation de 2 abonnes
        Abonne a1 = v.creerAbonne("Remi", "18331-13940-94873749273-90");
        Abonne a2 = v.creerAbonne("George","18331-13940-94873749273-90");
        //creation d'un exploitant
        Exploitant e = new Exploitant();
        //les exploitants acquerissent 22 velos
        for(int i=0; i<22; i++){
            e.acquerirVelo(new Velo());
        }
        //il ravitaille la ville
        e.ravitailler(v);

        Calendar date_emprunt = Calendar.getInstance();
        date_emprunt.set(2022, 4, 3, 10,0,0);
        long date_emprunt_ms = date_emprunt.getTimeInMillis();

        Calendar date_retour = Calendar.getInstance();
        date_retour.set(2022, 4, 3, 15,0,0);
        long date_retour_ms = date_retour.getTimeInMillis();

        Station s=Mockito.spy(v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));
        //Station s = v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");

        Mockito.when(s.maintenant()).thenReturn(date_emprunt_ms);

        IVelo ve=null;
        //l'abonne Remi emprunte les velos de la sation fontaine argent
        for(int i=1;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                ve=s.emprunterVelo(a1,i);
                break;
            }
        }
        Mockito.when(s.maintenant()).thenReturn(date_retour_ms);

        //repose les velos sur les bornes
        for (int i=1; i<s.capacite(); i++){
            if(s.veloALaBorne(i)==null){
                s.arrimerVelo(ve,i);
                break;
            }

        }

        double payer=0.0;
        for (HashMap.Entry<Abonne, Double> entry : v.facturation(14,2022).entrySet()) {
            payer+=entry.getValue();
        }

        assertEquals(0.0,payer,0.1);


    }

    @Test
    public void TestDecembreFacturation() throws IncorrectNameException, IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);

        Abonne a1 = v.creerAbonne("Remi", "19922-13333-13444444441-21");
        Abonne a2 = v.creerAbonne("George","18331-13940-94873749273-90");

        Exploitant e = new Exploitant();
        for(int i=0; i<22; i++){
            e.acquerirVelo(new Velo());
        }
        e.ravitailler(v);

        //Date d'emprunt et de retour
        Calendar date_emprunt = Calendar.getInstance();
        date_emprunt.set(2021, 12, 3, 12,0,0);
        long date_emprunt_ms = date_emprunt.getTimeInMillis();

        Calendar date_retour = Calendar.getInstance();
        date_retour.set(2021, 12, 3, 15,0,0);
        long date_retour_ms = date_retour.getTimeInMillis();


        Station s1 = Mockito.spy(v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));
        Mockito.when(s1.maintenant()).thenReturn(date_emprunt_ms);

        //emprunt du vélo
        IVelo velo=null;
        for(int i=0;i<s1.capacite();i++){
            if(s1.veloALaBorne(i)!=null){
                velo = s1.emprunterVelo(a1,i);
                break;
            }
        }

        Station s2 = Mockito.spy(v.getStation("Avenue du Maréchal Foch"));
        Mockito.when(s2.maintenant()).thenReturn(date_retour_ms);
        //retour du vélo
        for(int i=1;i<s2.capacite();i++){
            if(s2.veloALaBorne(i)==null){
                s2.arrimerVelo(velo,i);
                break;
            }
        }

        Map<Abonne, Double> facturations = v.facturation(12,2021);
        double facturation_tot=0.0;
        for (Map.Entry<Abonne, Double> entry : facturations.entrySet()) {
            facturation_tot+=entry.getValue();
        }

        Assert.assertEquals(6, facturation_tot, 0.000001);

    }

}
