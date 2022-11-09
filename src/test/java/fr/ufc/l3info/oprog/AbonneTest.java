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

    /*Test Mise à jour rib*/
    @Test
    public void testMiseAJourRibCorrect() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        a.miseAJourRIB("11111-22222-33333333333-91");
        // vérification de son rib mis à jour
        assertEquals("11111-22222-33333333333-91",a.rib);

    }
    @Test
    public void testMiseAJourRibFaux() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred","12345-12345-12345678912-13");
        a.miseAJourRIB("11111-22222-33333333333-94");
        // vérification de son rib mis à jour
        assertEquals("12345-12345-12345678912-13",a.rib);

    }
    @Test
    public void testMiseAJourRibCorrectAvecRibNullDepart() throws IncorrectNameException{
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        a.miseAJourRIB("11111-22222-33333333333-91");
        // vérification de son rib mis à jour
        assertEquals("11111-22222-33333333333-91",a.rib);

    }

    /*Test bloquer abonne*/
    @Test
    public void testBloqueAbonne() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred", "12345-12345-12345678912-13");
        a.bloquer();
        // vérification abonné bloqué
        assertEquals(true, a.bloque);
    }

    /*Test debloquer abonne*/
    @Test
    public void testDebloqueAbonne() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred", "12345-12345-12345678912-13");
        a.bloquer();
        a.debloquer();
        // vérification abonné débloqué
        assertEquals(false, a.bloque);
    }
    @Test
    public void testDebloqueAbonneConstructeur1() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        a.debloquer();
        // vérification abonné débloqué
        assertEquals(true, a.bloque);
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
        Abonne d = new Abonne("Clement","1IGES-AD739-09732H6493V-50");
        assertFalse(d.estBloque());
    }

    @Test
    public void testRibRemplacementLettre() throws IncorrectNameException{
        Abonne d = new Abonne("Clement","A12D4-B8E18-CF0363VD991-31");
        assertFalse(d.estBloque());
    }

    @Test
    public void testRibMauvaiseSeparation() throws IncorrectNameException{
        Abonne d = new Abonne("Clement","1IGES AD739 09732H6493V 59");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibMauvaisNombreCaracteresCodeBanque() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "1IGF-AD739-0C732H6493V-63");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibMauvaisNombreCaracteresCodeGuichet() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "1IGFZ-AD7392-0C732H6493V-63");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibMauvaisNombreCaracteresNumCompte() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "1IGFZ-AD739-0C732H6493-63");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibMauvaisNombreCaracteresCleRIB() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "1IGFZ-AD739-0C732H6493V-6");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibCaracteresInvalides() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "1IGES-A*739-09732&6493V-50");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibPartiesManquantes() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "1IGES-AD739-09732H6493V");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibCleFausse() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "1IGF-AD739-0C732H6493V-6C");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibCleTropGrande() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "1IGFZ-AD739-0C732H6493V-99");
        assertTrue(d.estBloque());
    }

    @Test
    public void testRibCleTropFaible() throws IncorrectNameException {
        Abonne d = new Abonne("Clement", "1IGFZ-AD739-0C732H6493V-00");
        assertTrue(d.estBloque());
    }

    /**
     * Tests mises à jour RIB
     */

    @Test
    public void testMAJRibCorrect() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien");
        d.miseAJourRIB("SZA33-24194-1I4YI219084-26");
        assertFalse(d.estBloque());
    }

    @Test
    public void testMAJRibCorrectDejaUnRib() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien", "1IGES-AD739-09732H6493V-50");
        d.miseAJourRIB("SZA33-24194-1I4YI219084-26");
        assertFalse(d.estBloque());
    }

    @Test
    public void testMAJRibFaux() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien");
        d.miseAJourRIB("SZA33-24194-1I4YI219084-58");
        assertTrue(d.estBloque());
    }

    @Test
    public void testMAJRibFauxDejaUnRib() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien", "1IGES-AD739-09732H6493V-50");
        d.miseAJourRIB("SZA33-24194-1I4YI219084-58");
        assertFalse(d.estBloque());
    }

    /**
     * Tests bloquer/débloquer volontairement
     */
    @Test
    public void testBloquerAbonne() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien", "SZA33-24194-1I4YI219084-26");
        d.bloquer();
        assertTrue(d.estBloque());
    }

    @Test
    public void testDebloquerAbonne() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien");
        d.debloquer();
        assertFalse(d.estBloque());
    }

    @Test
    public void testBloquerAbonneDejaBloque() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien");
        d.bloquer();
        assertTrue(d.estBloque());
    }

    @Test
    public void testDebloquerAbonneDejaDebloque() throws IncorrectNameException {
        Abonne d = new Abonne("Lucien", "SZA33-24194-1I4YI219084-26");
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

}
