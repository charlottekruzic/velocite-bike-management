package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;



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
        Assert.assertFalse(v.toString().contains(" homme "));

    }
    @Test
    public void testVeloConstructeurVideJuste() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" mixte "));

    }

    /*Test constructeur avec parametre*/

    @Test
    public void testVeloConstructeurParametreH() {
        // création d'un nouveau velo
        Velo v = new Velo('H');

        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" homme "));

    }
    @Test
    public void testVeloConstructeurParametreF() {
        // création d'un nouveau velo
        Velo v = new Velo('F');

        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" femme "));

    }

    @Test
    public void testVeloConstructeurParametref() {
        // création d'un nouveau velo
        Velo v = new Velo('f');

        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" femme "));

    }
    @Test
    public void testVeloConstructeurParametreh() {
        // création d'un nouveau velo
        Velo v = new Velo('h');

        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" homme "));

    }

    @Test
    public void testVeloConstructeurParametrek() {
        // création d'un nouveau velo
        Velo v = new Velo('k');

        // vérification de son type de cadre
        Assert.assertFalse(v.toString().contains(" k "));

    }
    @Test
    public void testVeloConstructeurParametreNull() {
        // création d'un nouveau velo
        Velo v = new Velo(' ');

        // vérification de son type de cadre
        Assert.assertFalse(v.toString().contains("null"));

    }
    @Test
    public void testVeloConstructeurParametre1() {
        // création d'un nouveau velo
        Velo v = new Velo('1');

        // vérification de son type de cadre
        Assert.assertFalse(v.toString().contains("1"));

    }




    /*Test kilometrage*/

    @Test
    public void testKilometrageCreation() {
        // création d'un nouveau velo
        Velo v = new Velo('H');

        // vérification de son kilometrage
        Assert.assertEquals(0, v.kilometrage(), DELTA);

    }



    /*Test parcourir*/

   @Test
    public void testKilometrageApresParcours() {
        // création d'un nouveau velo
        Velo v = new Velo('F');
        v.parcourir(25.8);

        // vérification de son kilometrage après parcours
        Assert.assertEquals(25.8, v.kilometrage(), DELTA);

    }
    @Test
    public void testKilometrageApresParcoursFaux() {
        // création d'un nouveau velo
        Velo v = new Velo('F');
        v.arrimer();
        v.parcourir(25.8);

        // vérification de son kilometrage après parcours
        Assert.assertEquals(0, v.kilometrage(), DELTA);

    }

    /*Test prochaine revision*/
   @Test
    public void testProchaineRevision() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.parcourir(25.8);

        // vérification de sa prochaine révision
        Assert.assertEquals(474.2, v.prochaineRevision(), DELTA);

    }
    @Test
    public void testProchaineRevision0() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.parcourir(200);
        v.parcourir(15);

        // vérification de sa prochaine révision
        Assert.assertEquals(285, v.prochaineRevision(), DELTA);

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
        Assert.assertEquals(485, v.prochaineRevision(), DELTA);

    }

    @Test
    public void testProchaineRevision2() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.parcourir(200);
        v.parcourir(300);

        // vérification de sa prochaine révision
        Assert.assertEquals(0, v.prochaineRevision(), DELTA);

    }

    /*Test tarif*/
    @Test
    public void testTarifJuste() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérification de son tarif
        Assert.assertEquals(2, v.tarif(), DELTA);

    }

    /*Test decrocher*/
    @Test
    public void testDecrocher() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.arrimer();

        // vérifié s'il est décroché
        Assert.assertEquals(0, v.decrocher());

    }

    @Test
    public void testDejaDecrocher() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérifié s'il est déjà décroché
        Assert.assertEquals(-1, v.decrocher());

    }

    /*Test arrimer*/
    @Test
    public void testArrimer() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérifié s'il y est arrimé
        Assert.assertEquals(0, v.arrimer());

    }
    @Test
    public void testDejaArrimer() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.arrimer();

        // vérifié s'il est déjà arrimé
        Assert.assertEquals(-1, v.arrimer());

    }

    /*Test abimer*/
    @Test
    public void testAbimerVrai() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.abimer();

        // vérifié s'il est abimé
        Assert.assertEquals(true, v.estAbime());

    }
    @Test
    public void testAbimerFaux() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérifié s'il est pas abimé
        Assert.assertEquals(false, v.estAbime());

    }

    /*Test reviser*/
    @Test
    public void testReviserDecrocheAbime() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.abimer();

        // vérifié s'il a été révisé
        Assert.assertEquals(0, v.reviser());

    }
    @Test
    public void testReviserDecrochePasAbime() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérifié s'il a été révisé
        Assert.assertEquals(0, v.reviser());

    }
    @Test
    public void testReviserPasDecroche() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.arrimer();

        // vérifié qu'il n'a pas été révisé
        Assert.assertEquals(-1, v.reviser());

    }

    /*Test reparer*/
    @Test
    public void testReparer() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.abimer();

        // vérification de sa réparation
        Assert.assertEquals(0, v.reparer());

    }
    @Test
    public void testPasReparerAccroche() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.abimer();
        v.arrimer();

        // vérification de sa réparation
        Assert.assertEquals(-1, v.reparer());

    }
    @Test
    public void testPasReparerAbime() {
        // création d'un nouveau velo
        Velo v = new Velo();

        // vérification de sa réparation
        Assert.assertEquals(-2, v.reparer());

    }

    /*test toString*/
    @Test
    public void testToString() {
        // création d'un nouveau velo
        Velo v = new Velo();
        v.parcourir(200.756);

        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" mixte ") && v.toString().contains(" 200.8 ") );

    }

    @Test
    public void testToString2() {
        // création d'un nouveau velo
        Velo v = new Velo('f');
        v.parcourir(200.756);

        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" femme ") && v.toString().contains(" 200.8 ") );

    }

    @Test
    public void testToString3() {
        // création d'un nouveau velo
        Velo v = new Velo('h');
        v.parcourir(200.756);

        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" homme ") && v.toString().contains(" 200.8 ") );

    }

    @Test
    public void testToString4() {
        // création d'un nouveau velo
        Velo v = new Velo('F');
        v.parcourir(200.756);

        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" femme ") && v.toString().contains(" 200.8 ") );

    }

    @Test
    public void testToString5() {
        // création d'un nouveau velo
        Velo v = new Velo('H');
        v.parcourir(200.756);

        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" homme ") && v.toString().contains(" 200.8 ") );

    }

    @Test
    public void testToStringFalse() {
        // création d'un nouveau velo
        Velo v = new Velo('H');
        v.parcourir(200.756);

        // vérification de son type de cadre
        Assert.assertFalse(v.toString().contains(" homme ") && v.toString().contains(" 200.756 ") );

    }

    @Test
    public void testToStringRevisonTrue() {
        // création d'un nouveau velo
        Velo v = new Velo('H');
        v.parcourir(200);
        v.parcourir(300);
        v.prochaineRevision();
        // vérification de son type de cadre
        Assert.assertTrue(v.toString().contains(" homme ") && v.toString().contains(" 500.0 ") && v.toString().contains(" (révision nécessaire)"));

    }

    @Test
    public void testToStringRevisonFalse() {
        // création d'un nouveau velo
        Velo v = new Velo('H');
        v.parcourir(200);
        v.prochaineRevision();
        // vérification de son type de cadre
        Assert.assertFalse(v.toString().contains(" homme ") && v.toString().contains(" 200.0 ") && v.toString().contains(" (révision nécessaire)"));

    }




}
