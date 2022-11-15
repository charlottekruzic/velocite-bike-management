package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;



import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class StationIntegrationTest {
    private static final double DELTA = 1e-3;

    @Test
    public void testConstruceurNom() {

        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        assertEquals("Gare Viotte",s.getNom());

    }

    @Test
    public void testConstructeurCapacite() {

        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        assertEquals(10,s.capacite());

    }
    @Test
    public void testConstructeurMauvaiseCapacite() {

        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,-1);
        assertEquals(0,s.capacite());

    }

    /*test emprunterVelo*/
    @Test
    public void testEmprunterVeloAbonneBloque () throws IncorrectNameException{
        // création d'un registre, d'un abonne et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");

        // vérification de l'emprunt avec abonne bloque
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        s.setRegistre(registre);
        assertNull(s.emprunterVelo(abonne,1));

    }

    @Test
    public void testEmprunterVeloNbEmpruntPasNull() throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("Jade","12345-12345-12345678912-13");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);
        registre.emprunter(abonne, velo, s.maintenant());

        // vérification de l'emprunt avec nombre d'emprunt différent de null
        assertNull(s.emprunterVelo(abonne,1));

    }


    @Test
    public void testEmprunterVeloCapaciteInf() throws IncorrectNameException{
        // création d'un registre, d'un abonne et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("Jade","12345-12345-12345678912-13");
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'emprunt avec une borne inférieur
        assertNull(s.emprunterVelo(abonne,0));

    }


    @Test
    public void testEmprunterVeloCapaciteSup() throws IncorrectNameException{
        // création d'un registre, d'un abonne et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("Jade","12345-12345-12345678912-13");
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'emprunt avec une borne supérieur
        assertNull(s.emprunterVelo(abonne,12));

    }
    @Test
    public void testEmprunterVeloRegistreNull() throws IncorrectNameException{
        // création d'un abonne et d'une station
       Abonne abonne = new Abonne("Jade","12345-12345-12345678912-13");
       Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

       // vérification de l'emprunt avec un registre null
       assertNull(s.emprunterVelo(abonne,12));

    }

    @Test
    public void testEmprunterVeloAbonneNull(){
        // création d'un registre et d'une station
        IRegistre registre = new JRegistre();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'emprunt avec un abonne null
        assertNull(s.emprunterVelo(null,5));

    }

    @Test
    public void testEmprunterVelo()throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("Jade","12345-12345-12345678912-13");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);
        s.arrimerVelo(velo,5);

        // vérification de l'emprunt valide
        assertEquals(velo,s.emprunterVelo(abonne,5));

    }

    /*test arrimerVelo*/

    @Test
    public void testArrimerVeloNull(){
        // création d'un registre et d'une station
        IRegistre registre = new JRegistre();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'arrimage avec un velo null
        assertEquals(-1,s.arrimerVelo(null,5));

    }

    @Test
    public void testArrimerVeloBorneInf(){
        // création d'un registre, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'arrimage avec une borne inférieur
        assertEquals(-1,s.arrimerVelo(velo,0));

    }
    @Test
    public void testArrimerVeloBorneSup(){
        // création d'un registre, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);

        // vérification de l'arrimage avec une borne superieur
        assertEquals(-1,s.arrimerVelo(velo,12));

    }

    @Test
    public void testArrimerVeloDejaPris(){
        // création d'un registre, des velos et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        IVelo velo2 = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);
        s.arrimerVelo(velo,5);

        // vérification de l'arrimage avec un velo qui est deja pris
        assertEquals(-2,s.arrimerVelo(velo2,5));

    }

    @Test
    public void testArrimerVeloRegistreNull(){
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification de l'arrimage avec un registre null
        assertEquals(-2,s.arrimerVelo(velo,5));

    }


    /*test nbBornesLibres*/
    @Test
    public void testNbBornesLibres(){
        // création d'un registre, des velos et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        IVelo velo2 = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,5);

        s.setRegistre(registre);
        s.arrimerVelo(velo,1);
        s.arrimerVelo(velo2,2);

        // vérification du nombre de bornes libres
        assertEquals(3,s.nbBornesLibres());

    }

    /*test veloALaBorne*/
    @Test
    public void testVeloALaBorneInf(){
        // création d'une station
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du velo à une borne inferieur
        assertNull(s.veloALaBorne(0));

    }

    @Test
    public void testVeloALaBorneSup(){
        // création d'une station
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du velo à une borne superieur
        assertNull(s.veloALaBorne(12));

    }

    @Test
    public void testVeloALaBorneNull(){
        // création d'une station
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du velo à une borne
        assertNull(s.veloALaBorne(5));

    }

    @Test
    public void testVeloALaBorne(){
        // création d'un registre, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        s.setRegistre(registre);
        s.arrimerVelo(velo,5);

        // vérification du velo à une borne
        assertEquals(velo,s.veloALaBorne(5));

    }


    /*test distance*/
    @Test
    public void testDistance(){
        // création de deux stations
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        Station s2 = new Station("Granvelle",  47.23531038159631,6.025353690286829,9);

        // vérification de la distance entre deux bornes
        assertEquals(73.227,s.distance(s2),DELTA);

    }
    @Test
    public void TestgetNom(){
        Station s = new Station("test",1.0,1.0,1);
        Assert.assertEquals("test",s.getNom());
    }
    @Test
    public void testCapStation() {
        Station s = new Station("test",30,1,10);
        Assert.assertTrue(s.capacite()==10);
    }
    @Test
    public void TestnbBorneLibre(){
        IRegistre r=new JRegistre();
        Station s = new Station("test",1.0,1.0,3);
        IVelo v= new Velo('f');
        v.decrocher();
        s.setRegistre(r);
        s.arrimerVelo(v,1);
        Assert.assertEquals(2,s.nbBornesLibres());
    }
    @Test
    public void TestCapaciteSupAZero(){
        Station s = new Station("test",1.0,1.0,3);
        Assert.assertTrue(s.capacite()>0);
    }
    @Test
    public void TestveloALaBorneVide(){


        IVelo v=new Velo('m');
        Station s=new Station("test",1.0,1.0,1);
        s.arrimerVelo(v,2);
        v.decrocher();
        Assert.assertEquals(null,s.veloALaBorne(2));
    }
    @Test
    public void TestVeloAlaBorneNumIncorrect(){
        Station s=new Station("test",1.0,1.0,3);
        Assert.assertNull(s.veloALaBorne(4));
    }
    @Test
    public void TestVeloAlaBorneNumIncorrectLarge(){
        Station s=new Station("test",1.0,1.0,3);
        Assert.assertNull(s.veloALaBorne(-1));
    }
    /*@Test
    public void TestArrimerVeloRegistrepasvalide()throws IncorrectNameException{
        IRegistre r=null;
        Abonne a=new Abonne("Jean-Eude");
        IVelo v=new Velo('m');
        Station s=new Station("test",1.0,1.0,3);
        s.setRegistre(r);
        Assert.assertEquals(-2,s.arrimerVelo(v,2));

    }
    @Test
    public void TestArrimerVeloBorneOccupe() throws IncorrectNameException{
        IRegistre r=new JRegistre();
        Abonne a=new Abonne("Jean-Eude");
        IVelo v=new Velo('m');
        IVelo v2= new Velo('f');
        Station s=new Station("test",1.0,1.0,3);
        s.arrimerVelo(v2,2);
        Assert.assertEquals(-2,s.arrimerVelo(v,2));

    }

    @Test
    public void TestEmpruntAbonneBloque() throws IncorrectNameException{
        Abonne a=new Abonne("Jean-Eude");
        Station s = new Station("test",1.0,1.0,1);
        Assert.assertEquals(null, s.emprunterVelo(a, 1));

    }
    @Test
    public void testEmpruntNoBorne1() throws IncorrectNameException{
        Abonne a=new Abonne("Jean-Eude");
        IRegistre r = new JRegistre();

        // objet sous test
        Station s = new Station("test",30,1,10);
        IVelo v = new Velo('m');
        v.decrocher();
        s.setRegistre(r);
        s.arrimerVelo(v,1);

        Assert.assertEquals(null, s.emprunterVelo(a, -1));
    }
    @Test
    public void testEmpruntNoBorne2() throws IncorrectNameException{
        Abonne a=new Abonne("Jean-Eude");
        IRegistre r = new JRegistre();
        Station s = new Station("test",30,1,10);
        IVelo v = new Velo('m');
        v.decrocher();
        s.setRegistre(r);
        s.arrimerVelo(v,1);

        Assert.assertEquals(null, s.emprunterVelo(a, 15));
    }
    @Test
    public void testEmpruntAbonneBloque() throws IncorrectNameException{
        Abonne a=new Abonne("Jean-Eude");
        a.bloquer();
        Station s = new Station("test",30,1,10);

        Assert.assertEquals(null, s.emprunterVelo(a, 1));

    }
    @Test
    public void testEmpruntAbonneNonBloque() throws IncorrectNameException{
        Abonne a=new Abonne("Jean-Eude");
        IVelo v = new Velo('m');
        IVelo v2 = new Velo('m');
        a.debloquer();
        IRegistre r= new JRegistre();
        r.emprunter(a,v,12102022);
        r.emprunter(a,v2,12102022);
        Station s = new Station("test",30,1,10);
        v.decrocher();
        s.setRegistre(r);
        s.arrimerVelo(v,1);

        Assert.assertEquals(null, s.emprunterVelo(a, 1));

    }
    @Test
    public void testEmprunt2emprunts() throws IncorrectNameException{
        Abonne a=new Abonne("Jean-Eude");
        IVelo v = new Velo('m');
        IVelo v2 = new Velo('m');
        IRegistre r= new JRegistre();
        a.debloquer();
        // objet sous test
        Station s = new Station("test",30,1,10);
        v.decrocher();
        s.setRegistre(r);
        s.arrimerVelo(v,1);

        s.emprunterVelo(a, 1);
        Assert.assertEquals(null, s.emprunterVelo(a, 1));
    }

    @Test
    public void testArrimerNoVelo() {
        Station s = new Station("test",30,1,10);
        Assert.assertTrue(s.arrimerVelo(null,1)==-1);
    }
    @Test
    public void testArrimerNoBorneValide() {
        Station s = new Station("test",30,1,10);
        IVelo v = new Velo('m');
        Assert.assertTrue(s.arrimerVelo(v,12)==-1);
    }
    @Test
    public void testArrimerNoBorneValide2() {
        Station s = new Station("test",30,1,10);
        IVelo v = new Velo('m');
        Assert.assertTrue(s.arrimerVelo(v,-4)==-1);
    }*/
    @Test
    public void testArrimerNoReg() {
        Station s = new Station("test",30,1,10);
        IVelo v = new Velo('m');
        Assert.assertTrue(s.arrimerVelo(v,1)==-2);
    }
    /*@Test
    public void testArrimerNoPlace() throws IncorrectNameException{
        Abonne a=new Abonne("Jean-Eude");
        IVelo v = new Velo('m');
        IVelo v2 = new Velo('m');
        IRegistre r= new JRegistre();
        Station s = new Station("test",30,1,10);
        s.setRegistre(r);
        s.arrimerVelo(v,1);
        Assert.assertTrue(s.arrimerVelo(v2,1)==-2);
    }
    @Test
    public void testArrimerNoArrimer() {
        IRegistre r= new JRegistre();
        Station s = new Station("test",30,1,10);
        IVelo v = new Velo('m');
        s.setRegistre(r);
        s.arrimerVelo(v,1);
        Assert.assertTrue(s.arrimerVelo(v,2)==-3);
    }
    @Test
    public void testArrimerSuccess() throws IncorrectNameException{
        IRegistre r= new JRegistre();
        Abonne a=new Abonne("Jean-Eude");
        Station s = new Station("test",30,1,10);
        IVelo v = new Velo('m');
        r.emprunter(a,v,2000000);
        s.setRegistre(r);
        Assert.assertEquals(0,s.arrimerVelo(v,1));
    }*/
    @Test
    public void testStationNbBornes() {
        IRegistre r= new JRegistre();
        Station s = new Station("test",30,1,10);
        IVelo v = new Velo('m');
        s.setRegistre(r);
        s.arrimerVelo(v,1);
        Assert.assertTrue(s.nbBornesLibres()==9);
    }
    @Test
    public void testStationNbBornesPlein() {
        Station s = new Station("test",30,1,10);
        Assert.assertTrue(s.nbBornesLibres()==10);
    }
    @Test
    public void testQuelVeloBorne() {
        IRegistre r= new JRegistre();
        Station s = new Station("test",30,1,10);
        IVelo v = new Velo('m');
        s.setRegistre(r);
        s.arrimerVelo(v,1);
        Assert.assertEquals(v,s.veloALaBorne(1));
    }
    @Test
    public void testAucunVeloBorne() {
        Station s = new Station("test",30,1,10);
        Assert.assertEquals(null,s.veloALaBorne(1));
    }

    /*@Test
    public void testStationEquilibrer1() throws IncorrectNameException {

        Abonne a = new Abonne("Jean-Eude","11111-11111-11111111111-11");
        IRegistre r = new JRegistre();

        Station s = new Station("test",30,1,3);
        s.setRegistre(r);

        IVelo v = new Velo();
        s.arrimerVelo(v,1);

        Set<IVelo> hash_Set = new HashSet<IVelo>();

        s.equilibrer(hash_Set);
        Assert.assertTrue(s.nbBornesLibres()==2);

    }

    @Test
    public void testStationEquilibrer2() throws IncorrectNameException {

        Abonne a = new Abonne("Jean-Eude","11111-11111-11111111111-11");
        IRegistre r = new JRegistre();

        // objet sous test
        Station s = new Station("test",30,1,3);
        s.setRegistre(r);

        IVelo v = new Velo();
        s.arrimerVelo(v,1);

        IVelo v2 = new Velo();
        s.arrimerVelo(v2,2);

        IVelo v3 = new Velo();
        s.arrimerVelo(v3,3);

        Set<IVelo> hash_Set = new HashSet<IVelo>();

        s.equilibrer(hash_Set);
        Assert.assertTrue(s.nbBornesLibres()==1);

    }

    @Test
    public void testStationEquilibrer3() throws IncorrectNameException {
        Abonne a = new Abonne("Jean-Eude","11111-11111-11111111111-11");
        IRegistre r = new JRegistre();

        // objet sous test
        Station s = new Station("test",30,1,3);
        s.setRegistre(r);

        IVelo v = new Velo();
        s.arrimerVelo(v,1);

        IVelo v2 = new Velo();
        s.arrimerVelo(v2,2);

        Set<IVelo> hash_Set = new HashSet<IVelo>();

        s.equilibrer(hash_Set);
        Assert.assertTrue(s.nbBornesLibres()==1);

    }

    @Test
    public void testStationEquilibrer4() throws IncorrectNameException {
        Abonne a = new Abonne("Jean-Eude","11111-11111-11111111111-11");
        Abonne a2 = new Abonne("Christophe","11111-11111-11111111111-11");
        IRegistre r = new JRegistre();

        Station s = new Station("test",30,1,3);
        s.setRegistre(r);

        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        s.emprunterVelo(a,1);
        v.abimer();
        s.arrimerVelo(v,1);

        IVelo v2 = new Velo();
        s.arrimerVelo(v2,2);
        s.emprunterVelo(a2,2);
        v2.abimer();
        s.arrimerVelo(v2,2);

        Set<IVelo> hash_Set = new HashSet<IVelo>();

        s.equilibrer(hash_Set);
        Assert.assertTrue(s.nbBornesLibres()==3);

    }

    @Test
    public void testStationEquilibrer5() throws IncorrectNameException {
        Abonne a = new Abonne("Jean-Eude","11111-11111-11111111111-11");
        IRegistre r = new JRegistre();

        // objet sous test
        Station s = new Station("test",30,1,3);
        s.setRegistre(r);

        IVelo v = new Velo();
        v.parcourir(500);
        s.arrimerVelo(v,1);

        IVelo v2 = new Velo();
        v.parcourir(500);
        s.arrimerVelo(v2,2);

        IVelo v3 = new Velo();
        v.parcourir(500);
        s.arrimerVelo(v3,3);

        Set<IVelo> hash_Set = new HashSet<IVelo>();

        s.equilibrer(hash_Set);
        Assert.assertTrue(s.nbBornesLibres()==1);

    }

    @Test
    public void testStationEquilibrer6() throws IncorrectNameException {
        Abonne a = new Abonne("Jean-Eude","11111-11111-11111111111-11");
        IRegistre r = new JRegistre();

        // objet sous test
        Station s = new Station("test",30,1,3);
        s.setRegistre(r);

        IVelo v = new Velo();
        v.parcourir(500);
        s.arrimerVelo(v,1);

        IVelo v2 = new Velo();
        v.parcourir(500);
        s.arrimerVelo(v2,2);


        Set<IVelo> hash_Set = new HashSet<IVelo>();
        IVelo neuf1 = new Velo();
        hash_Set.add(neuf1);
        IVelo neuf2 = new Velo();
        hash_Set.add(neuf2);

        s.equilibrer(hash_Set);
        Assert.assertTrue(s.nbBornesLibres()==1);
        if (s.veloALaBorne(1)!=null){
            Assert.assertTrue(s.veloALaBorne(1).prochaineRevision()==500);
        }
        if (s.veloALaBorne(2)!=null){
            Assert.assertTrue(s.veloALaBorne(2).prochaineRevision()==500);
        }
        if (s.veloALaBorne(3)!=null){
            Assert.assertTrue(s.veloALaBorne(3).prochaineRevision()==500);
        }

    }

    @Test
    public void testStationEquilibrer7() throws IncorrectNameException {
        Abonne a = new Abonne("Jean-Eude","11111-11111-11111111111-11");
        IRegistre r = new JRegistre();

        // objet sous test
        Station s = new Station("test",30,1,3);
        s.setRegistre(r);

        Set<IVelo> hash_Set = new HashSet<IVelo>();
        IVelo revise1 = new Velo();
        revise1.parcourir(500);
        hash_Set.add(revise1);
        IVelo neuf2 = new Velo();
        hash_Set.add(neuf2);

        s.equilibrer(hash_Set);
        Assert.assertTrue(s.nbBornesLibres()==1);

    }
    @Test
    public void test_Distance() throws IncorrectNameException {
        Abonne a = new Abonne("Jean-Eude","11111-11111-11111111111-11");
        IRegistre r = new JRegistre();

        // objet sous test
        Station s = new Station("test",30,1,10);
        s.setRegistre(r);

        Station s2 = new Station("test2",31,2,10);
        s2.setRegistre(r);

        Assert.assertEquals(146.7,s.distance(s2),0.1);


    }*/

    /**
     * Tests Set & Get
     */
    @Test
    public void testConstructorStationCapacityNeg() {
        Station s = new Station("Station_1", 10020, 12200, -1);
        assertEquals(s.capacite(),0);
    }

    @Test
    public void testConstructorStationName() {
        Station s = new Station("Station_1", 10020, 12200, 10);
        assertEquals(s.getNom(),"Station_1");
    }

    @Test
    public void testBornesLibresDepart() {
        Station s = new Station("Station_1", 10020, 12200, 10);
        assertEquals(s.nbBornesLibres(),10);
    }

    /**
     * Tests emprunter
     */
    @Test
    public void testEmprunterVeloSuccess() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        assertNotNull(s.emprunterVelo(a, 1));
    }

    @Test
    public void ck_testEmprunterVeloAbonneNull(){
        Station s = new Station("Station_1", 10020, 12200, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        assertNull(s.emprunterVelo(null,1));
    }

    @Test
    public void testEmprunterVeloBorneInexistante() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        assertNull(s.emprunterVelo(a,s.capacite()+1));
        assertNull(s.emprunterVelo(a,0));
        assertNull(s.emprunterVelo(a,-1));
    }

    @Test
    public void testEmprunterVeloBorneVide() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        assertNull(s.emprunterVelo(a,1));
    }

    @Test
    public void ck_testEmprunterVeloAbonneBloque() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        a.bloquer();
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        assertNull(s.emprunterVelo(a,1));
    }


    @Test
    public void ck_testEmprunterVeloRegistreNull() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        s.setRegistre(null);
        assertNull(s.emprunterVelo(a,1));
    }

    @Test
    public void testEmprunterVeloEmpruntEnCours() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v1 = new Velo();
        IVelo v2 = new Velo();
        s.arrimerVelo(v1,1);
        s.arrimerVelo(v2,2);
        assertNotNull(s.emprunterVelo(a, 1));
        assertNull(s.emprunterVelo(a, 2));
    }

    @Test
    public void testEmprunterVeloDejaDecroche() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        v.decrocher();
        assertNull(s.emprunterVelo(a, 1));
    }

    @Test
    public void testEmprunterVeloDejaEmprunte() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        Abonne b = new Abonne("Jeanne", "13341-89317-13746913443-92");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        assertNotNull(s.emprunterVelo(b, 1));
        s.arrimerVelo(v,2);
        assertNull(s.emprunterVelo(a, 2));
    }

    /**
     * Test arrimer
     */

    @Test
    public void testArrimerVeloSuccess() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        assertNotNull(s.emprunterVelo(a, 1));
        assertEquals(0,s.arrimerVelo(v, 1));
    }

    @Test
    public void ck_testArrimerVeloNull(){
        Station s = new Station("Station_1", 10020, 12200, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        assertEquals(s.arrimerVelo(null, 1),-1);
    }


    @Test
    public void testArrimerBorneInexistante() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        s.emprunterVelo(a,1);
        assertEquals(s.arrimerVelo(v,s.capacite()+1),-1);
        assertEquals(s.arrimerVelo(v,0),-1);
        assertEquals(s.arrimerVelo(v,-1),-1);
    }

    @Test
    public void testArrimerRegistreNull() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        s.emprunterVelo(a,1);
        assertEquals(s.arrimerVelo(v,2),-2);
    }

    @Test
    public void testArrimerBorneOccupee() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v1 = new Velo();
        IVelo v2 = new Velo();
        s.arrimerVelo(v1,1);
        s.arrimerVelo(v2,2);
        s.emprunterVelo(a,1);
        assertEquals(s.arrimerVelo(v1,2),-2);
    }

    @Test
    public void testArrimerVeloDejaArrime() throws IncorrectNameException {
        Station s = new Station("Station_1", 10020, 12200, 10);
        Abonne a = new Abonne("Charlotte", "19372-10383-09976354833-37");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        s.emprunterVelo(a,1);
        v.arrimer();
        assertEquals(s.arrimerVelo(v,1),-3);
    }

    @Test
    public void testArrimerErreurRegistre(){
        Station s = new Station("Station_1", 10020, 12200, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = new Velo();
        s.arrimerVelo(v,1);
        v.decrocher();
        assertEquals(s.arrimerVelo(v,2),-4);
        assertNotNull(s.veloALaBorne(2));
    }

    /**
     * Test equilibrer
     */

    @Test
    public void testEquilibrerCompleterBornesPaire(){
        Station s = new Station("Station_1", 10020, 12200, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        Set<IVelo> velos = new HashSet<>();
        for(int i=0;i<s.capacite();i++){
            velos.add(new Velo());
        }
        s.equilibrer(velos);
        assertEquals(5,s.nbBornesLibres());
    }

    @Test
    public void testEquilibrerCompleterBornesImpaire(){
        Station s = new Station("Station_1", 10020, 12200, 11);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        Set<IVelo> velos = new HashSet<>();
        for(int i=0;i<s.capacite();i++){
            velos.add(new Velo());
        }
        s.equilibrer(velos);
        assertEquals(5,s.nbBornesLibres());
    }

    @Test
    public void testEquilibrerPasAssezVelosCompleter(){
        Station s = new Station("Station_1", 10020, 12200, 11);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        Set<IVelo> velos = new HashSet<>();
        for(int i=0;i<3;i++){
            velos.add(new Velo());
        }
        s.equilibrer(velos);
        assertEquals(8,s.nbBornesLibres());
    }

    @Test
    public void testEquilibrerRemplacerAbime(){
        Station s = new Station("Station_1", 10020, 12200, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);

        //Ajout de 3 vélos abimés à la station
        IVelo v1 = new Velo();
        IVelo v2 = new Velo();
        IVelo v3 = new Velo();
        v1.abimer();
        v2.abimer();
        v3.abimer();
        s.arrimerVelo(v1,1);
        s.arrimerVelo(v2,3);
        s.arrimerVelo(v3,8);

        //Equilibré avec 6 vélos non abimés
        Set<IVelo> velos_remplacement = new HashSet<>();
        for(int i=0;i<6;i++){
            velos_remplacement.add(new Velo());
        }
        s.equilibrer(velos_remplacement);

        //Vérifie qu'ils ont été changés
        for(int i=0;i<s.capacite();i++){
            IVelo v =s.veloALaBorne(i);
            if(v!=null ){
                assertFalse(v.estAbime());
            }
        }

        //Vérifie qu'ils ont été ajoutés au Set
        int nb_abime=0;
        for(IVelo v : velos_remplacement) {
            if(v!=null && v.estAbime()){
                nb_abime+=1;
            }
        }
        assertEquals(3, nb_abime);
        assertEquals(4, velos_remplacement.size());
        assertEquals(5,s.nbBornesLibres());
    }

    @Test
    public void testEquilibrerRemplacerAbimePasAssezSecours(){
        Station s = new Station("Station_1", 10020, 12200, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);

        //Ajout de 3 vélos abimés à la station
        IVelo v1 = new Velo();
        IVelo v2 = new Velo();
        IVelo v3 = new Velo();
        v1.abimer();
        v2.abimer();
        v3.abimer();
        s.arrimerVelo(v1,1);
        s.arrimerVelo(v2,3);
        s.arrimerVelo(v3,8);

        //Equilibré avec 1 vélo non abimé
        Set<IVelo> velos_remplacement = new HashSet<>();
        velos_remplacement.add(new Velo());
        s.equilibrer(velos_remplacement);

        //Vérifie qu'ils ont été enlevés
        int nb_abime=0;
        for(int i=0;i<s.capacite();i++){
            IVelo v =s.veloALaBorne(i);
            if(v!=null){
                assertFalse(v.estAbime());
            }
        }

        //Vérifie qu'ils ont été ajoutés au Set
        for(IVelo v : velos_remplacement) {
            if(v!=null && v.estAbime()){
                nb_abime+=1;
            }
        }

        assertEquals(3, nb_abime);
        assertEquals(3, velos_remplacement.size());
        assertEquals(9,s.nbBornesLibres());
    }

    @Test
    public void testEquilibrerRemplacerReviser(){
        Station s = new Station("Station_1", 10020, 12200, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);

        //Ajout de 3 vélos à réviser
        IVelo v1 = new Velo();
        IVelo v2 = new Velo();
        IVelo v3 = new Velo();
        v1.parcourir(600);
        v2.parcourir(650);
        v3.parcourir(660);
        s.arrimerVelo(v1,1);
        s.arrimerVelo(v2,3);
        s.arrimerVelo(v3,8);

        Set<IVelo> velos_remplacement = new HashSet<>();
        for(int i=0;i<s.capacite();i++){
            velos_remplacement.add(new Velo());
        }
        s.equilibrer(velos_remplacement);


        //Vérifie qu'ils ont été ajoutés au Set
        int nb_revise=0;
        for(IVelo v : velos_remplacement) {
            if(v!=null && v.kilometrage()!=0){
                nb_revise+=1;
            }
        }
        assertEquals(3, nb_revise);
        assertEquals(5, s.nbBornesLibres());
    }


    @Test
    public void testEquilibrerRemplacerReviserPasAssez(){
        Station s = new Station("Station_1", 10020, 12200, 6);
        IRegistre r = new JRegistre();
        s.setRegistre(r);

        //Ajout de 3 vélos à réviser
        IVelo v1 = new Velo();
        IVelo v2 = new Velo();
        IVelo v3 = new Velo();
        v1.parcourir(600);
        v2.parcourir(650);
        v3.parcourir(660);
        s.arrimerVelo(v1,1);
        s.arrimerVelo(v2,3);
        s.arrimerVelo(v3,5);

        //Equilibré avec 1 vélo non abimé
        Set<IVelo> velos_remplacement = new HashSet<>();
        velos_remplacement.add(new Velo());
        s.equilibrer(velos_remplacement);

        //Vérifie qu'ils ont été ajoutés au Set
        int nb_revise=0;
        for(IVelo v : velos_remplacement) {
            if(v!=null && v.kilometrage()>=500){
                nb_revise+=1;
            }
        }
        assertEquals(1, nb_revise);
        assertEquals(1, velos_remplacement.size());
    }


    @Test
    public void testDistancePositive() {
        Station s1 = new Station("Station_1", 10000, 12000, 10);
        Station s2 = new Station("Station_2", 20000, 14000, 8);
        assertEquals(9326,s1.distance(s2),1);
    }

    @Test
    public void testDistanceNegative() {
        Station s1 = new Station("Station_1", -10000, 12000, 10);
        Station s2 = new Station("Station_2", 20000, -14000, 8);
        assertEquals(13981,s1.distance(s2),1);
    }


}
