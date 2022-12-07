import fr.ufc.l3info.oprog.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class TestsFonctionnels {
    final String path = "./target/classes/data/";
    private static final double DELTA = 1e-15;

    @Test
    public void testFonctionnelsCreerMauvaisAbonne() throws IOException {
        File file = new File(path + "stationsOK.txt");
        Ville ville = new Ville();
        ville.initialiser(file);
        FabriqueVelo f = FabriqueVelo.getInstance();

        IVelo velo1 = null;
        IVelo velo2 = null;

        Abonne a1 = ville.creerAbonne("Remi", "18331-13940-94873749273-90");
        Abonne a2 = ville.creerAbonne("Remi", "18331-13940-94873749273-92");

        Exploitant e = new Exploitant();
        for(int i=0; i<10; i++){
            e.acquerirVelo(f.construire('f'));
        }
        for(int i=10; i<22; i++){
            e.acquerirVelo(f.construire('h'));
        }
        e.ravitailler(ville);

        Station s = Mockito.spy(ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));

        Calendar date_emprunt = Calendar.getInstance();
        date_emprunt.set(2022, 5, 2, 1,0,0);
        long date_emprunt_ms = date_emprunt.getTimeInMillis();

        Mockito.when(s.maintenant()).thenReturn(date_emprunt_ms);
        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                velo1 = s.emprunterVelo(a1,i);
                break;
            }
        }

        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                velo2 = s.emprunterVelo(a2,i);
                break;
            }
        }


        Calendar date_retour = Calendar.getInstance();
        date_retour.set(2022, 5, 2, 5,0,0);
        long date_retour_ms = date_retour.getTimeInMillis();

        Mockito.when(s.maintenant()).thenReturn(date_retour_ms);

        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)==null){
                s.arrimerVelo(velo1,i);
                e.acquerirVelo(velo1);
                break;
            }
        }


        e.entretenirVelos();

        assertTrue(a2.estBloque());
        assertNull(velo2);
    }

    @Test (expected= IOException.class)
    public void testFonctionnelsInitialiserMauvaisFichier() throws IOException {
        File f = new File(path + "stationsAccoladeFermanteManquante.txt");
        Ville v = new Ville();
        v.initialiser(f);

    }

    @Test
    public void testFonctionnelsVeloAbime() throws IOException {
        File file = new File(path + "stationsOK.txt");
        Ville ville = new Ville();
        ville.initialiser(file);
        FabriqueVelo f = FabriqueVelo.getInstance();

        IVelo velo1 = null;
        IVelo velo2 = null;

        Abonne a1 = ville.creerAbonne("Remi", "18331-13940-94873749273-90");
        Abonne a2 = ville.creerAbonne("Remi", "18331-13940-94873749273-90");

        Exploitant e = new Exploitant();
        for(int i=0; i<10; i++){
            e.acquerirVelo(f.construire('f'));
        }
        for(int i=10; i<22; i++){
            e.acquerirVelo(f.construire('h'));
        }
        e.ravitailler(ville);

        Station s = Mockito.spy(ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));

        Calendar date_emprunt = Calendar.getInstance();
        date_emprunt.set(2022, 5, 2, 1,0,0);
        long date_emprunt_ms = date_emprunt.getTimeInMillis();

        Mockito.when(s.maintenant()).thenReturn(date_emprunt_ms);
        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                velo1 = s.emprunterVelo(a1,i);
                break;
            }
        }

        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                velo2 = s.emprunterVelo(a2,i);
                break;
            }
        }


        Calendar date_retour = Calendar.getInstance();
        date_retour.set(2022, 5, 2, 5,0,0);
        long date_retour_ms = date_retour.getTimeInMillis();

        Mockito.when(s.maintenant()).thenReturn(date_retour_ms);

        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)==null){
                s.arrimerVelo(velo1,i);
                e.acquerirVelo(velo1);
                break;
            }
        }
        velo2.abimer();
        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)==null){
                s.arrimerVelo(velo2,i);
                e.acquerirVelo(velo2);
                break;
            }
        }

        e.entretenirVelos();




        assertFalse(velo2.estAbime());

    }

    @Test
    public void testFonctionnelsVeloBesoinDeRevision() throws IOException {
        File file = new File(path + "stationsOK.txt");
        Ville ville = new Ville();
        ville.initialiser(file);
        FabriqueVelo f = FabriqueVelo.getInstance();

        IVelo velo1 = null;
        IVelo velo2 = null;

        Abonne a1 = ville.creerAbonne("Remi", "18331-13940-94873749273-90");
        Abonne a2 = ville.creerAbonne("Remi", "18331-13940-94873749273-90");

        Exploitant e = new Exploitant();
        for(int i=0; i<10; i++){
            e.acquerirVelo(f.construire('f'));
        }
        for(int i=10; i<22; i++){
            e.acquerirVelo(f.construire('h'));
        }
        e.ravitailler(ville);

        Station s = Mockito.spy(ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));

        Calendar date_emprunt = Calendar.getInstance();
        date_emprunt.set(2022, 5, 2, 1,0,0);
        long date_emprunt_ms = date_emprunt.getTimeInMillis();

        Mockito.when(s.maintenant()).thenReturn(date_emprunt_ms);
        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                velo1 = s.emprunterVelo(a1,i);
                break;
            }
        }

        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                velo2 = s.emprunterVelo(a2,i);
                break;
            }
        }


        Calendar date_retour = Calendar.getInstance();
        date_retour.set(2022, 5, 2, 5,0,0);
        long date_retour_ms = date_retour.getTimeInMillis();

        Mockito.when(s.maintenant()).thenReturn(date_retour_ms);

        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)==null){
                s.arrimerVelo(velo1,i);
                e.acquerirVelo(velo1);
                break;
            }
        }
        velo2.parcourir(550);
        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)==null){
                s.arrimerVelo(velo2,i);
                e.acquerirVelo(velo2);
                break;
            }
        }

        e.entretenirVelos();


        assertEquals(500, velo2.prochaineRevision(), 0.00001);

    }

    @Test
    public void testFonctionnelsVeloBesoinDeRevisionEtVeloAbime() throws IOException {
        File file = new File(path + "stationsOK.txt");
        Ville ville = new Ville();
        ville.initialiser(file);
        FabriqueVelo f = FabriqueVelo.getInstance();

        IVelo velo1 = null;
        IVelo velo2 = null;

        Abonne a1 = ville.creerAbonne("Remi", "18331-13940-94873749273-90");
        Abonne a2 = ville.creerAbonne("Remi", "18331-13940-94873749273-90");

        Exploitant e = new Exploitant();
        for(int i=0; i<10; i++){
            e.acquerirVelo(f.construire('f'));
        }
        for(int i=10; i<22; i++){
            e.acquerirVelo(f.construire('h'));
        }
        e.ravitailler(ville);

        Station s = Mockito.spy(ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));

        Calendar date_emprunt = Calendar.getInstance();
        date_emprunt.set(2022, 5, 2, 1,0,0);
        long date_emprunt_ms = date_emprunt.getTimeInMillis();

        Mockito.when(s.maintenant()).thenReturn(date_emprunt_ms);
        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                velo1 = s.emprunterVelo(a1,i);
                break;
            }
        }

        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                velo2 = s.emprunterVelo(a2,i);
                break;
            }
        }


        Calendar date_retour = Calendar.getInstance();
        date_retour.set(2022, 5, 2, 5,0,0);
        long date_retour_ms = date_retour.getTimeInMillis();

        Mockito.when(s.maintenant()).thenReturn(date_retour_ms);

        velo1.abimer();
        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)==null){
                s.arrimerVelo(velo1,i);
                e.acquerirVelo(velo1);
                break;
            }
        }
        velo2.parcourir(550);
        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)==null){
                s.arrimerVelo(velo2,i);
                e.acquerirVelo(velo2);
                break;
            }
        }

        e.entretenirVelos();

        assertFalse(velo1.estAbime());
        assertEquals(500, velo2.prochaineRevision(), 0.00001);

    }
    @Test
    public void testFonctionnelcreationVille() throws IOException {
        Ville ville = new Ville();
        ville.initialiser(new File(path + "ville_besac.txt"));
        Assert.assertNotNull(ville.getStation("Victor Hugo"));
        Assert.assertNotNull(ville.getStation("Jean Cornet"));
        Assert.assertNotNull(ville.getStation("Révolution"));
        Assert.assertNotNull(ville.getStation("Square Bouchot"));
        Assert.assertNotNull(ville.getStation("Isembart"));
        Assert.assertNull(ville.getStation("Avenue de la fin"));
        ville.setStationPrincipale("Jean Cornet");
        Iterator<Station> i=ville.iterator();
        assertEquals(ville.getStation("Jean Cornet"),i.next());

        Assert.assertEquals(ville.getStation("Victor Hugo"), ville.getStationPlusProche(47.234, 6.029));
        Assert.assertNotEquals(ville.getStation("Isembart"), ville.getStationPlusProche(47.234, 6.029));

        Abonne michel = ville.creerAbonne("Michel","0");
        Abonne achour = ville.creerAbonne("Achour", "0");
        Abonne jean_pi = ville.creerAbonne("Jean-Pierre", "0");
        Abonne rien = ville.creerAbonne("", "0");
        Assert.assertNotNull(michel);
        Assert.assertNotNull(achour);
        Assert.assertNotNull(jean_pi);
        Assert.assertNull(rien);


        double payer=0.0;
        for (HashMap.Entry<Abonne, Double> entry : ville.facturation(4,2022).entrySet()) {
            payer+=entry.getValue();
        }

        assertEquals(0.0,payer,0.1);
        Assert.assertEquals(3,ville.facturation(4,2022).size());

    }
    @Test
    public void testFonctionnelcreationExploitant() throws IOException {
        Exploitant e = new Exploitant();
        Ville ville = new Ville();
        ville.initialiser(new File(path + "ville_besac.txt"));
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo v = fabrique.construire('f');
        IVelo v2 = fabrique.construire('m');
        IVelo v3 = fabrique.construire('h',"ASSISTANCE_ELECTRIQUE");
        IVelo v4 = fabrique.construire('f', "ASSISTANCE_ELECTRIQUE");
        IVelo v5 = fabrique.construire('m', "FREINS_DISQUE", "CADRE_ALUMINIUM");
        IVelo v6 = fabrique.construire('m',"SUSPENSION_ARRIERE", "SUSPENSION_AVANT");
        e.acquerirVelo(v);
        e.acquerirVelo(v2);
        e.acquerirVelo(v3);
        e.acquerirVelo(v4);
        e.acquerirVelo(v5);
        e.acquerirVelo(v6);
        e.ravitailler(ville);

        Assert.assertEquals(7,ville.getStation("Mairie").nbBornesLibres());
        Assert.assertEquals(9,ville.getStation("Morand").nbBornesLibres());
        Assert.assertEquals(9,ville.getStation("8 Septembre").nbBornesLibres()) ;
        Assert.assertEquals(6,ville.getStation("Belfort").nbBornesLibres() );
        Assert.assertEquals(10,ville.getStation("Office de tourisme").nbBornesLibres());
    }
    @Test
    public void testFonctionnelsEntretienVelo()throws IOException {
        Exploitant e = new Exploitant();
        Ville ville = new Ville();
        ville.initialiser(new File(path + "ville_besac.txt"));
        //Ajout de vélos au stock
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo v = fabrique.construire('f');
        v.parcourir(600);
        v.abimer();
        IVelo v2 = fabrique.construire('m');
        v2.abimer();
        IVelo v3 = fabrique.construire('h',"ASSISTANCE_ELECTRIQUE");
        v.parcourir(530);
        IVelo v4 = fabrique.construire('f', "ASSISTANCE_ELECTRIQUE");
        //Acquisition des vélos
        e.acquerirVelo(v);
        e.acquerirVelo(v2);
        e.acquerirVelo(v3);
        e.acquerirVelo(v4);
        //Entretenir
        e.entretenirVelos();

        assertFalse(v.estAbime());
        assertFalse(v2.estAbime());
        assertFalse(v3.estAbime());
        assertFalse(v4.estAbime());
        assertEquals(500, v.prochaineRevision(), 1e-15);
        assertEquals(500, v2.prochaineRevision(), 1e-15);
        assertEquals(500, v3.prochaineRevision(),1e-15);
        assertEquals(500, v4.prochaineRevision(),1e-15);
    }
    @Test
    public void TestFonctionnelsfacturation() throws IOException {
        File f = new File(path + "stationsOK.txt");
        Ville v = new Ville();
        v.initialiser(f);

        //creation de 2 abonnes
        Abonne a1 = v.creerAbonne("Remi", "18331-13940-94873749273-90");
        Abonne a2 = v.creerAbonne("George","18331-13940-94873749273-90");
        //creation d'un exploitant
        Exploitant e = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        //les exploitants acquerissent 22 velos
        for(int i=0; i<22; i++){
            fabrique.construire('f');
            e.acquerirVelo(fabrique.construire('f'));
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
    public void testAjoutAbonnes() throws IOException {
        File f = new File(path + "stations_OK_v3.txt");
        Ville ville = new Ville();
            ville.initialiser(f);

        Abonne a1 = ville.creerAbonne("Agathe", "93041-19840-03948482304-04");
        assertFalse(a1.estBloque());
        assertEquals(a1.getNom(),"Agathe");
            a1.debloquer();

        Abonne a2 = ville.creerAbonne(null, "93048-19840-03948482304-60");
        assertNull(a2);

        Abonne a3 = ville.creerAbonne("Louison", "93046-19240-03948182304-23");
        assertTrue(a3.estBloque());
            a3.miseAJourRIB("93046-19240-03948482330-42");
        assertFalse(a3.estBloque());

        Abonne a4 = ville.creerAbonne("Ro*se", "93041-19240-03948482304-80");
        assertNull(a4);

        Abonne a5 = a1;
        assertTrue(a1.equals(a5));
        assertFalse(a1.equals(a3));

        assertTrue(a1.getID()<a3.getID());

        Abonne a6 = ville.creerAbonne("Marion", "19240-03948482304-80");
        assertTrue(a6.estBloque());

        Abonne a7 = ville.creerAbonne("Benjamin", "930*1-19240-03948482304-80");
        assertTrue(a7.estBloque());
    }

    @Test (expected= IOException.class)
    public void testInitialisationVilleVide() throws IOException {
        File f = new File(path + "nomStationsNonUnique.txt");
        Ville ville2 = new Ville();
        ville2.initialiser(f);
    }

    @Test
    public void testInitialisationStations() throws IOException {
        //Création ville
        File f = new File(path + "stations_OK_v4.txt");
        Ville ville = new Ville();
        ville.initialiser(f);

        assertNotNull(ville.getStation("Homestead"));
        assertNotNull(ville.getStation("Kendal"));
        assertNotNull(ville.getStation("Miami"));
        assertNotNull(ville.getStation("Hollywood"));
        assertNotNull(ville.getStation("Tamarac"));
        assertNotNull(ville.getStation("Coral Springs"));
    }

    @Test
    public void testParcoursStations() throws IOException {
        //Création ville
        File f = new File(path + "stations_OK_v4.txt");
        Ville ville = new Ville();
        ville.initialiser(f);

        Iterator<Station> i = ville.iterator();
        assertEquals(ville.getStation("Homestead").getNom(),i.next().getNom());
        assertEquals(ville.getStation("Kendal").getNom(),i.next().getNom());
        assertEquals(ville.getStation("Miami").getNom(),i.next().getNom());
        assertEquals(ville.getStation("Hollywood").getNom(),i.next().getNom());
        assertEquals(ville.getStation("Tamarac").getNom(),i.next().getNom());
        assertEquals(ville.getStation("Coral Springs").getNom(),i.next().getNom());

        assertNull(ville.getStation("Inexistante"));
    }

    @Test
    public void testAjoutVelos() throws IOException {
        //Création ville
        File f = new File(path + "une_station_OK.txt");
        Ville ville = new Ville();
        ville.initialiser(f);

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo v1 = fabrique.construire('h', "ASSISTANCE_ELECTRIQUE", "SUSPENSION_ARRIERE");
        IVelo v2 = fabrique.construire('h', "CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        IVelo v3 = fabrique.construire('f', "CADRE_ALUMINIUM", "ASSISTANCE_ELECTRIQUE");
        IVelo v4 = fabrique.construire('f', "CADRE_ALUMINIUM");
        IVelo v5 = fabrique.construire('m',  "ASSISTANCE_ELECTRIQUE");
        IVelo v6 = fabrique.construire('m', "SUSPENSION_ARRIERE");
        IVelo v7 = fabrique.construire('F', "CADRE_ALUMINIUM", "ASSISTANCE_ELECTRIQUE", "SUSPENSION_ARRIERE");

        Exploitant exploitant = new Exploitant();
        exploitant.acquerirVelo(v1);
        exploitant.acquerirVelo(v2);
        exploitant.acquerirVelo(v3);
        exploitant.acquerirVelo(v4);
        exploitant.acquerirVelo(v5);
        exploitant.acquerirVelo(v6);
        exploitant.acquerirVelo(v7);

        exploitant.ravitailler(ville);

        int nb_velo=0;
        Station station = ville.getStation("La station");
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                nb_velo++;
            }
        }

        assertEquals(nb_velo,7);
    }

    @Test
    public void testBloquerAbonneRetourVeloAbime() throws IOException {
        File f = new File(path + "une_station_OK.txt");
        Ville ville = new Ville();
        ville.initialiser(f);

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo v1 = fabrique.construire('h', "ASSISTANCE_ELECTRIQUE", "SUSPENSION_ARRIERE");

        Exploitant exploitant = new Exploitant();
        exploitant.acquerirVelo(v1);

        exploitant.ravitailler(ville);

        Abonne abonne = ville.creerAbonne("Agathe", "93041-19840-03948482304-04");

        //Emprunt vélo
        IVelo velo=null;
        Station station = ville.getStation("La station");
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                velo = station.emprunterVelo(abonne,i);
                break;
            }
        }

        velo.parcourir(40);
        velo.abimer();
        assertEquals(velo, v1);

        //Retour vélo
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)==null){
                station.arrimerVelo(velo,i);
                break;
            }
        }

        assertTrue(abonne.estBloque());

        //Emprunt par un abonné bloqué
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                velo = station.emprunterVelo(abonne,i);
                break;
            }
        }

        assertNull(velo);

    }

    @Test
    public void testFacturationUnAbonne() throws IOException {
        //Création ville
        File f = new File(path + "stations_OK_v3.txt");
        Ville ville = new Ville();

        ville.initialiser(f);

        Abonne a1 = ville.creerAbonne("Agathe", "93041-19840-03948482304-04");

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        Exploitant exploitant = new Exploitant();
        for(int i=0; i<100; i++){
            exploitant.acquerirVelo(fabrique.construire('f', "ASSISTANCE_ELECTRIQUE", "SUSPENSION_ARRIERE"));
        }

        exploitant.ravitailler(ville);

        //Premier emprunt
        Calendar date_emprunt_1 = Calendar.getInstance();
        date_emprunt_1.set(2021, 8, 20, 13,0,0);
        long date_emprunt_ms_1 = date_emprunt_1.getTimeInMillis();

        Calendar date_retour_1 = Calendar.getInstance();
        date_retour_1.set(2021, 8, 20, 18,30,0);
        long date_retour_ms_1 = date_retour_1.getTimeInMillis();

        Station s1 = Mockito.spy(ville.getStation("Station 1"));
        Mockito.when(s1.maintenant()).thenReturn(date_emprunt_ms_1);
        IVelo velo=null;
        for(int i=1;i<=s1.capacite();i++){
            if(s1.veloALaBorne(i)!=null){
                velo = s1.emprunterVelo(a1,i);
                break;
            }
        }

        Station s2 = Mockito.spy(ville.getStation("Station 2"));
        Mockito.when(s2.maintenant()).thenReturn(date_retour_ms_1);
        for(int i=1;i<=s2.capacite();i++){
            if(s2.veloALaBorne(i)==null){
                s2.arrimerVelo(velo,i);
                break;
            }
        }

        //Afficher vélo emprunté
        assertEquals(velo.toString(),"Vélo cadre femme, assistance électrique, suspension arrière - 0.0 km");

        //Deuxième emprunt
        Calendar date_emprunt_2 = Calendar.getInstance();
        date_emprunt_2.set(2021, 8, 3, 11,0,0);
        long date_emprunt_ms_2 = date_emprunt_2.getTimeInMillis();

        Calendar date_retour_2 = Calendar.getInstance();
        date_retour_2.set(2021, 8, 3, 16,0,0);
        long date_retour_ms_2 = date_retour_2.getTimeInMillis();

        Mockito.when(s2.maintenant()).thenReturn(date_emprunt_ms_2);

        for(int i=1;i<=s2.capacite();i++){
            if(s2.veloALaBorne(i)!=null){
                velo = s2.emprunterVelo(a1,i);
                break;
            }
        }

        Mockito.when(s1.maintenant()).thenReturn(date_retour_ms_2);
        for(int i=1;i<=s1.capacite();i++){
            if(s1.veloALaBorne(i)==null){
                s1.arrimerVelo(velo,i);
                break;
            }
        }


        //Facture de l'abonné
        Map<Abonne, Double> facturations = ville.facturation(8,2021);
        double facturation_tot=0.0;
        for (Map.Entry<Abonne, Double> entry : facturations.entrySet()) {
            facturation_tot+=entry.getValue();
        }

        assertEquals(47.25, facturation_tot, 0.000001);
    }

    @Test
    public void testFacturationPlusieursAbonnes() throws IOException {
        File f = new File(path + "stations_OK_v3.txt");
        Ville ville = new Ville();

        ville.initialiser(f);

        Abonne a1 = ville.creerAbonne("Agathe", "93041-19840-03948482304-04");
        Abonne a2 = ville.creerAbonne("Marion", "93048-19840-03948482304-60");

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        Exploitant exploitant = new Exploitant();
        for(int i=0; i<100; i++){
            exploitant.acquerirVelo(fabrique.construire('m', "ASSISTANCE_ELECTRIQUE", "SUSPENSION_ARRIERE"));
        }

        exploitant.ravitailler(ville);

        //Emprunt abonné 1
        Calendar date_emprunt_1 = Calendar.getInstance();
        date_emprunt_1.set(2021, 8, 20, 13,0,0);
        long date_emprunt_ms_1 = date_emprunt_1.getTimeInMillis();

        Calendar date_retour_1 = Calendar.getInstance();
        date_retour_1.set(2021, 8, 20, 18,30,0);
        long date_retour_ms_1 = date_retour_1.getTimeInMillis();

        Station s1 = Mockito.spy(ville.getStation("Station 1"));
        Mockito.when(s1.maintenant()).thenReturn(date_emprunt_ms_1);
        IVelo velo=null;
        for(int i=1;i<=s1.capacite();i++){
            if(s1.veloALaBorne(i)!=null){
                velo = s1.emprunterVelo(a1,i);
                break;
            }
        }

        Station s2 = Mockito.spy(ville.getStation("Station 2"));
        Mockito.when(s2.maintenant()).thenReturn(date_retour_ms_1);
        for(int i=1;i<=s2.capacite();i++){
            if(s2.veloALaBorne(i)==null){
                s2.arrimerVelo(velo,i);
                break;
            }
        }

        //Emprunt abonné 2
        Calendar date_emprunt_2 = Calendar.getInstance();
        date_emprunt_2.set(2021, 8, 3, 11,0,0);
        long date_emprunt_ms_2 = date_emprunt_2.getTimeInMillis();

        Calendar date_retour_2 = Calendar.getInstance();
        date_retour_2.set(2021, 8, 3, 16,0,0);
        long date_retour_ms_2 = date_retour_2.getTimeInMillis();

        Mockito.when(s2.maintenant()).thenReturn(date_emprunt_ms_2);

        for(int i=1;i<=s2.capacite();i++){
            if(s2.veloALaBorne(i)!=null){
                velo = s2.emprunterVelo(a2,i);
                break;
            }
        }

        Mockito.when(s1.maintenant()).thenReturn(date_retour_ms_2);
        for(int i=1;i<=s1.capacite();i++){
            if(s1.veloALaBorne(i)==null){
                s1.arrimerVelo(velo,i);
                break;
            }
        }


        //Facture de l'abonné aout
        Map<Abonne, Double> facturations_aout2021 = ville.facturation(8,2021);
        double facturations_aout2021_tot=0.0;
        for (Map.Entry<Abonne, Double> entry : facturations_aout2021.entrySet()) {
            if(entry.getKey()==a1){
                assertEquals(24.75, entry.getValue(), 0.000001);
            }
            if(entry.getKey()==a2){
                assertEquals(22.5, entry.getValue(), 0.000001);
            }
            facturations_aout2021_tot+=entry.getValue();
        }

        assertEquals(47.25, facturations_aout2021_tot, 0.000001);

        //Facture de l'abonné mois inexistant
        Map<Abonne, Double> facturations_inexistante = ville.facturation(16,2021);
        double facturations_inexistante_tot=0.0;
        for (Map.Entry<Abonne, Double> entry : facturations_inexistante.entrySet()) {
            if(entry.getKey()==a1){
                assertEquals(24.75, entry.getValue(), 0.000001);
            }
            if(entry.getKey()==a2){
                assertEquals(22.5, entry.getValue(), 0.000001);
            }
            facturations_inexistante_tot+=entry.getValue();
        }

        assertEquals(0, facturations_inexistante_tot, 0.000001);
    }

    @Test
    public void testRavitaillerVelosAbimesVelosNeufs() throws IOException {
        File f = new File(path + "une_station_OK.txt");
        Ville ville = new Ville();

        ville.initialiser(f);

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        Exploitant exploitant = new Exploitant();
        for(int i=0; i<100; i++){
            exploitant.acquerirVelo(fabrique.construire('h', "ASSISTANCE_ELECTRIQUE", "SUSPENSION_ARRIERE"));
        }

        exploitant.ravitailler(ville);

        Station station = ville.getStation("La station");
        List<IVelo> velos_abime = new ArrayList<>();
        //Abimer tous les vélos
        int nb_velo=0;
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                IVelo v = station.veloALaBorne(i);
                v.abimer();
                velos_abime.add(v);
                nb_velo++;
            }
        }
        assertEquals(nb_velo, 20);

        //Changer les vélos
        exploitant.ravitailler(ville);

        //Vérifie qu'ils sont encore abimé, mais plus aux bornes
        for(IVelo v : velos_abime){
            assertTrue(v.estAbime());
        }

        nb_velo=0;
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null && !station.veloALaBorne(i).estAbime()){
                nb_velo++;
            }
        }
        assertEquals(nb_velo, 20);

        //Réparation des vélos dans le stock
        exploitant.entretenirVelos();

        //Vérifie qu'ils ne sont plus abimé
        for(IVelo v : velos_abime){
            assertFalse(v.estAbime());
        }
    }

    @Test
    public void testRavitaillerVelosAReviserVelosNeufs() throws IOException {
        File f = new File(path + "une_station_OK.txt");
        Ville ville = new Ville();

        ville.initialiser(f);

        Abonne a1 = ville.creerAbonne("Agathe", "93041-19840-03948482304-04");

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        Exploitant exploitant = new Exploitant();
        for(int i=0; i<100; i++){
            exploitant.acquerirVelo(fabrique.construire('h', "ASSISTANCE_ELECTRIQUE", "SUSPENSION_ARRIERE"));
        }

        exploitant.ravitailler(ville);

        Station station = ville.getStation("La station");
        List<IVelo> velos_a_reviser = new ArrayList<>();
        //Abimer tous les vélos
        int nb_velo=0;
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                IVelo v = station.veloALaBorne(i);
                station.emprunterVelo(a1,i);
                v.parcourir(600);
                velos_a_reviser.add(v);
                station.arrimerVelo(v,i);
                if(nb_velo==0){
                    //Afficher le vélo
                    assertEquals(station.veloALaBorne(i).toString(),"Vélo cadre homme, assistance électrique, suspension arrière - 600.0 km (révision nécessaire)");
                }
                nb_velo++;
            }
        }
        assertEquals(nb_velo, 20);

        //Changer les vélos
        exploitant.ravitailler(ville);

        //Vérifie qu'ils sont encore à réviser, mais plus aux bornes
        for(IVelo v : velos_a_reviser){
            assertTrue(v.prochaineRevision()<0);
        }

        nb_velo=0;
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null && station.veloALaBorne(i).prochaineRevision()==500){
                nb_velo++;
            }
        }
        assertEquals(nb_velo, 20);

        //Réparation des vélos dans le stock
        exploitant.entretenirVelos();

        //Vérifie qu'ils ne sont plus abimé
        for(IVelo v : velos_a_reviser){
            assertEquals(v.prochaineRevision(),500, DELTA);
        }
    }

    @Test
    public void testAllerStationLaPlusProcheDepuisPrincipale() throws IOException {
        File f = new File(path + "stations_OK_v4.txt");
        Ville ville = new Ville();
        ville.initialiser(f);
        ville.setStationPrincipale("Miami");

        Exploitant exploitant = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        for(int i=0; i<100; i++){
            exploitant.acquerirVelo(fabrique.construire('h', "FREIN_DISQUE", "SUSPENSION_AVANT"));
        }


        exploitant.ravitailler(ville);

        Abonne abonne = ville.creerAbonne("Agathe", "93041-19840-03948482304-04");
        Iterator<Station> it = ville.iterator();
        Station station = it.next();
        assertEquals(station.getNom(),"Miami");

        //Emprunt vélo
        IVelo velo=null;
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                velo = station.emprunterVelo(abonne,i);
                break;
            }
        }
        //Afficher vélo emprunté
        assertEquals(velo.toString(),"Vélo cadre homme, suspension avant - 0.0 km");

        velo.parcourir(40);
        station = it.next();
        assertEquals(station.getNom(),"Kendal");

        //Retour vélo
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)==null){
                station.arrimerVelo(velo,i);
                break;
            }
        }
    }

    @Test
    public void testAllerStationLaPlusProche() throws IOException {
        File f = new File(path + "stations_OK_v4.txt");
        Ville ville = new Ville();
        ville.initialiser(f);

        Exploitant exploitant = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        for(int i=0; i<100; i++){
            exploitant.acquerirVelo(fabrique.construire('h', "FREIN_DISQUE", "SUSPENSION_AVANT"));
        }


        exploitant.ravitailler(ville);

        Abonne abonne = ville.creerAbonne("Agathe", "93041-19840-03948482304-04");
        Station station = ville.getStation("Kendal");

        //Emprunt vélo
        IVelo velo=null;
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                velo = station.emprunterVelo(abonne,i);
                break;
            }
        }
        //Afficher vélo emprunté
        assertEquals(velo.toString(),"Vélo cadre homme, suspension avant - 0.0 km");

        velo.parcourir(40);
        station = ville.getStationPlusProche(25.99343267484602, -80.38638302540232);
        assertEquals(station.getNom(),"Hollywood");

        //Retour vélo
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)==null){
                station.arrimerVelo(velo,i);
                break;
            }
        }
    }

    @Test
    public void testEmpruntsImpossibles() throws IOException {
        File f = new File(path + "stations_OK_v4.txt");
        Ville ville = new Ville();
        ville.initialiser(f);


        Exploitant exploitant = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        for(int i=0; i<100; i++){
            exploitant.acquerirVelo(fabrique.construire('h'));
        }


        exploitant.ravitailler(ville);

        Abonne abonne = ville.creerAbonne("Agathe", "93041-19840-03948482304-04");

        Station station = ville.getStation("Kendal");

        //Emprunt borne vide
        IVelo velo=null;
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)==null){
                assertNull(station.emprunterVelo(abonne,i));
                break;
            }
        }

        //Emprunt borne inexistante
        assertNull(station.emprunterVelo(abonne,-1));
        assertNull(station.emprunterVelo(abonne,500));

        //Emprunt abonné bloqué
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                velo = station.emprunterVelo(abonne,i);
                break;
            }
        }

        velo.abimer();

        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)==null){
                station.arrimerVelo(velo, i);
                break;
            }
        }

        assertTrue(abonne.estBloque());

        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null && !station.veloALaBorne(i).estAbime()){
                assertNull(station.emprunterVelo(abonne, i));
                break;
            }
        }

        //Plusieurs emprunts
        abonne.debloquer();
        int nb=0;
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null && !station.veloALaBorne(i).estAbime()){
                if(nb==0){
                    assertNotNull(station.emprunterVelo(abonne,i));
                }else{
                    assertNull(station.emprunterVelo(abonne,i));
                }
                nb++;
            }
        }

    }


    @Test
    public void testRetoursImpossibles() throws IOException {
        File f = new File(path + "stations_OK_v4.txt");
        Ville ville = new Ville();
        ville.initialiser(f);


        Exploitant exploitant = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        for(int i=0; i<100; i++){
            exploitant.acquerirVelo(fabrique.construire('h'));
        }


        exploitant.ravitailler(ville);

        Abonne abonne = ville.creerAbonne("Agathe", "93041-19840-03948482304-04");

        Station station = ville.getStation("Kendal");

        //Retour borne occupée
        IVelo velo=null;
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                velo = station.emprunterVelo(abonne,i);
                break;
            }
        }
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                assertEquals(station.arrimerVelo(velo,i), -2);
                break;
            }
        }
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)==null){
                assertNotNull(station.arrimerVelo(velo,i));
                break;
            }
        }

        //Retour borne inexistante
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)!=null){
                velo = station.emprunterVelo(abonne,i);
                break;
            }
        }
        assertEquals(station.arrimerVelo(velo,-1), -1);
        assertEquals(station.arrimerVelo(velo,500), -1);

        //Retour velo null
        for(int i=1;i<=station.capacite();i++){
            if(station.veloALaBorne(i)==null){
                assertEquals(station.arrimerVelo(null,3), -1);
                break;
            }
        }

    }


    @Test
    public void testFonctionnelGeneral() throws IOException {
        //Création ville
        File f = new File(path + "une_station_OK.txt");
        Ville ville = new Ville();
        //Ajout stations
        ville.initialiser(f);
        //Ajout abonnés
        Abonne a1 = ville.creerAbonne("Agathe", "93041-19840-03948482304-04");

        //Création vélo
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo velo = fabrique.construire('F', "CADRE_ALUMINIUM", "ASSISTANCE_ELECTRIQUE", "SUSPENSION_ARRIERE");

        //Initialisation Exploitant + son stock
        Exploitant exploitant = new Exploitant();
        exploitant.acquerirVelo(velo);

        exploitant.ravitailler(ville);

        //Utilisation des vélos
        Calendar date_emprunt = Calendar.getInstance();
        date_emprunt.set(2001, 8, 3, 13,0,0);
        long date_emprunt_ms = date_emprunt.getTimeInMillis();

        Calendar date_retour = Calendar.getInstance();
        date_retour.set(2001, 8, 3, 18,30,0);
        long date_retour_ms = date_retour.getTimeInMillis();

        Station s = Mockito.spy(ville.getStation("La station"));
        Mockito.when(s.maintenant()).thenReturn(date_emprunt_ms);
        IVelo velo1=null;
        for(int i=0;i<s.capacite();i++){
            if(s.veloALaBorne(i)!=null){
                velo1 = s.emprunterVelo(a1,i);
                break;
            }
        }

        velo1.abimer();

        Mockito.when(s.maintenant()).thenReturn(date_retour_ms);
        for(int i=1;i<s.capacite();i++){
            if(s.veloALaBorne(i)==null){
                s.arrimerVelo(velo1,i);
                break;
            }
        }

        //Tournée réparer vélos
        exploitant.ravitailler(ville);
        Assert.assertTrue(velo1.estAbime());
        exploitant.entretenirVelos();
        assertFalse(velo1.estAbime());

        //Facture des utilisations
        Map<Abonne, Double> facturations = ville.facturation(8,2001);
        double facturation_tot=0.0;
        for (Map.Entry<Abonne, Double> entry : facturations.entrySet()) {
            facturation_tot+=entry.getValue();
        }

        assertEquals(25.85, facturation_tot, 0.000001);
    }

}
