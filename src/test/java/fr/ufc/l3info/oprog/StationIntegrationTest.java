package fr.ufc.l3info.oprog;

import org.junit.Test;
import org.mockito.Mock;



import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class StationIntegrationTest {
    private static final double DELTA = 1e-3;

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

    /*test emprunterVelo*/
    @Test
    public void testEmprunterVeloAbonneBloque () throws IncorrectNameException{
        // création d'un registre, d'un abonne et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");

        // vérification de l'emprunt avec abonne bloque
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        s.setRegistre(registre);
        assertNull(s.emprunterVelo(abonne,1));

    }

    @Test
    public void testEmprunterVeloNbEmpruntPasNull() throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("Jade","12345-12345-12345678912-13");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);
        registre.emprunter(abonne, velo, s.maintenant());

        // vérification de l'emprunt avec nombre d'emprunt différent de null
        assertNull(s.emprunterVelo(abonne,1));

    }


    @Test
    public void testEmprunterVeloCapaciteInf() throws IncorrectNameException{
        // création d'un registre, d'un abonne et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("Jade","12345-12345-12345678912-13");
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'emprunt avec une borne inférieur
        assertNull(s.emprunterVelo(abonne,0));

    }


    @Test
    public void testEmprunterVeloCapaciteSup() throws IncorrectNameException{
        // création d'un registre, d'un abonne et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("Jade","12345-12345-12345678912-13");
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'emprunt avec une borne supérieur
        assertNull(s.emprunterVelo(abonne,12));

    }
    @Test
    public void testEmprunterVeloRegistreNull() throws IncorrectNameException{
        // création d'un abonne et d'une station
       Abonne abonne = new Abonne("Jade","12345-12345-12345678912-13");
       Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

       // vérification de l'emprunt avec un registre null
       assertNull(s.emprunterVelo(abonne,12));

    }

    @Test
    public void testEmprunterVeloAbonneNull(){
        // création d'un registre et d'une station
        IRegistre registre = new JRegistre();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'emprunt avec un abonne null
        assertNull(s.emprunterVelo(null,5));

    }

    @Test
    public void testEmprunterVelo()throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("Jade","12345-12345-12345678912-13");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);
        s.arrimerVelo(velo,5);

        // vérification de l'emprunt valide
        assertEquals(velo,s.emprunterVelo(abonne,5));

    }

    /*test arrimerVelo*/

    @Test
    public void testArrimerVeloNull(){
        // création d'un registre et d'une station
        IRegistre registre = new JRegistre();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'arrimage avec un velo null
        assertEquals(-1,s.arrimerVelo(null,5));

    }

    @Test
    public void testArrimerVeloBorneInf(){
        // création d'un registre, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'arrimage avec une borne inférieur
        assertEquals(-1,s.arrimerVelo(velo,0));

    }
    @Test
    public void testArrimerVeloBorneSup(){
        // création d'un registre, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'arrimage avec une borne superieur
        assertEquals(-1,s.arrimerVelo(velo,12));

    }

    @Test
    public void testArrimerVeloDejaPris(){
        // création d'un registre, des velos et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        IVelo velo2 = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);
        s.arrimerVelo(velo,5);

        // vérification de l'arrimage avec un velo qui est deja pris
        assertEquals(-2,s.arrimerVelo(velo2,5));

    }

    @Test
    public void testArrimerVeloRegistreNull(){
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification de l'arrimage avec un registre null
        assertEquals(-2,s.arrimerVelo(velo,5));

    }


    /*test nbBornesLibres*/
    @Test
    public void testNbBornesLibres(){
        // création d'un registre, des velos et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        IVelo velo2 = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,5);

        s.setRegistre(registre);
        s.arrimerVelo(velo,1);
        s.arrimerVelo(velo2,2);

        // vérification du nombre de bornes libres
        assertEquals(3,s.nbBornesLibres());

    }

    /*test veloALaBorne*/
    @Test
    public void testVeloALaBorneInf(){
        // création d'une station
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du velo à une borne inferieur
        assertNull(s.veloALaBorne(0));

    }

    @Test
    public void testVeloALaBorneSup(){
        // création d'une station
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du velo à une borne superieur
        assertNull(s.veloALaBorne(12));

    }

    @Test
    public void testVeloALaBorneNull(){
        // création d'une station
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du velo à une borne
        assertNull(s.veloALaBorne(5));

    }

    @Test
    public void testVeloALaBorne(){
        // création d'un registre, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);
        s.arrimerVelo(velo,5);

        // vérification du velo à une borne
        assertEquals(velo,s.veloALaBorne(5));

    }


    /*test distance*/
    @Test
    public void testDistance(){
        // création de deux stations
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        Station s2 = new Station("Granvelle",  47.23531038159631,6.025353690286829,9);

        // vérification de la distance entre deux bornes
        assertEquals(73.227,s.distance(s2),DELTA);

    }


}
