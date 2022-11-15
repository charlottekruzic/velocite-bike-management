package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Test unitaire pour les abonnés.
 */
public class AbonneTest {

    /*Test nom Constructeur1*/

    @Test
    public void testNomCorrect() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");

        // vérification de son nom
        assertEquals("Fred", a.getNom());

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
        assertEquals("fred", a.getNom());
    }
    @Test
    public void testNomEspace2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("   fred D");

        // vérification de son nom
        assertEquals("fred D", a.getNom());
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
        assertTrue(a.getID()>0);

    }
    @Test
    public void testIdCorrect2Constructeur1() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        Abonne b = new Abonne("Jade");

        // vérification de son id
        assertTrue(a.getID()<b.getID());

    }


    /*Test nom Constucteur2*/
    @Test
    public void testAbonne2NomCorrect() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");

        // vérification de son nom
        assertEquals("Fred", a.getNom());

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
        assertEquals("fred", a.getNom());
    }
    @Test
    public void testNomAbonne2Espace2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("   fred D","12345-12345-12345678912-13");

        // vérification de son nom
        assertEquals("fred D", a.getNom());
    }
    /*Test Id Constucteur2*/
    @Test
    public void testIdCorrectConstructeur2() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");


        // vérification de son id
        assertTrue(a.getID()>0);

    }
    @Test
    public void testIdCorrect2Constructeur2() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        Abonne b = new Abonne("Jade","12345-12345-12345678912-13");

        // vérification de son id
        assertTrue(a.getID()<b.getID());


    }
*/
    /*Test bloquer abonne*/
    @Test
    public void testBloqueAbonne() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred", "12345-12345-12345678912-13");
        a.bloquer();
        // vérification abonné bloqué
        assertEquals(true, a.estBloque());
    }

    /*Test debloquer abonne*/
    @Test
    public void testDebloqueAbonne() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred", "12345-12345-12345678912-13");
        a.bloquer();
        a.debloquer();
        // vérification abonné débloqué
        assertEquals(false, a.estBloque());
    }
    @Test
    public void testDebloqueAbonneConstructeur1() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        a.debloquer();
        // vérification abonné débloqué
        assertEquals(true, a.estBloque());
    }

    /*Test si abonne est bloque*/
    @Test
    public void testEstBloqueFalse() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred", "12345-12345-12345678912-13");

        // vérification abonné bloqué
        assertEquals(false, a.estBloque());
    }


    @Test
    public void testEstBloqueVrai() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred", "12345-12345-12345678912-15");
        // vérification abonné bloqué
        assertEquals(true, a.estBloque());
    }


    @Test
    public void testestBloqueVrai2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        // vérification abonné bloqué
        assertEquals(true, a.estBloque());
    }

    /*Test equals*/
    @Test
    public void testEqualsFaux() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        Abonne b = new Abonne("Jade","11111-22222-33333333333-91");
        // vérification abonnés égaux
        assertFalse(a.equals(b));
    }
    @Test
    public void testEqualsFaux2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        Object obj = new Object();
        //vérification abonnés égaux
        assertFalse(a.equals(obj));
    }


    /*Test hashCode*/


    @Test
    public void testHashCodeDifferent() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        Abonne b = new Abonne("Jade","11111-22222-33333333333-91");
        // vérification du hashCode
        assertFalse(a.hashCode() == b.hashCode());
    }

    /**
     * Tests sur les noms
     */
    @Test
    public void testNomValide() throws IncorrectNameException {
        Abonne a = new Abonne("Fred");
        assertEquals("Fred", a.getNom());
    }

    @Test
    public void testNomEspaceExtremite() throws IncorrectNameException {
        Abonne d = new Abonne(" Jean  ");
        assertEquals("Jean", d.getNom());
    }

    @Test
    public void testNomTiret() throws IncorrectNameException {
        Abonne e = new Abonne("Jean-Michel");
        assertEquals("Jean-Michel", e.getNom());
    }

    @Test
    public void testNomEspace() throws IncorrectNameException {
        Abonne e = new Abonne("  Jean Louis");
        assertEquals("Jean Louis", e.getNom());
    }

    @Test
    public void testNomPlusieursTirets() throws IncorrectNameException {
        Abonne e = new Abonne("Jean-Pierre-Marie");
        assertEquals("Jean-Pierre-Marie", e.getNom());
    }

    @Test
    public void testNomTiretEtEspace() throws IncorrectNameException {
        Abonne e = new Abonne("Jean-Pierre Marie");
        assertEquals("Jean-Pierre Marie", e.getNom());
    }

    @Test(expected=IncorrectNameException.class)
    public void testNomMauvaisCaracteres() throws IncorrectNameException {
        Abonne c = new Abonne("Ja3@d");
    }

    @Test(expected=IncorrectNameException.class)
    public void testNomTiretFin() throws IncorrectNameException {
        Abonne c = new Abonne("Matt---");
    }

    @Test(expected=IncorrectNameException.class)
    public void testNomEspaceTiretDebut() throws IncorrectNameException {
        Abonne c = new Abonne(" -Jul");
    }

    /**
     * Tests sur les RIB de depart
     */
    @Test
    public void testRibCorrect() throws IncorrectNameException{
        Abonne d = new Abonne("Clement","18331-13940-94873749273-90");
        assertFalse(d.estBloque());
    }

    @Test
    public void testRibMauvaiseSeparation() throws IncorrectNameException{
        Abonne d = new Abonne("Clement","18331 13940 94873749273 90");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibMauvaisNombreCaracteresCodeBanque() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "1831-13940-94873749273-90");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibMauvaisNombreCaracteresCodeGuichet() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "18331-139940-94873749273-90");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibMauvaisNombreCaracteresNumCompte() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "18331-13940-9487374923-90");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibMauvaisNombreCaracteresCleRIB() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "18331-13940-94873749273-9");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibCaracteresInvalides() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "18331-13*90-94873749273-90");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibPartiesManquantes() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "18331-13940-94873749273");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibCleFausse() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "18331-13940-94873749273-91");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibCleTropGrande() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "18331-13940-94873749273-99");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibCleTropFaible() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "18331-13940-94873749273-00");
        assertTrue(d.estBloque());
    }

    /**
     * Tests mises à jour RIB
     */

    @Test
    public void testMAJRibCorrect() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien");
        d.miseAJourRIB("18331-13940-94873749273-90");
        assertFalse(d.estBloque());
    }

    @Test
    public void testMAJRibCorrectDejaUnRib() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien", "18333-13940-94873749273-09");
        d.miseAJourRIB("18331-13940-94873749273-90");
        assertFalse(d.estBloque());
    }

    @Test
    public void testMAJRibFaux() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien");
        d.miseAJourRIB("18333-13940-94873749273-59");
        assertTrue(d.estBloque());
    }

    @Test
    public void testMAJRibFauxDejaUnRib() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien", "18331-13940-94873749273-90");
        d.miseAJourRIB("18333-13940-94873749273-59");
        assertFalse(d.estBloque());
    }

    /**
     * Tests bloquer/débloquer volontairement
     */
    @Test
    public void testBloquerAbonne() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien", "18333-13940-94873749273-09");
        d.bloquer();
        assertTrue(d.estBloque());
    }

    @Test
    public void testDebloquerAbonne() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien","18333-13940-94873749273-09");
        d.bloquer();
        assertTrue(d.estBloque());
        d.debloquer();
        assertFalse(d.estBloque());
    }

    @Test
    public void testDebloquerAbonneSansRIB() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien");
        assertTrue(d.estBloque());
        d.debloquer();
        assertTrue(d.estBloque());
    }

    @Test
    public void testBloquerAbonneDejaBloque() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien");
        d.bloquer();
        assertTrue(d.estBloque());
    }

    @Test
    public void testDebloquerAbonneDejaDebloque() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien", "18331-13940-94873749273-90");
        d.debloquer();
        assertFalse(d.estBloque());
    }

    /**
     * Tests abonnés identiques ou non
     */

    @Test
    public void testMemeAbonne() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        assertTrue(lucien.equals(lucien));

    }

    @Test
    public void testDifferentsAbonnes() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        Abonne jason = new Abonne("Jason");
        assertFalse(lucien.equals(jason));
    }

    @Test
    public void testAbonneNull() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        Abonne nul = null;
        assertFalse(lucien.equals(nul));
    }

    @Test
    public void testPasUnAbonne() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        int pasUnAbonne = 12;
        assertFalse(lucien.equals(pasUnAbonne));
    }

    @Test
    public void testDifferentsAbonnesHash() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        Abonne agathe = new Abonne("Agathe");
        HashSet<Abonne> abonnes = new HashSet<Abonne>();
        abonnes.add(lucien);
        abonnes.add(agathe);
        assertEquals(2, abonnes.size());
    }

    @Test
    public void testMemeAbonneHash() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        HashSet<Abonne> abonnes = new HashSet<Abonne>();
        abonnes.add(lucien);
        abonnes.add(lucien);
        assertEquals(1, abonnes.size());
    }
    public void testNom() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        // vérification de son nom
        Assert.assertEquals("Fred", a.getNom());

    }
    @Test
    public void testConstructeurValideNomSimple() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Anne");
        // vérification de son nom
        Assert.assertEquals("Anne", a.getNom());

    }
    /*@Test
    public void testConstructeur2ValideNomSimple() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Anne","77777-11111-11111111111-77");
        // vérification de son nom
        Assert.assertEquals("Anne", a.getNom());

    }*/
    @Test
    public void testConstructeurValideNomAccentSimple() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Léa");
        // vérification de son nom
        Assert.assertEquals("Léa", a.getNom());

    }
    /*@Test
    public void testConstructeur2ValideNomAccentSimple() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Léa","77777-11111-11111111111-77");
        // vérification de son nom
        Assert.assertEquals("Léa", a.getNom());

    }*/
    @Test
    public void testConstructeurValideNomSimpleAvecEspaceExterne() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne(" Anne ");
        // vérification de son nom
        Assert.assertEquals("Anne", a.getNom());

    }
    /*@Test
    public void testConstructeur2ValideNomSimpleAvecEspaceExterne() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne(" Anne ","77777-11111-11111111111-77");
        // vérification de son nom
        Assert.assertEquals("Anne", a.getNom());

    }*/
    @Test
    public void testValideAvec1Espace() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Jean Pierre");
        Assert.assertEquals("Jean Pierre", a.getNom());

    }
    /*@Test
    public void testValideAvec1Espace2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Jean Pierre","77777-11111-11111111111-77");
        Assert.assertEquals("Jean Pierre", a.getNom());

    }*/
    /*@Test
    public void testValideAvec1Trait() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Jean-Pierre");
        Assert.assertEquals("Jean-Pierre", a.getNom());

    }*/
    /*@Test
    public void testValideAvec1Trait2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Jean-Pierre","77777-11111-11111111111-77" );
        Assert.assertEquals("Jean-Pierre", a.getNom());

    }*/
    @Test (expected= IncorrectNameException.class)
    public void testNom_Vide() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("");
        Assert.assertEquals(new IncorrectNameException(),a.getNom());

    }
    @Test (expected= IncorrectNameException.class)
    public void testNomVide2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("","77777-11111-11111111111-77");
        Assert.assertEquals(new IncorrectNameException(),a.getNom());

    }
    @Test (expected= IncorrectNameException.class)
    public void testNomPasValideChiffre() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("charles 2");
        Assert.assertEquals(new IncorrectNameException(), a.getNom());

    }
    @Test (expected= IncorrectNameException.class)
    public void testNomPasValideChiffre2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("charles 2","77777-11111-11111111111-77");
        Assert.assertEquals(new IncorrectNameException(), a.getNom());


    }
    @Test (expected= IncorrectNameException.class)
    public void testNomPasValideTrait() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("charles-Emilien-----");
        Assert.assertEquals(new IncorrectNameException(), a.getNom());


    @Test
    public void testDifferentsAbonnes() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        Abonne jason = new Abonne("Jason");
        assertFalse(lucien.equals(jason));
    }

    @Test
    public void testAbonneNull() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        Abonne nul = null;
        assertFalse(lucien.equals(nul));
    }

    @Test
    public void testPasUnAbonne() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        int pasUnAbonne = 12;
        assertFalse(lucien.equals(pasUnAbonne));
    }

    @Test
    public void testDifferentsAbonnesHash() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        Abonne agathe = new Abonne("Agathe");
        HashSet<Abonne> abonnes = new HashSet<Abonne>();
        abonnes.add(lucien);
        abonnes.add(agathe);
        assertEquals(2, abonnes.size());
    }

    @Test
    public void testMemeAbonneHash() throws IncorrectNameException {
        Abonne lucien = new Abonne("Lucien");
        HashSet<Abonne> abonnes = new HashSet<Abonne>();
        abonnes.add(lucien);
        abonnes.add(lucien);
        assertEquals(1, abonnes.size());
    }
    }
    @Test (expected= IncorrectNameException.class)
    public void testNomPasValideTrait2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("charles-Emilien-----","77777-11111-11111111111-77");
        Assert.assertEquals(new IncorrectNameException(), a.getNom());

    }
    /*@Test (expected= IncorrectNameException.class)
    public void testNomPasValideEspaceInterne() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("charles   Emilien");
        Assert.assertEquals(new IncorrectNameException(), a.getNom());

    }*/
    /*@Test (expected= IncorrectNameException.class)
    public void testNomPasValideEspaceInterne2() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("charles   Emilien","77777-11111-11111111111-77");
        Assert.assertEquals(new IncorrectNameException(), a.getNom());

    }*/

    /*@Test
    public void testRibCorrect() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","77777-11111-11111111111-77");
        // vérification de son nom
        Assert.assertEquals(false, a.estBloque());

    }*/
    @Test
    public void testRibIncorrectChiffreEnTrop() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","123451-12345-78945612341-22");
        // vérification de son nom
        Assert.assertTrue(a.estBloque());
    }
   /* @Test
    public void testRibIncorrectLettre() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-7894561234B-22");
        // vérification de son nom
        Assert.assertTrue(a.estBloque());
    }*/
    @Test
    public void testRibIncorrectChiffreEnMoins() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","123451-23457-89456423412-3");
        // vérification de son nom
        Assert.assertTrue(a.estBloque());
    }
    /*@Test
    public void testRibIncorrectEspace() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred"," 123451-23457-89456123412-42");
        // vérification de son nom
        Assert.assertTrue(a.estBloque());
    }*/
    @Test
    public void testRibIncorrectSup97() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","123451-23457-89456123412-98");
        // vérification de son nom
        Assert.assertTrue(a.estBloque());
    }
    @Test
    public void testRibIncorrectInf01() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","123451-23457-89456123412-00");
        // vérification de son nom
        Assert.assertTrue(a.estBloque());
    }
    /*@Test
    public void testRibIncorrectSansTrait() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345126857894561234122");
        // vérification de son nom
        Assert.assertTrue(a.estBloque());
    }*/
    @Test
    public void testIdIncrementIncorrect() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        Abonne b = new Abonne("Fred");
        // vérification de son nom
        Assert.assertFalse(a.getID()==b.getID());
    }

    @Test
    public void testIdIncrementCorrect() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        Abonne b = new Abonne("Fred");
        // vérification de son nom
        Assert.assertTrue(a.getID()+1==b.getID());
    }

    /*@Test
    public void testMAJRibIncorrect() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-56845-78943332341-22");
        a.miseAJourRIB("16519qza5erd");
        // vérification de son nom
        Assert.assertTrue(a.estBloque());
    }*/

    /*@Test
    public void testMAJRib() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-14445-7987661sdfsdf2341-22");
        a.miseAJourRIB("33345-12399-78947712341-32");
        // vérification de son nom
        Assert.assertTrue(a.estBloque());
    }*/

    /*@Test
    public void testBloqueDebloque() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-78235782341-32");
        // vérification de son état non bloqué
        Assert.assertFalse(a.estBloque());
        // blocage de l'abonné
        a.bloquer();
        // vérification de son état bloqué
        Assert.assertTrue(a.estBloque());
        // déblocage de l'abonné
        a.debloquer();
        // vérification de son état non bloqué
        Assert.assertFalse(a.estBloque());
    }*/

    @Test
    public void testDebloque() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        // déblocage de l'abonné
        a.debloquer();
        // vérification de son état non bloqué
        Assert.assertTrue(a.estBloque());
    }

    /*@Test
    public void testDebloquePasValide() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","AAAA-21-Q-1-FO-2354");
        // déblocage de l'abonné
        a.debloquer();
        // vérification de son état non bloqué
        Assert.assertTrue(a.estBloque());
    }*/

    /*@Test
    public void testDebloqueValide() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","AAAA-21-Q-1-FO-2354");
        a.miseAJourRIB("12345-12345-78235782341-32");
        // déblocage de l'abonné
        a.debloquer();
        // vérification de son état non bloqué
        Assert.assertFalse(a.estBloque());
    }*/

    /*@Test
    public void testDebloqueNonValideMAJ() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","AAAA-21-Q-1-FO-2354");
        a.miseAJourRIB("BBBB-21-Q-1-FO-2354");
        // déblocage de l'abonné
        a.debloquer();
        // vérification de son état non bloqué
        Assert.assertTrue(a.estBloque());
    }*/

    /*@Test
    public void testEquals() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","33345-12399-78947712341-32");
        // création d'un nouvel abonné
        Abonne b = new Abonne("Fred","33345-12399-78947712341-32");
        // vérification de son nom
        Assert.assertFalse(a.equals(b));
    }*/

    @Test
    public void testEqualsSame() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","33345-12399-78947712341-32");
        // vérification de son nom
        Assert.assertTrue(a.equals(a));
    }

    @Test
    public void testEqualsNull() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","33345-12399-78947712341-32");
        // vérification de son nom
        Assert.assertFalse(a.equals(null));
    }

    @Test
    public void testNotEquals() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        String b = "Fred";
        // vérification de son nom
        Assert.assertFalse(a.equals(b));
    }

    /*@Test
    public void testHashcode() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","33345-12399-78947712341-32");
        // création d'un nouvel abonné
        Abonne b = new Abonne("Fred","33345-12399-78947712341-32");
        // vérification de son nom
        Assert.assertFalse(a.hashCode()==b.hashCode());
    }*/

}
