import fr.ufc.l3info.oprog.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

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

}
