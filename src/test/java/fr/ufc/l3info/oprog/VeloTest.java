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
    @Test
    public void testVelo() {
        Velo v = new Velo();
        Assert.assertNotNull(v);
    }
    @Test
    public void testVeloF() {
        Velo v = new Velo('F');
        Assert.assertNotNull(v);
    }
    @Test
    public void testVelokilometrageSupOuEgaleZero() {
        Velo v = new Velo('F');
        v.kilometrage();
        Assert.assertTrue(v.kilometrage()>=0);
    }
    @Test
    public void testVelokilometrageJuste() {
        Velo v = new Velo('F');
        v.parcourir(100.0);
        Assert.assertEquals(100.0,v.kilometrage(),0);
    }
    @Test
    public void testVelokilometragePasJuste() {
        Velo v = new Velo('F');
        v.parcourir(100);
        v.kilometrage();
        Assert.assertNotEquals(300.0,v.kilometrage());
    }
    @Test
    public void testVeloProchaineRevisionDeBase() {
        Velo v = new Velo('?');
        Assert.assertEquals(500.0,v.prochaineRevision(),0);
    }
    @Test
    public void testVeloProchaineRevisionAFaireValeurNeg() {
        Velo v = new Velo('F');
        v.parcourir(600.0);
        Assert.assertEquals(-100.0,v.prochaineRevision(),0);
    }
    @Test
    public void testVeloParcourirEmprunter() {
        Velo v = new Velo('F');
        v.decrocher();
        v.parcourir(50.0);
        Assert.assertEquals(50.0,v.kilometrage(),0);
    }
    @Test
    public void testVeloParcourirPasEmprunter() {
        Velo v = new Velo('F');
        v.decrocher();
        v.arrimer();
        v.parcourir(50.0);
        Assert.assertNotEquals(50.0,v.kilometrage());
    }
    /*@Test
    public void TestParcourirPasPuEtreEffectuerVAleurNegative() {
        Velo v = new Velo('F');
        v.decrocher();
        v.parcourir(-60.0);
        Assert.assertEquals(0.0,v.kilometrage(),0);
    }*/
    @Test
    public void testVeloTarifJuste1() {
        Velo v = new Velo('F');
        Assert.assertEquals(2.0,v.tarif(),0);
    }
    @Test
    public void testVeloTarifJuste2() {
        Velo v = new Velo('F');
        Assert.assertTrue(2.0==v.tarif());
    }
    @Test
    public void testVeloTarifFalse() {
        Velo v = new Velo('F');
        Assert.assertFalse(v.tarif()!=2.0);
    }
    @Test
    public void testVeloDecrocherValeurJuste() {
        Velo v = new Velo('F');
        Assert.assertTrue(v.decrocher()==0 || v.decrocher()==-1);
    }

    @Test
    public void testVeloDecrocher() {
        Velo v = new Velo('F');
        v.arrimer();
        Assert.assertEquals(0,v.decrocher());
    }

    @Test
    public void testVeloDejaDecrocher() {
        Velo v = new Velo('F');
        v.arrimer();
        Assert.assertEquals(0,v.decrocher());
        Assert.assertEquals(-1,v.decrocher());
    }
    @Test
    public void testVeloAccrocherValeurJuste() {
        Velo v = new Velo('F');
        Assert.assertTrue(v.arrimer()==0 || v.arrimer()==-1);
    }

    @Test
    public void testVeloAccrocher() {
        Velo v = new Velo('F');
        v.decrocher();
        Assert.assertEquals(0,v.arrimer());
    }
    @Test
    public void testVeloDejaAccrocher() {
        Velo v = new Velo('F');
        v.decrocher();
        Assert.assertEquals(0,v.arrimer());
        Assert.assertEquals(-1,v.arrimer());
    }
    @Test
    public void testAbimer() {
        Velo v = new Velo('F');
        v.abimer();
        Assert.assertTrue(v.estAbime());
    }
    @Test
    public void testAbimerCarPasDecrocherPourRevision() {
        Velo v = new Velo('F');
        v.abimer();
        v.arrimer();
        v.reviser();
        Assert.assertEquals(true, v.estAbime());
    }
    @Test
    public void testAbimerCarPasDecrocherPourReparation() {
        Velo v = new Velo('F');
        v.abimer();
        v.arrimer();
        v.reparer();
        Assert.assertEquals(true, v.estAbime());
    }
    @Test
    public void testPasAbimer() {
        Velo v = new Velo('F');
        Assert.assertFalse(v.estAbime());
    }
    /*@Test
    public void testPasAbimerApresRevision() {
        Velo v = new Velo('F');
        v.decrocher();
        v.abimer();
        v.reviser();
        Assert.assertFalse(v.estAbime());
    }
    @Test
    public void testPasAbimerApresReparation() {
        Velo v = new Velo('F');
        v.decrocher();
        v.abimer();
        v.reparer();
        Assert.assertFalse(v.estAbime());

    }*/
    @Test
    public void testPasAbimerPourReparation() {
        Velo v = new Velo('F');
        v.arrimer();
        v.decrocher();
        v.reparer();
        Assert.assertEquals(-2,v.reparer());

    }
    @Test
    public void TestRevisionValeurJuste() {
        Velo v = new Velo('F');
        Assert.assertTrue(v.reviser()==0 || v.reviser()==-1);
    }

    @Test
    public void TestRevisionEffectuer() {
        Velo v = new Velo('F');
        v.decrocher();
        v.parcourir(60.0);
        Assert.assertEquals(60.0,v.kilometrage(),0);
        Assert.assertEquals(440.0,v.prochaineRevision(),0);
        v.reviser();
        Assert.assertEquals(500.0,v.prochaineRevision(),0);
        Assert.assertEquals(0,v.reviser());
    }
    @Test
    public void TestRevisionPasPuEtreEffectuer() {
        Velo v = new Velo('F');
        v.decrocher();
        v.parcourir(60.0);
        Assert.assertEquals(60.0,v.kilometrage(),0);
        v.arrimer();
        v.reviser();
        Assert.assertEquals(60.0,v.kilometrage(),0);
        Assert.assertEquals(-1,v.reviser());
    }
    @Test
    public void TestStringFemme(){
        Velo v = new Velo('F');
        Assert.assertEquals("Vélo cadre femme - 0.0 km", v.toString());
    }
    @Test
    public void TestStringHomme(){
        Velo v = new Velo('H');
        Assert.assertEquals("Vélo cadre homme - 0.0 km", v.toString());
    }
    @Test
    public void TestStringf(){
        Velo v = new Velo('f');
        Assert.assertEquals("Vélo cadre femme - 0.0 km", v.toString());
    }
    @Test
    public void TestStringh(){
        Velo v = new Velo('h');
        Assert.assertEquals("Vélo cadre homme - 0.0 km", v.toString());
    }
    @Test
    public void TestStringMixteSansValeur(){
        Velo v = new Velo();
        Assert.assertEquals("Vélo cadre mixte - 0.0 km", v.toString());
    }
    /*@Test
    public void TestStringValeurArrondiSup(){
        Velo v = new Velo('x');
        v.parcourir(45.67);
        Assert.assertEquals("Vélo cadre mixte - 45.7 km", v.toString());
    }
    @Test
    public void TestStringValeurArrondiInf(){
        Velo v = new Velo('x');
        v.parcourir(45.33);
        Assert.assertEquals("Vélo cadre mixte - 45.3 km", v.toString());
    }
    @Test
    public void TestStringValeurArrondiEgal(){
        Velo v = new Velo('x');
        v.parcourir(45.35);
        Assert.assertEquals("Vélo cadre mixte - 45.4 km", v.toString());
    }
    @Test
    public void TestStringRevision(){
        Velo v = new Velo('x');
        v.parcourir(600.35);
        Assert.assertEquals("Vélo cadre mixte - 600.4 km (révision nécessaire)", v.toString());
    }
    @Test
    public void TestStringMixteAvecValeur(){
        Velo v = new Velo('x');
        Assert.assertEquals("Vélo cadre mixte - 0.0 km", v.toString());
    }*/



}
