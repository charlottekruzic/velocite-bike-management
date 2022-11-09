package fr.ufc.l3info.oprog;

// Mockito

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StationWithMocksTest {

    @Test
    public void testConstruceurNom() {

        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        assertEquals("Gare Viotte",s.getNom());

    }

    @Test
    public void testConstructeurCapacite() {

        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        assertEquals(10,s.capacite());

    }
    @Test
    public void testConstructeurMauvaiseCapacite() {

        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,-1);

        assertEquals(0,s.capacite());

    }



    @Test
    public void testEmprunterVeloAbonneBloque() {

        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        Abonne mockAbonne = Mockito.mock(Abonne.class);
        Mockito.when(mockRegistre.nbEmpruntsEnCours(mockAbonne)).thenReturn(0);
        Mockito.when(mockAbonne.estBloque()).thenReturn(true);


        // objet sous test
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        s.setRegistre(mockRegistre);
        assertNull(s.emprunterVelo(mockAbonne,1));

    }
/*
    @Test
    public void testEmprunterVeloNbEmpruntPasNull() {

        Abonne mockAbonne = Mockito.mock(Abonne.class);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        Mockito.when(mockRegistre.nbEmpruntsEnCours(mockAbonne)).thenReturn(2);
        // objet sous test
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        s.setRegistre(mockRegistre);
        assertNull(s.emprunterVelo(mockAbonne,1));

    }

    @Test
    public void testEmprunterVeloCapaciteInf() {


        Abonne mockAbonne = Mockito.mock(Abonne.class);
        Mockito.when(mockAbonne.estBloque()).thenReturn(false);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        Mockito.when(mockRegistre.nbEmpruntsEnCours(mockAbonne)).thenReturn(0);
        // objet sous test
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        s.setRegistre(mockRegistre);
        assertNull(s.emprunterVelo(mockAbonne,0));

    }

    @Test
    public void testEmprunterVeloCapaciteSup() {


        Abonne mockAbonne = Mockito.mock(Abonne.class);
        Mockito.when(mockAbonne.estBloque()).thenReturn(false);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        //Mockito.when(mockRegistre.nbEmpruntsEnCours(mockAbonne)).thenReturn(0);
        // objet sous test
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        s.setRegistre(mockRegistre);
        assertNull(s.emprunterVelo(mockAbonne,12));

    }
    //test a==null
    //test registre==null

    /*@Test
    public void testEmprunterVelo() {


        Abonne mockAbonne = Mockito.mock(Abonne.class);
        Mockito.when(mockAbonne.estBloque()).thenReturn(false);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        Mockito.when(mockRegistre.nbEmpruntsEnCours(mockAbonne)).thenReturn(0);
        // objet sous test
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        s.setRegistre(mockRegistre);
        assertTrue(Ivelo,s.emprunterVelo(mockAbonne,5));

    }*/




}
