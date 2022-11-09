package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test unitaire pour les abonnés.
 */
public class VeloTest {
    private static final double DELTA = 1e-15;

    /*Test constructeur sans parametre*/
    @Test
    public void testVeloConstructeurVideFaux() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérification de son type de cadre
        assertFalse(v.toString().contains(" homme "));

    }
    @Test
    public void testVeloConstructeurVideJuste() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérification de son type de cadre
        assertTrue(v.toString().contains(" mixte "));

    }

    /*Test constructeur avec parametre*/

    @Test
    public void testVeloConstructeurParametreH() {
        // création d'un nouveau velo
        Velo v = new Velo('H');

        // vérification de son type de cadre
        assertTrue(v.toString().contains(" homme "));

    }
    @Test
    public void testVeloConstructeurParametreF() {
        // création d'un nouveau velo
        Velo v = new Velo('F');

        // vérification de son type de cadre
        assertTrue(v.toString().contains(" femme "));

    }

    @Test
    public void testVeloConstructeurParametref() {
        // création d'un nouveau velo
        Velo v = new Velo('f');

        // vérification de son type de cadre
        assertTrue(v.toString().contains(" femme "));

    }
    @Test
    public void testVeloConstructeurParametreh() {
        // création d'un nouveau velo
        Velo v = new Velo('h');

        // vérification de son type de cadre
        assertTrue(v.toString().contains(" homme "));

    }

    @Test
    public void testVeloConstructeurParametrek() {
        // création d'un nouveau velo
        Velo v = new Velo('k');

        // vérification de son type de cadre
        assertFalse(v.toString().contains(" k "));

    }
    @Test
    public void testVeloConstructeurParametreNull() {
        // création d'un nouveau velo
        Velo v = new Velo(' ');

        // vérification de son type de cadre
        assertFalse(v.toString().contains("null"));

    }
    @Test
    public void testVeloConstructeurParametre1() {
        // création d'un nouveau velo
        Velo v = new Velo('1');

        // vérification de son type de cadre
        assertFalse(v.toString().contains("1"));

    }




    /*Test kilometrage*/

    @Test
    public void testKilometrageCreation() {
        // création d'un nouveau velo
        Velo v = new Velo('H');

        // vérification de son kilometrage
        assertEquals(0, v.kilometrage(), DELTA);

    }



    /*Test parcourir*/

   @Test
    public void testKilometrageApresParcours() {
        // création d'un nouveau velo
        Velo v = new Velo('F');
        v.parcourir(25.8);

        // vérification de son kilometrage après parcours
        assertEquals(25.8, v.kilometrage(), DELTA);

    }
    @Test
    public void testKilometrageApresParcoursFaux() {
        // création d'un nouveau velo
        Velo v = new Velo('F');
        v.arrimer();
        v.parcourir(25.8);

        // vérification de son kilometrage après parcours
        assertEquals(0, v.kilometrage(), DELTA);

    }

    /*Test prochaine revision*/
   @Test
    public void testProchaineRevision() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.parcourir(25.8);

        // vérification de sa prochaine révision
        assertEquals(474.2, v.prochaineRevision(), DELTA);

    }
    @Test
    public void testProchaineRevision0() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.parcourir(200);
        v.parcourir(15);

        // vérification de sa prochaine révision
        assertEquals(285, v.prochaineRevision(), DELTA);

    }

    @Test
    public void testProchaineRevision1() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.parcourir(200);
        v.parcourir(300);
        if(v.prochaineRevision()==0){
            v.reviser();
        }
        v.parcourir(15);

        // vérification de sa prochaine révision
        assertEquals(485, v.prochaineRevision(), DELTA);

    }

    @Test
    public void testProchaineRevision2() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.parcourir(200);
        v.parcourir(300);

        // vérification de sa prochaine révision
        assertEquals(0, v.prochaineRevision(), DELTA);

    }

    /*Test tarif*/
    @Test
    public void testTarifJuste() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérification de son tarif
        assertEquals(2, v.tarif(), DELTA);

    }

    /*Test decrocher*/
    @Test
    public void testDecrocher() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.arrimer();

        // vérifié s'il est décroché
        assertEquals(0, v.decrocher());

    }

    @Test
    public void testDejaDecrocher() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérifié s'il est déjà décroché
        assertEquals(-1, v.decrocher());

    }

    /*Test arrimer*/
    @Test
    public void testArrimer() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérifié s'il y est arrimé
        assertEquals(0, v.arrimer());

    }
    @Test
    public void testDejaArrimer() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.arrimer();

        // vérifié s'il est déjà arrimé
        assertEquals(-1, v.arrimer());

    }

    /*Test abimer*/
    @Test
    public void testAbimerVrai() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.abimer();

        // vérifié s'il est abimé
        assertEquals(true, v.estAbime());

    }
    @Test
    public void testAbimerFaux() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérifié s'il est pas abimé
        assertEquals(false, v.estAbime());

    }

    /*Test reviser*/
    @Test
    public void testReviserDecrocheAbime() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.abimer();

        // vérifié s'il a été révisé
        assertEquals(0, v.reviser());

    }
    @Test
    public void testReviserDecrochePasAbime() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérifié s'il a été révisé
        assertEquals(0, v.reviser());

    }
    @Test
    public void testReviserPasDecroche() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.arrimer();

        // vérifié qu'il n'a pas été révisé
        assertEquals(-1, v.reviser());

    }

    /*Test reparer*/
    @Test
    public void testReparer() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.abimer();

        // vérification de sa réparation
        assertEquals(0, v.reparer());

    }
    @Test
    public void testPasReparerAccroche() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.abimer();
        v.arrimer();

        // vérification de sa réparation
        assertEquals(-1, v.reparer());

    }
    @Test
    public void testPasReparerAbime() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérification de sa réparation
        assertEquals(-2, v.reparer());

    }

    /*test toString*/
    @Test
    public void testToString() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.parcourir(200.756);

        // vérification de son type de cadre
        assertTrue(v.toString().contains(" mixte ") && v.toString().contains(" 200.8 ") );

    }

    @Test
    public void testToString2() {
        // création d'un nouveau velo
        Velo v = new Velo('f');
        v.parcourir(200.756);

        // vérification de son type de cadre
        assertTrue(v.toString().contains(" femme ") && v.toString().contains(" 200.8 ") );

    }

    @Test
    public void testToString3() {
        // création d'un nouveau velo
        Velo v = new Velo('h');
        v.parcourir(200.756);

        // vérification de son type de cadre
        assertTrue(v.toString().contains(" homme ") && v.toString().contains(" 200.8 ") );

    }

    @Test
    public void testToString4() {
        // création d'un nouveau velo
        Velo v = new Velo('F');
        v.parcourir(200.756);

        // vérification de son type de cadre
        assertTrue(v.toString().contains(" femme ") && v.toString().contains(" 200.8 ") );

    }

    @Test
    public void testToString5() {
        // création d'un nouveau velo
        Velo v = new Velo('H');
        v.parcourir(200.756);

        // vérification de son type de cadre
        assertTrue(v.toString().contains(" homme ") && v.toString().contains(" 200.8 ") );

    }

    @Test
    public void testToStringFalse() {
        // création d'un nouveau velo
        Velo v = new Velo('H');
        v.parcourir(200.756);

        // vérification de son type de cadre
        assertFalse(v.toString().contains(" homme ") && v.toString().contains(" 200.756 ") );

    }

    @Test
    public void testToStringRevisonTrue() {
        // création d'un nouveau velo
        Velo v = new Velo('H');
        v.parcourir(200);
        v.parcourir(300);
        v.prochaineRevision();
        // vérification de son type de cadre
        assertTrue(v.toString().contains(" homme ") && v.toString().contains(" 500.0 ") && v.toString().contains(" (révision nécessaire)"));

    }

    @Test
    public void testToStringRevisonFalse() {
        // création d'un nouveau velo
        Velo v = new Velo('H');
        v.parcourir(200);
        v.prochaineRevision();
        // vérification de son type de cadre
        assertFalse(v.toString().contains(" homme ") && v.toString().contains(" 200.0 ") && v.toString().contains(" (révision nécessaire)"));

    }


    @Test
    public void testConstructeurSansParametre() {
        Velo v = new Velo();
        assertTrue(v.toString().contains("mixte"));
    }

    @Test
    public void testConstructeurTypeH() {
        Velo v1 = new Velo('H');
        Velo v2 = new Velo('h');
        assertTrue(v1.toString().contains("homme"));
        assertTrue(v2.toString().contains("homme"));
    }

    @Test
    public void testConstructeurTypeF() {
        Velo v1 = new Velo('F');
        Velo v2 = new Velo('f');
        assertTrue(v1.toString().contains("femme"));
        assertTrue(v2.toString().contains("femme"));
    }

    @Test
    public void testConstructeurTypeMixte() {
        Velo v = new Velo('4');
        assertTrue(v.toString().contains("mixte"));
    }

    //Test les autres attributs
    @Test
    public void testConstructeurKmDepart() {
        Velo v1 = new Velo();
        Velo v2 = new Velo('h');
        assertEquals(0,v1.kilometrage(),DELTA);
        assertEquals(0,v2.kilometrage(),DELTA);
    }

    @Test
    public void testConstructeurTarifHoraire() {
        Velo v1 = new Velo();
        Velo v2 = new Velo('h');
        assertEquals(2.0,v1.tarif(),DELTA);
        assertEquals(2.0,v2.tarif(),DELTA);
    }

    @Test
    public void testConstructeurProchaineRevision() {
        Velo v = new Velo();
        assertEquals(500,v.prochaineRevision(),DELTA);
    }

    @Test
    public void testConstructeurEstDecroche() {
        Velo v = new Velo();
        assertEquals(-1,v.decrocher());
    }

    @Test
    public void testConstructeurPasAbime() {
        Velo v = new Velo();
        assertFalse(v.estAbime());
    }

    /**
     * Tests évolution de l'état du vélo
     */

    private Velo v;

    @Before
    public void setUp() {
        v  = new Velo();
    }

    //Test de décrocher un vélo non abimé
    @Test
    public void testDecrocherVeloDejaDecroche_OK() {
        assertEquals(-1,v.decrocher());
    }

    @Test
    public void testDecrocherVeloAccroche_OK() {
        v.arrimer();
        assertEquals(0,v.decrocher());
    }

    //Test d'accrocher un vélo non abimé
    @Test
    public void testAccrocherVeloDecroche_OK() {
        assertEquals(0,v.arrimer());
    }

    @Test
    public void testAccrocherVeloDejaAccroche_OK() {
        v.arrimer();
        assertEquals(-1,v.arrimer());
    }

    //Test d'abimer un vélo
    @Test
    public void testAbimerVeloDecroche() {
        v.abimer();
        assertTrue(v.estAbime());
    }

    @Test
    public void testAbimerVeloAccroche() {
        v.arrimer();
        v.abimer();
        assertTrue(v.estAbime());
    }

    //Test de décrocher un vélo abimé
    @Test
    public void testDecrocherVeloDejaDecroche_Abime() {
        v.abimer();
        assertEquals(-1,v.decrocher());
    }

    @Test
    public void testDecrocherVeloAccroche_Abime() {
        v.abimer();
        v.arrimer();
        assertEquals(0,v.decrocher());
    }

    //Test d'accrocher un vélo abimé
    @Test
    public void testAccrocherVeloDecroche_Abime() {
        v.abimer();
        assertEquals(0,v.arrimer());
    }

    @Test
    public void testAccrocherVeloDejaAccroche_Abime() {
        v.abimer();
        v.arrimer();
        assertEquals(-1,v.arrimer());
    }

    /**
     * Tests réparation du vélo
     */
    //Test de réparer un vélo abimé
    @Test
    public void testReparerVeloDecroche_Abime() {
        v.abimer();
        assertEquals(0,v.reparer());
    }

    @Test
    public void testReparerVeloAccroche_Abime() {
        v.abimer();
        v.arrimer();
        assertEquals(-1,v.reparer());
    }

    //Test de réparer un vélo non abimé
    @Test
    public void testReparerVeloDecroche_Ok() {
        assertEquals(-2,v.reparer());
    }

    /**
     * Tests parcours et révision vélo
     */
    //Test de parcourir une distance avec un vélo
    @Test
    public void testParcourirVeloAccroche_Ok() {
        v.arrimer();
        v.parcourir(40.0);
        assertEquals(0,v.kilometrage(), DELTA);
    }

    @Test
    public void testParcourirVeloDecroche_Ok() {
        double distance=40.0;
        v.parcourir(distance);
        assertEquals(distance,v.kilometrage(), DELTA);
    }

    @Test
    public void testParcourirVeloAccroche_Abime() {
        v.arrimer();
        v.abimer();
        v.parcourir(40.0);
        assertEquals(0,v.kilometrage(), DELTA);
    }

    @Test
    public void testParcourirVeloDecroche_Abime() {
        v.abimer();
        double distance=40.0;
        v.parcourir(distance);
        assertEquals(distance,v.kilometrage(), DELTA);
    }

    @Test
    public void testParcourirPlusieursFois() {
        double distance1=40.0;
        v.parcourir(distance1);
        double distance2=100.0;
        v.parcourir(distance2);
        assertEquals(distance1+distance2,v.kilometrage(), DELTA);
    }

    @Test
    public void testParcourirValeurNegative() {
        v.parcourir(-40.0);
        assertEquals(0,v.kilometrage(), DELTA);
    }

    //Test combien de kilomètres il reste avant la révision
    @Test
    public void testSiBesoinReviserVelo500km() {
        v.parcourir(500);
        assertEquals(0,v.prochaineRevision(), DELTA);
    }

    @Test
    public void testSiBesoinReviserVeloPlus500km() {
        v.parcourir(500.0000001);
        assertTrue(0>v.prochaineRevision());
    }

    @Test
    public void testSiBesoinReviserMoinsVelo500km() {
        v.parcourir(499.99999999);
        assertTrue(0<v.prochaineRevision());
    }

    //Test la révision d'un vélo décroché
    @Test
    public void testReviserVeloDecroche_Ok_Plus500() {
        v.parcourir(600.00);
        assertEquals(0,v.reviser());
    }

    @Test
    public void testReviserVeloDecroche_Ok_Moins500() {
        v.parcourir(200.00);
        assertEquals(0,v.reviser());
    }

    @Test
    public void testReviserVeloDecroche_Abime() {
        v.abimer();
        v.parcourir(300.00);
        assertEquals(0,v.reviser());
        assertEquals(0,v.kilometrage(), DELTA);
        //Test si la réparation a eu lieu pendant la révision
        assertEquals(-2,v.reparer());
    }

    //Test la révision d'un vélo accroché
    @Test
    public void testReviserVeloAccroche() {
        v.arrimer();
        v.parcourir(600.00);
        assertEquals(-1,v.reviser());
    }

    @Test
    public void testReviserVeloAccroche_Abime() {
        v.parcourir(300);
        v.arrimer();
        v.abimer();
        assertEquals(-1,v.reviser());
        assertEquals(300,v.kilometrage(), DELTA);
        //Test si la réparation a eu lieu pendant la révision
        v.decrocher();
        assertEquals(0,v.reparer());
    }

    /**
     * Tests toString
     */

    @Test
    public void testToStringRevision() {
        v.parcourir(600);
        assertTrue(v.toString().contains("(révision nécessaire)"));
    }

    @Test
    public void testToStringPasRevision() {
        assertFalse(v.toString().contains("(révision nécessaire)"));
    }

    @Test
    public void testToStringKmArrondiDecimal() {
        v.parcourir(327.37);
        assertTrue(v.toString().contains(" 327.4 "));
    }

    @Test
    public void testToStringKmArrondiUnite() {
        v.parcourir(99.99);
        assertTrue(v.toString().contains(" 100.0 "));
    }

    @Test
    public void testToStringKmSansDecimal() {
        v.parcourir(250);
        assertTrue(v.toString().contains(" 250.0 "));
    }




}
