import fr.ufc.l3info.oprog.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class TestsFonctionnels {
    final String path = "./target/classes/data/";

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

}
