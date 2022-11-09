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

}
