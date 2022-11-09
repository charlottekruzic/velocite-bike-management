package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;

public class OptionTest {
    private static final double DELTA = 1e-15;

    /*Test OptCadreAlu*/
    @Test
    public void testOptionOptCadreAluTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptCadreAlu o = new OptCadreAlu(velo);

        // vérification du tarif
        Assert.assertEquals(2.2,o.tarif(),DELTA);
    }

    @Test
    public void testOptionOptCadreAluToString() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptCadreAlu o = new OptCadreAlu(velo);

        // vérification de l'option
        Assert.assertTrue(o.toString().contains("cadre aluminium"));
    }

    /*Test OptFreinsDisque*/
    @Test
    public void testOptionOptFreinsDisqueTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptFreinsDisque o = new OptFreinsDisque(velo);

        // vérification du tarif
        Assert.assertEquals(2.3,o.tarif(),DELTA);
    }

    @Test
    public void testOptionOptFreinsDisqueToString() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptFreinsDisque o = new OptFreinsDisque(velo);

        // vérification de l'option
        Assert.assertTrue(o.toString().contains("freins à disque"));
    }

    /*Test OptSuspensionAvant*/
    @Test
    public void testOptionOptSuspensionAvantTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptSuspensionAvant o = new OptSuspensionAvant(velo);

        // vérification du tarif
        Assert.assertEquals(2.5,o.tarif(),DELTA);
    }

    @Test
    public void testOptionOptSuspensionAvantToString() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptSuspensionAvant o = new OptSuspensionAvant(velo);

        // vérification de l'option
        Assert.assertTrue(o.toString().contains("suspension avant"));
    }

    /*Test OptSuspensionArriere*/
    @Test
    public void testOptionOptSuspensionArriereTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptSuspensionArriere o = new OptSuspensionArriere(velo);

        // vérification du tarif
        Assert.assertEquals(2.5,o.tarif(),DELTA);
    }

    @Test
    public void testOptionOptSuspensionArriereToString() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptSuspensionArriere o = new OptSuspensionArriere(velo);

        // vérification de l'option
        Assert.assertTrue(o.toString().contains("suspension arrière"));
    }

    /*Test OptAssistanceElectrique*/
    @Test
    public void testOptAssistanceElectriqueTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptAssistanceElectrique o = new OptAssistanceElectrique(velo);

        // vérification du tarif
        Assert.assertEquals(4,o.tarif(),DELTA);
    }

    @Test
    public void testOptAssistanceElectriqueToString() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        OptAssistanceElectrique o = new OptAssistanceElectrique(velo);
        System.out.println(o.toString());
        // vérification de l'option
        Assert.assertTrue(o.toString().contains(" assistance électrique"));
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
        Assert.assertTrue(velo.toString().contains("assistance électrique") && velo.toString().contains("suspension arrière"));
    }

    @Test
    public void testOptTarif() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo= new OptAssistanceElectrique(velo);
        velo= new OptSuspensionArriere(velo);

        // vérification du tarif
        Assert.assertEquals(4.5, velo.tarif(), DELTA);
    }



    @Test
    public void testKilometrageCreation() {
        // création d'un nouveau velo
        IVelo velo = new Velo('H');
        velo = new OptCadreAlu(velo);
        // vérification de son kilometrage
        Assert.assertEquals(0, velo.kilometrage(), DELTA);

    }

    @Test
    public void testKilometrageApresParcours() {
        // création d'un nouveau velo
        IVelo velo = new Velo('F');
        velo = new OptCadreAlu(velo);
        velo.parcourir(25.8);

        // vérification de son kilometrage après parcours
        Assert.assertEquals(25.8, velo.kilometrage(), DELTA);

    }

    @Test
    public void testProchaineRevision() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.parcourir(25.8);

        // vérification de sa prochaine révision
        Assert.assertEquals(474.2, velo.prochaineRevision(), DELTA);

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
        Assert.assertEquals(485, velo.prochaineRevision(), DELTA);

    }

    @Test
    public void testProchaineRevision2() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.parcourir(200);
        velo.parcourir(300);

        // vérification de sa prochaine révision
        Assert.assertEquals(0, velo.prochaineRevision(), DELTA);

    }

    @Test
    public void testDecrocher() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.arrimer();

        // vérifié s'il est décroché
        Assert.assertEquals(0, velo.decrocher());

    }

    @Test
    public void testDejaDecrocher() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);

        // vérifié s'il est déjà décroché
        Assert.assertEquals(-1, velo.decrocher());

    }

    @Test
    public void testArrimer() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);

        // vérifié s'il y est arrimé
        Assert.assertEquals(0, velo.arrimer());

    }
    @Test
    public void testDejaArrimer() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.arrimer();

        // vérifié s'il est déjà arrimé
        Assert.assertEquals(-1, velo.arrimer());

    }

    @Test
    public void testAbimerVrai() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.abimer();

        // vérifié s'il est abimé
        Assert.assertEquals(true, velo.estAbime());

    }
    @Test
    public void testAbimerFaux() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);

        // vérifié s'il est pas abimé
        Assert.assertEquals(false, velo.estAbime());

    }

    @Test
    public void testReviserDecrocheAbime() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.abimer();

        // vérifié s'il a été révisé
        Assert.assertEquals(0, velo.reviser());

    }
    @Test
    public void testReviserDecrochePasAbime() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);

        // vérifié s'il a été révisé
        Assert.assertEquals(0, velo.reviser());

    }
    @Test
    public void testReviserPasDecroche() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.arrimer();

        // vérifié qu'il n'a pas été révisé
        Assert.assertEquals(-1, velo.reviser());

    }


    @Test
    public void testReparer() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.abimer();

        // vérification de sa réparation
        Assert.assertEquals(0, velo.reparer());

    }
    @Test
    public void testPasReparerAccroche() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);
        velo.abimer();
        velo.arrimer();

        // vérification de sa réparation
        Assert.assertEquals(-1, velo.reparer());

    }
    @Test
    public void testPasReparerAbime() {
        // création d'un nouveau velo
        IVelo velo = new Velo();
        velo = new OptCadreAlu(velo);

        // vérification de sa réparation
        Assert.assertEquals(-2, velo.reparer());

    }

    @Test
    public void testVeloConstructeur() {
        // création d'un nouveau velo
        IVelo velo = new Velo('h');
        OptCadreAlu o = new OptCadreAlu(velo);

        // vérification de son type de cadre
        Assert.assertTrue(o.toString().contains(" homme "));

    }




}
