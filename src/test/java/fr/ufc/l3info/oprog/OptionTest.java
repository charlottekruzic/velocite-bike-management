package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OptionTest {
    private static final double DELTA = 1e-15;

    /*Test OptCadreAlu*/
    @Test
    public void testOptionOptCadreAluTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptCadreAlu o = new OptCadreAlu(velo);

        // vérification du tarif
        assertEquals(2.2,o.tarif(),DELTA);
    }

    @Test
    public void testOptionOptCadreAluToString() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptCadreAlu o = new OptCadreAlu(velo);

        // vérification de l'option
        assertTrue(o.toString().contains("cadre aluminium"));
    }

    /*Test OptFreinsDisque*/
    @Test
    public void testOptionOptFreinsDisqueTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptFreinsDisque o = new OptFreinsDisque(velo);

        // vérification du tarif
        assertEquals(2.3,o.tarif(),DELTA);
    }

    @Test
    public void testOptionOptFreinsDisqueToString() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptFreinsDisque o = new OptFreinsDisque(velo);

        // vérification de l'option
        assertTrue(o.toString().contains("freins à disque"));
    }

    /*Test OptSuspensionAvant*/
    @Test
    public void testOptionOptSuspensionAvantTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptSuspensionAvant o = new OptSuspensionAvant(velo);

        // vérification du tarif
        assertEquals(2.5,o.tarif(),DELTA);
    }

    @Test
    public void testOptionOptSuspensionAvantToString() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptSuspensionAvant o = new OptSuspensionAvant(velo);

        // vérification de l'option
        assertTrue(o.toString().contains("suspension avant"));
    }

    /*Test OptSuspensionArriere*/
    @Test
    public void testOptionOptSuspensionArriereTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptSuspensionArriere o = new OptSuspensionArriere(velo);

        // vérification du tarif
        assertEquals(2.5,o.tarif(),DELTA);
    }

    @Test
    public void testOptionOptSuspensionArriereToString() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptSuspensionArriere o = new OptSuspensionArriere(velo);

        // vérification de l'option
        assertTrue(o.toString().contains("suspension arrière"));
    }

    /*Test OptAssistanceElectrique*/
    @Test
    public void testOptAssistanceElectriqueTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptAssistanceElectrique o = new OptAssistanceElectrique(velo);

        // vérification du tarif
        assertEquals(4,o.tarif(),DELTA);
    }

    @Test
    public void testOptAssistanceElectriqueToString() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptAssistanceElectrique o = new OptAssistanceElectrique(velo);
        System.out.println(o.toString());
        // vérification de l'option
        assertTrue(o.toString().contains(" assistance électrique"));
    }

    /*Test velo avec plusieurs option*/
    @Test
    public void testOpt() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo= new OptAssistanceElectrique(velo);
        velo= new OptSuspensionArriere(velo);
        System.out.println(velo.toString());
        // vérification des options
        assertTrue(velo.toString().contains("assistance électrique") && velo.toString().contains("suspension arrière"));
    }

    @Test
    public void testOptTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo= new OptAssistanceElectrique(velo);
        velo= new OptSuspensionArriere(velo);

        // vérification du tarif
        assertEquals(4.5, velo.tarif(), DELTA);
    }



    @Test
    public void testKilometrageCreation() {
        // création d'un nouveau velo
        IVelo velo = new Velo('H');
        velo = new OptCadreAlu(velo);
        // vérification de son kilometrage
        assertEquals(0, velo.kilometrage(), DELTA);

    }

    @Test
    public void testKilometrageApresParcours() {
        // création d'un nouveau velo
        IVelo velo = new Velo('F');
        velo = new OptCadreAlu(velo);
        velo.parcourir(25.8);

        // vérification de son kilometrage après parcours
        assertEquals(25.8, velo.kilometrage(), DELTA);

    }

    @Test
    public void testProchaineRevision() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.parcourir(25.8);

        // vérification de sa prochaine révision
        assertEquals(474.2, velo.prochaineRevision(), DELTA);

    }


    @Test
    public void testProchaineRevision1() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.parcourir(200);
        velo.parcourir(300);
        if(velo.prochaineRevision()==0){
            velo.reviser();
        }
        velo.parcourir(15);

        // vérification de sa prochaine révision
        assertEquals(485, velo.prochaineRevision(), DELTA);

    }

    @Test
    public void testProchaineRevision2() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.parcourir(200);
        velo.parcourir(300);

        // vérification de sa prochaine révision
        assertEquals(0, velo.prochaineRevision(), DELTA);

    }

    @Test
    public void testDecrocher() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.arrimer();

        // vérifié s'il est décroché
        assertEquals(0, velo.decrocher());

    }

    @Test
    public void testDejaDecrocher() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);

        // vérifié s'il est déjà décroché
        assertEquals(-1, velo.decrocher());

    }

    @Test
    public void testArrimer() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);

        // vérifié s'il y est arrimé
        assertEquals(0, velo.arrimer());

    }
    @Test
    public void testDejaArrimer() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.arrimer();

        // vérifié s'il est déjà arrimé
        assertEquals(-1, velo.arrimer());

    }

    @Test
    public void testAbimerVrai() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.abimer();

        // vérifié s'il est abimé
        assertEquals(true, velo.estAbime());

    }
    @Test
    public void testAbimerFaux() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);

        // vérifié s'il est pas abimé
        assertEquals(false, velo.estAbime());

    }

    @Test
    public void testReviserDecrocheAbime() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.abimer();

        // vérifié s'il a été révisé
        assertEquals(0, velo.reviser());

    }
    @Test
    public void testReviserDecrochePasAbime() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);

        // vérifié s'il a été révisé
        assertEquals(0, velo.reviser());

    }
    @Test
    public void testReviserPasDecroche() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.arrimer();

        // vérifié qu'il n'a pas été révisé
        assertEquals(-1, velo.reviser());

    }


    @Test
    public void testReparer() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.abimer();

        // vérification de sa réparation
        assertEquals(0, velo.reparer());

    }
    @Test
    public void testPasReparerAccroche() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.abimer();
        velo.arrimer();

        // vérification de sa réparation
        assertEquals(-1, velo.reparer());

    }
    @Test
    public void testPasReparerAbime() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);

        // vérification de sa réparation
        assertEquals(-2, velo.reparer());

    }

    @Test
    public void testVeloConstructeur() {
        // création d'un nouveau velo
        IVelo velo = new Velo('h');
        OptCadreAlu o = new OptCadreAlu(velo);

        // vérification de son type de cadre
        assertTrue(o.toString().contains(" homme "));

    }


    private IVelo v;
    @Before
    public void setUp() {
        v  = new Velo();
    }

    /**
     * Tests le tarif des options
     */
    @Test
    public void testTarifOptCadreAlu() {
        v=new OptCadreAlu(v);
        assertEquals(2.2, v.tarif(), DELTA);
    }

    @Test
    public void testTarifOptFreinsDisque() {
        v=new OptFreinsDisque(v);
        assertEquals(2.3, v.tarif(), DELTA);
    }

    @Test
    public void testTarifOptSuspensionAvant() {
        v=new OptSuspensionAvant(v);
        assertEquals(2.5, v.tarif(), DELTA);
    }

    @Test
    public void testTarifOptSuspensionArriere() {
        v=new OptSuspensionArriere(v);
        assertEquals(2.5, v.tarif(), DELTA);
    }

    @Test
    public void testTarifOptAssistanceElectrique() {
        v=new OptAssistanceElectrique(v);
        assertEquals(4, v.tarif(), DELTA);
    }

    @Test
    public void testTarifPlusieursOpt() {
        v =new OptAssistanceElectrique(v);
        v =new OptSuspensionArriere(v);
        assertEquals(4.5, v.tarif(), DELTA);
    }

    /**
     * Tests toString options
     */

    @Test
    public void testToStringSansOptions() {
        assertEquals(v.toString(), "Vélo cadre mixte - 0.0 km");
    }

    @Test
    public void testToStringOptCadreAlu() {
        v=new OptCadreAlu(v);
        assertEquals(v.toString(), "Vélo cadre mixte, cadre aluminium - 0.0 km");
    }

    @Test
    public void testToStringOptFreinsDisque() {
        v=new OptFreinsDisque(v);
        assertEquals(v.toString(), "Vélo cadre mixte, freins à disque - 0.0 km");
    }

    @Test
    public void testToStringOptSuspensionAvant() {
        v=new OptSuspensionAvant(v);
        assertEquals(v.toString(), "Vélo cadre mixte, suspension avant - 0.0 km");
    }

    @Test
    public void testToStringOptSuspensionArriere() {
        v=new OptSuspensionArriere(v);
        assertEquals(v.toString(), "Vélo cadre mixte, suspension arrière - 0.0 km");
    }

    @Test
    public void testToStringOptAssistanceElectrique() {
        v=new OptAssistanceElectrique(v);
        assertEquals(v.toString(), "Vélo cadre mixte, assistance électrique - 0.0 km");
    }

    @Test
    public void testToStringPlusieursOptAvecRevisionNecessaire() {
        v =new OptAssistanceElectrique(v);
        v =new OptSuspensionArriere(v);
        v.parcourir(643.4367);
        assertEquals(v.toString(), "Vélo cadre mixte, assistance électrique, suspension arrière - 643.4 km (révision nécessaire)");
    }

    @Test
    public void testToStringOptSansRevision() {
        v =new OptAssistanceElectrique(v);
        v.parcourir(149.99);
        assertEquals(v.toString(), "Vélo cadre mixte, assistance électrique - 150.0 km");
    }

    /**
     * Tests évolution de l'état du vélo avec options
     */

    //Test de décrocher un vélo avec options non abimé
    @Test
    public void testDecrocherVeloOptDejaDecroche_OK() {
        v =new OptAssistanceElectrique(v);
        assertEquals(-1,v.decrocher());
    }

    @Test
    public void testDecrocherVeloOptAccroche_OK() {
        v =new OptAssistanceElectrique(v);
        v.arrimer();
        assertEquals(0,v.decrocher());
    }

    //Test d'accrocher un vélo avec options non abimé
    @Test
    public void testAccrocherVeloOptDecroche_OK() {
        v =new OptCadreAlu(v);
        assertEquals(0,v.arrimer());
    }

    @Test
    public void testAccrocherVeloOptDejaAccroche_OK() {
        v =new OptCadreAlu(v);
        v.arrimer();
        assertEquals(-1,v.arrimer());
    }

    //Test d'abimer un vélo avec options
    @Test
    public void testAbimerVeloOptDecroche() {
        v =new OptCadreAlu(v);
        v.abimer();
        assertTrue(v.estAbime());
    }

    @Test
    public void testAbimerVeloOptAccroche() {
        v =new OptCadreAlu(v);
        v.arrimer();
        v.abimer();
        assertTrue(v.estAbime());
    }

    //Test de décrocher un vélo abimé avec options
    @Test
    public void testDecrocherVeloOptDejaDecroche_Abime() {
        v =new OptFreinsDisque(v);
        v.abimer();
        assertEquals(-1,v.decrocher());
    }

    @Test
    public void testDecrocherVeloOptAccroche_Abime() {
        v =new OptFreinsDisque(v);
        v.abimer();
        v.arrimer();
        assertEquals(0,v.decrocher());
    }

    //Test d'accrocher un vélo abimé avec options
    @Test
    public void testAccrocherVeloOptDecroche_Abime() {
        v =new OptSuspensionAvant(v);
        v.abimer();
        assertEquals(0,v.arrimer());
    }

    @Test
    public void testAccrocherVeloOptDejaAccroche_Abime() {
        v =new OptSuspensionAvant(v);
        v.abimer();
        v.arrimer();
        assertEquals(-1,v.arrimer());
    }

    /**
     * Tests réparation du vélo avec options
     */
    //Test de réparer un vélo abimé avec options
    @Test
    public void testReparerVeloOptDecroche_Abime() {
        v =new OptSuspensionArriere(v);
        v.abimer();
        assertEquals(0,v.reparer());
    }

    @Test
    public void testReparerVeloOptAccroche_Abime() {
        v =new OptSuspensionArriere(v);
        v.abimer();
        v.arrimer();
        assertEquals(-1,v.reparer());
    }

    //Test de réparer un vélo non abimé avec options
    @Test
    public void testReparerVeloOptDecroche_Ok() {
        v =new OptSuspensionArriere(v);
        assertEquals(-2,v.reparer());
    }

    /**
     * Tests parcours et révision vélo avec options
     */
    //Test de parcourir une distance avec un vélo avec options
    @Test
    public void testParcourirVeloOptAccroche_Ok() {
        v =new OptAssistanceElectrique(v);
        v =new OptSuspensionArriere(v);
        v.arrimer();
        v.parcourir(40.0);
        assertEquals(0,v.kilometrage(), DELTA);
    }

    @Test
    public void testParcourirVeloOptDecroche_Ok() {
        v =new OptAssistanceElectrique(v);
        double distance=40.0;
        v.parcourir(distance);
        assertEquals(distance,v.kilometrage(), DELTA);
    }

    @Test
    public void testParcourirVeloOptAccroche_Abime() {
        v =new OptAssistanceElectrique(v);
        v.arrimer();
        v.abimer();
        v.parcourir(40.0);
        assertEquals(0,v.kilometrage(), DELTA);
    }

    @Test
    public void testParcourirVeloOptDecroche_Abime() {
        v =new OptAssistanceElectrique(v);
        v.abimer();
        double distance=40.0;
        v.parcourir(distance);
        assertEquals(distance,v.kilometrage(), DELTA);
    }

    @Test
    public void testParcourirPlusieursFois() {
        v =new OptAssistanceElectrique(v);
        double distance1=40.0;
        v.parcourir(distance1);
        double distance2=100.0;
        v.parcourir(distance2);
        assertEquals(distance1+distance2,v.kilometrage(), DELTA);
    }

    @Test
    public void testParcourirValeurNegative() {
        v =new OptAssistanceElectrique(v);
        v.parcourir(-40.0);
        assertEquals(0,v.kilometrage(), DELTA);
    }

    //Test combien de kilomètres il reste avant la révision
    @Test
    public void testSiBesoinReviserVeloOpt500km() {
        v =new OptAssistanceElectrique(v);
        v.parcourir(500);
        assertEquals(0,v.prochaineRevision(), DELTA);
    }

    @Test
    public void testSiBesoinReviserVeloOptPlus500km() {
        v =new OptAssistanceElectrique(v);
        v.parcourir(500.0000001);
        assertTrue(0>v.prochaineRevision());
    }

    @Test
    public void testSiBesoinReviserMoinsVeloOpt500km() {
        v =new OptAssistanceElectrique(v);
        v.parcourir(499.99999999);
        assertTrue(0<v.prochaineRevision());
    }

    //Test la révision d'un vélo décroché avec options
    @Test
    public void testReviserVeloOptDecroche_Ok_Plus500() {
        v =new OptAssistanceElectrique(v);
        v.parcourir(600.00);
        assertEquals(0,v.reviser());
    }

    @Test
    public void testReviserVeloOptDecroche_Ok_Moins500() {
        v =new OptAssistanceElectrique(v);
        v.parcourir(200.00);
        assertEquals(0,v.reviser());
    }

    @Test
    public void testReviserVeloOptDecroche_Abime() {
        v =new OptAssistanceElectrique(v);
        v.abimer();
        v.parcourir(300.00);
        assertEquals(0,v.reviser());
        assertEquals(0,v.kilometrage(), DELTA);
        //Test si la réparation a eu lieu pendant la révision
        assertEquals(-2,v.reparer());
    }

    //Test la révision d'un vélo accroché avec options
    @Test
    public void testReviserVeloOptAccroche() {
        v =new OptAssistanceElectrique(v);
        v.arrimer();
        v.parcourir(600.00);
        assertEquals(-1,v.reviser());
    }

    @Test
    public void testReviserVeloOptAccroche_Abime() {
        v =new OptAssistanceElectrique(v);
        v.parcourir(300);
        v.arrimer();
        v.abimer();
        assertEquals(-1,v.reviser());
        assertEquals(300,v.kilometrage(), DELTA);
        //Test si la réparation a eu lieu pendant la révision
        v.decrocher();
        assertEquals(0,v.reparer());
    }



}
