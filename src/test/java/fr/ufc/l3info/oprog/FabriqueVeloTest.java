package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;

public class FabriqueVeloTest {


    @Test
    public void testConstruireFREINS_DISQUE() {
        // création d'un nouveau velo

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo velo = fabrique.construire('f',"FREINS_DISQUE");

        // vérification de l'option
        Assert.assertTrue(velo.toString().contains("freins à disque"));
    }

    @Test
    public void testConstruireCADRE_ALUMINIUM() {
        // création d'un nouveau velo

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo velo = fabrique.construire('f',"CADRE_ALUMINIUM");

        // vérification de l'option
        Assert.assertTrue(velo.toString().contains("cadre aluminium"));
    }

    @Test
    public void testConstruireSUSPENSION_AVANT() {
        // création d'un nouveau velo

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo velo = fabrique.construire('f',"SUSPENSION_AVANT");

        // vérification de l'option
        Assert.assertTrue(velo.toString().contains("suspension avant"));
    }

    @Test
    public void testConstruireSUSPENSION_ARRIERE() {
        // création d'un nouveau velo

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo velo = fabrique.construire('f',"SUSPENSION_ARRIERE");

        // vérification de l'option
        Assert.assertTrue(velo.toString().contains("suspension arrière"));
    }

    @Test
    public void testConstruireSUSASSISTANCE_ELECTRIQUE() {
        // création d'un nouveau velo

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo velo = fabrique.construire('f',"ASSISTANCE_ELECTRIQUE");

        // vérification de l'option
        Assert.assertTrue(velo.toString().contains("assistance électrique"));
    }


    @Test
    public void testConstruirePlusieursOption() {
        // création d'un nouveau velo

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo velo = fabrique.construire('f',"ASSISTANCE_ELECTRIQUE", "SUSPENSION_ARRIERE");

        // vérification des options
        Assert.assertTrue(velo.toString().contains("assistance électrique") && velo.toString().contains("suspension arrière"));
    }

    @Test
    public void testConstruire2() {
        // création d'un nouveau velo

        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo velo = fabrique.construire('f');

        // vérification sans option
        Assert.assertFalse(velo.toString().contains("cadre aluminium") || velo.toString().contains("assistance électrique") || velo.toString().contains("suspension arrière") || velo.toString().contains("suspension avant") || velo.toString().contains("freins à disque"));
    }
    /*@Test
    public void testFabriqueSansOpt() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('F', null);
        // vérification de son nom
        Assert.assertEquals("Vélo cadre femme - 0.0 km", v.toString());
    }*/
    @Test
    public void testFabriqueSansOptF () {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('F');
        // vérification de son nom
        Assert.assertEquals("Vélo cadre femme - 0.0 km", v.toString());
    }
    @Test
    public void testFabriqueSansOptf () {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('f');
        // vérification de son nom
        Assert.assertEquals("Vélo cadre femme - 0.0 km", v.toString());
    }
    @Test
    public void testFabriqueSansOptH () {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('H');
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme - 0.0 km", v.toString());
    }
    @Test
    public void testFabriqueSansOpth () {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h');
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme - 0.0 km", v.toString());
    }
    /*@Test
    public void testFabriqueSansOptM () {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('z');
        // vérification de son nom
        Assert.assertEquals("Vélo cadre mixte - 0.0 km", v.toString());
    }*/
    /*@Test
    public void testFabriqueOptalu() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "CADRE_ALUMINIUM");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, cadre aluminium - 0.0 km", v.toString());
    }*/

   /* @Test
    public void testFabriqueOptsusava() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "SUSPENSION_AVANT");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, suspension avant - 0.0 km", v.toString());
    }*/

    /*@Test
    public void testFabriqueOptsusarr() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "SUSPENSION_ARRIERE");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, suspension arrière - 0.0 km", v.toString());
    }*/

    /*@Test
    public void testFabriqueOptfreid() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "FREINS_DISQUE");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, freins à disque - 0.0 km", v.toString());
    }*/

    /*@Test
    public void testFabriqueOptassEle() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "ASSISTANCE_ELECTRIQUE");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, assistance électrique - 0.0 km", v.toString());
    }*/
    @Test
    public void testFabriqueOptfausse() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "Erreur");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme - 0.0 km", v.toString());
    }
    /*@Test
    public void testFabriqueOptassEleetfausse() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "ASSISTANCE_ELECTRIQUE","Erreur");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, assistance électrique - 0.0 km", v.toString());
    }*/
    /*@Test
    public void testFabriqueOptassEleDouble() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "ASSISTANCE_ELECTRIQUE","ASSISTANCE_ELECTRIQUE");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, assistance électrique - 0.0 km", v.toString());
    }*/
    /*@Test
    public void testFabriqueOptassEleQuadruple() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "ASSISTANCE_ELECTRIQUE","ASSISTANCE_ELECTRIQUE","ASSISTANCE_ELECTRIQUE","ASSISTANCE_ELECTRIQUE");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, assistance électrique - 0.0 km", v.toString());
    }*/
    /*@Test
    public void testFabriqueOptelecetalu() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "ASSISTANCE_ELECTRIQUE","CADRE_ALUMINIUM");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, assistance électrique, cadre aluminium - 0.0 km", v.toString());
    }*/
    /*@Test
    public void testFabriqueOptelecetaluetfausse() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "ASSISTANCE_ELECTRIQUE","Erreur","CADRE_ALUMINIUM");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, assistance électrique, cadre aluminium - 0.0 km", v.toString());
    }*/
    /*@Test
    public void testFabrique3Opt() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "ASSISTANCE_ELECTRIQUE","CADRE_ALUMINIUM","FREINS_DISQUE");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, assistance électrique, cadre aluminium, freins à disque - 0.0 km", v.toString());
    }*/
    /*@Test
    public void testFabrique4Opt() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "SUSPENSION_AVANT","ASSISTANCE_ELECTRIQUE","CADRE_ALUMINIUM","FREINS_DISQUE");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, suspension avant, assistance électrique, cadre aluminium, freins à disque - 0.0 km", v.toString());
    }*/
    /*@Test
    public void testFabrique5Opt() {
        // création d'un nouvel abonné
        FabriqueVelo f = FabriqueVelo.getInstance();
        IVelo v = f.construire('h', "SUSPENSION_AVANT","ASSISTANCE_ELECTRIQUE","CADRE_ALUMINIUM","FREINS_DISQUE","SUSPENSION_ARRIERE");
        // vérification de son nom
        Assert.assertEquals("Vélo cadre homme, suspension avant, assistance électrique, cadre aluminium, freins à disque, suspension arrière - 0.0 km", v.toString());
    }*/

}
