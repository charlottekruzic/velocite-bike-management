package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test unitaire pour les abonnés.
 */
public class AbonneTest {

    /*Test nom Constructeur1*/
//test
    @Test
    public void testNomCorrect() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");

        // vérification de son nom
        Assert.assertEquals("Fred", a.getNom());

    }
    @Test(expected = IncorrectNameException.class)
    public void testNomVide() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("");

    }

    @Test(expected = IncorrectNameException.class)
    public void testNomNull() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne(null);

    }

    @Test
    public void testNomEspace1() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("   fred   ");

        // vérification de son nom
        Assert.assertEquals("fred", a.getNom());
    }
    @Test
    public void testNomEspace2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("   fred D");

        // vérification de son nom
        Assert.assertEquals("fred D", a.getNom());
    }
    @Test(expected = IncorrectNameException.class)
    public void testNomCharSpecial() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("fred*1");


    }

    //test marche seul mais pas avec tous les tests
    /*Test Id Constructeur1*/
    @Test
    public void testIdCorrectConstructeur1() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");


        // vérification de son id
        Assert.assertTrue(a.getID()>0);

    }
    @Test
    public void testIdCorrect2Constructeur1() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        Abonne b = new Abonne("Jade");

        // vérification de son id
        Assert.assertTrue(a.getID()<b.getID());

    }


    /*Test nom Constucteur2*/
    @Test
    public void testAbonne2NomCorrect() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");

        // vérification de son nom
        Assert.assertEquals("Fred", a.getNom());

    }

    @Test(expected = IncorrectNameException.class)
    public void testAbonne2NomVide() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("","12345-12345-12345678912-13");

    }

    @Test(expected = IncorrectNameException.class)
    public void testAbonne2NomNull() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne(null,"12345-12345-12345678912-13");

    }
    @Test(expected = IncorrectNameException.class)
    public void testAbonne2NomCharSpecial() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("fred*1","12345-12345-12345678912-13");


    }
    @Test
    public void testNomAbonne2Espace1() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("   fred   ","12345-12345-12345678912-13");

        // vérification de son nom
        Assert.assertEquals("fred", a.getNom());
    }
    @Test
    public void testNomAbonne2Espace2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("   fred D","12345-12345-12345678912-13");

        // vérification de son nom
        Assert.assertEquals("fred D", a.getNom());
    }
    /*Test Id Constucteur2*/
    @Test
    public void testIdCorrectConstructeur2() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");


        // vérification de son id
        Assert.assertTrue(a.getID()>0);

    }
    @Test
    public void testIdCorrect2Constructeur2() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        Abonne b = new Abonne("Jade","12345-12345-12345678912-13");

        // vérification de son id
        Assert.assertTrue(a.getID()<b.getID());

    }

    /*Test Mise à jour rib*/
    @Test
    public void testMiseAJourRibCorrect() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        a.miseAJourRIB("11111-22222-33333333333-91");
        // vérification de son rib mis à jour
        Assert.assertEquals("11111-22222-33333333333-91",a.rib);

    }
    @Test
    public void testMiseAJourRibFaux() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        a.miseAJourRIB("11111-22222-33333333333-94");
        // vérification de son rib mis à jour
        Assert.assertEquals("12345-12345-12345678912-13",a.rib);

    }
    @Test
    public void testMiseAJourRibCorrectAvecRibNullDepart() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        a.miseAJourRIB("11111-22222-33333333333-91");
        // vérification de son rib mis à jour
        Assert.assertEquals("11111-22222-33333333333-91",a.rib);

    }

    /*Test bloquer abonne*/
    @Test
    public void testBloqueAbonne() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred", "12345-12345-12345678912-13");
        a.bloquer();
        // vérification abonné bloqué
        Assert.assertEquals(true, a.bloque);
    }

    /*Test debloquer abonne*/
    @Test
    public void testDebloqueAbonne() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred", "12345-12345-12345678912-13");
        a.bloquer();
        a.debloquer();
        // vérification abonné débloqué
        Assert.assertEquals(false, a.bloque);
    }
    @Test
    public void testDebloqueAbonneConstructeur1() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        a.debloquer();
        // vérification abonné débloqué
        Assert.assertEquals(true, a.bloque);
    }

    /*Test si abonne est bloque*/
    @Test
    public void testEstBloqueFalse() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred", "12345-12345-12345678912-13");

        // vérification abonné bloqué
        Assert.assertEquals(false, a.estBloque());
    }


    @Test
    public void testEstBloqueVrai() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred", "12345-12345-12345678912-15");
        // vérification abonné bloqué
        Assert.assertEquals(true, a.estBloque());
    }


    @Test
    public void testestBloqueVrai2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        // vérification abonné bloqué
        Assert.assertEquals(true, a.estBloque());
    }

    /*Test equals*/
    @Test
    public void testEqualsFaux() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        Abonne b = new Abonne("Jade","11111-22222-33333333333-91");
        // vérification abonnés égaux
        Assert.assertFalse(a.equals(b));
    }
    @Test
    public void testEqualsFaux2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        Object obj = new Object();
        //vérification abonnés égaux
        Assert.assertFalse(a.equals(obj));
    }


    /*Test hashCode*/


    @Test
    public void testHashCodeDifferent() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        Abonne b = new Abonne("Jade","11111-22222-33333333333-91");
        // vérification du hashCode
        Assert.assertFalse(a.hashCode() == b.hashCode());
    }




}
