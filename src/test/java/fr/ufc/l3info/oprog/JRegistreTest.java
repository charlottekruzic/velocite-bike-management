package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


public class JRegistreTest {

    private static final double DELTA = 1e-3;

    /*test emprunter*/

    @Test
    public void testEmprunterAbonneNull(){
        // création d'un registre, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification de l'emprunt avec abonne null
        assertEquals(-1, registre.emprunter(null, velo,s.maintenant()));

    }

    @Test
    public void testEmprunterVeloNull() throws IncorrectNameException{
        // création d'un registre, d'un abonne et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification de l'emprunt avec velo null
        assertEquals(-1, registre.emprunter(abonne, null,s.maintenant()));

    }

    @Test
    public void testEmprunterVeloDejaEmprunte() throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        registre.emprunter(abonne, velo,s.maintenant());

        // vérification de l'emprunt avec un velo déjà emprunté
        assertEquals(-2, registre.emprunter(abonne, velo,s.maintenant()));

    }

    @Test
    public void testEmprunterVelo() throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        long maintenant = System.currentTimeMillis();
        long apres10minutes = maintenant + 10 * 60 * 1000;
        long apres20minutes = maintenant + 20 * 60 * 1000;

        registre.emprunter(abonne, velo, maintenant);
        registre.retourner(velo, apres20minutes);

        // vérification de l'emprunt
        assertEquals(-2, registre.emprunter(abonne,velo,apres10minutes));
    }



    /*Test retourner*/

    @Test
    public void testRetournerVeloNull(){
        // création d'un registre et d'une station
        IRegistre registre = new JRegistre();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du retour avec velo null
        assertEquals(-1, registre.retourner(null,s.maintenant()));

    }

    @Test
    public void testRetournerVeloPasEmprunte(){
        // création d'un registre, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du retour sans emprunt
        assertEquals(-2, registre.retourner(velo,s.maintenant()));

    }

    @Test
    public void testRetournerAvantEmprunt() throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        long maintenant = System.currentTimeMillis();
        long avant10minutes = maintenant - 10 * 60 * 1000;

        registre.emprunter(abonne, velo, maintenant);

        // vérification du retour avant emprunt
        assertEquals(-3, registre.retourner(velo,avant10minutes));

    }

    @Test
    public void testRetournerEmpruntParVelo() throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        long maintenant = System.currentTimeMillis();
        long apres10minutes = maintenant + 10 * 60 * 1000;
        long apres20minutes = maintenant + 20 * 60 * 1000;
        long avant20minutes = maintenant - 20 * 60 * 1000;

        registre.emprunter(abonne, velo, maintenant);
        registre.retourner(velo, apres20minutes);
        registre.emprunter(abonne,velo,avant20minutes);

        // vérification du retour
        assertEquals(-3, registre.retourner(velo,apres10minutes));

    }

    @Test
    public void testRetourner() throws IncorrectNameException{
        // création d'un registre, d'un abonne, des velos et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        IVelo velo2 = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        long maintenant = System.currentTimeMillis();
        long apres10minutes = maintenant + 10 * 60 * 1000;

        registre.emprunter(abonne, velo, maintenant);
        registre.retourner(velo,apres10minutes);
        registre.emprunter(abonne, velo2, maintenant);

        // vérification du retour
        assertEquals(0, registre.retourner(velo2,apres10minutes));

    }

    @Test
    public void testRetourner2() throws IncorrectNameException{
        // création d'un registre, d'un abonne, des velos et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        IVelo velo2 = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        long maintenant = System.currentTimeMillis();
        long apres10minutes = maintenant + 10 * 60 * 1000;
        long apres20minutes = maintenant + 20 * 60 * 1000;

        registre.emprunter(abonne, velo, maintenant);
        registre.retourner(velo,apres10minutes);
        registre.emprunter(abonne, velo2, maintenant);

        // vérification du retour
        assertEquals(0, registre.retourner(velo2,apres20minutes));

    }



    /*Test nbEmpruntsEnCours*/
    @Test
    public void testNbEmpruntsEnCours() throws IncorrectNameException{
        // création d'un registre, d'un abonne, des velos et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        IVelo velo2 = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        registre.emprunter(abonne, velo, s.maintenant());
        registre.emprunter(abonne, velo2, s.maintenant());

        // vérification du nombre d'emprunt en cours
        assertEquals(2, registre.nbEmpruntsEnCours(abonne));

    }

    @Test
    public void testNbEmpruntsEnCours0() throws IncorrectNameException{
        // création d'un registre, d'un abonne et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du nombre d'emprunt en cours
        assertEquals(0, registre.nbEmpruntsEnCours(abonne));

    }

    @Test
    public void testNbEmpruntsEnCours2() throws IncorrectNameException{
        // création d'un registre, d'un abonne, des velos et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        IVelo velo2 = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        long maintenant = System.currentTimeMillis();
        long apres20minutes = maintenant + 20 * 60 * 1000;

        registre.emprunter(abonne, velo, maintenant);
        registre.retourner(velo, apres20minutes);
        registre.emprunter(abonne, velo2, maintenant);

        // vérification du nombre d'emprunt en cours
        assertEquals(1, registre.nbEmpruntsEnCours(abonne));

    }


    /*test facturation*/

    @Test
    public void testFacturation() throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        long maintenant = System.currentTimeMillis();
        long fin = maintenant + 20 * 60 * 1000;

        registre.emprunter(abonne, velo, maintenant);
        registre.retourner(velo,fin);

        // vérification de la facturation
        assertEquals(0.666, registre.facturation(abonne, maintenant,fin),DELTA);

    }

    @Test
    public void testFacturationEnCours() throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        long maintenant = System.currentTimeMillis();
        long fin = maintenant + 20 * 60 * 1000;

        registre.emprunter(abonne, velo, maintenant);

        // vérification de la facturation
        assertEquals(0, registre.facturation(abonne, maintenant,fin),DELTA);

    }

    @Test
    public void testFacturation2() throws IncorrectNameException{
        // création d'un registre, d'un abonne, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);
        long maintenant = System.currentTimeMillis();
        long fin = maintenant + 20 * 60 * 1000;

        // vérification de la facturation
        assertEquals(0, registre.facturation(abonne, maintenant,fin),DELTA);

    }
    /*@Test
    public void testJRegistreEmpruntReussi() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= new Abonne("Jean-Mi");
        IVelo v= new Velo('m');
        Assert.assertEquals(0,re.emprunter(a,v,10102022));
    }*/
    /*@Test
    public void testJRegistreEmprunterReussi2VelosMemePersonne () throws IncorrectNameException {
        IRegistre r = new JRegistre();
        IVelo v = new Velo();
        Abonne a = new Abonne("Jean-Mi");
        r.emprunter(a,v,10102022);
        IVelo v2 = new Velo();
        Assert.assertEquals(0,r.emprunter(a,v2,10102022));
    }*/
    /*@Test
    public void testJRegistreEmprunterReussi2velos2Personnes() throws IncorrectNameException {
        IRegistre r = new JRegistre();
        IVelo v = new Velo();
        Abonne a = new Abonne("Jean-Mi");
        r.emprunter(a,v,10102022);
        Abonne b = new Abonne("Marc-Henri");
        IVelo v2 = new Velo();
        Assert.assertEquals(r.emprunter(b,v2,10102022),0);
    }*/
    @Test
    public void testJRegistreEmpruntPasReussiPasAbonne() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= null;
        IVelo v= new Velo('m');
        Assert.assertEquals(-1,re.emprunter(a,v,10102022));
    }
    /*@Test
    public void testJRegistreEmpruntPasReussiPasVelo() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= new Abonne("Jean-Mi");
        IVelo v= null;
        Assert.assertEquals(-1,re.emprunter(a,v,10102022));
    }*/
    @Test
    public void testJRegistreEmpruntPasReussiPasVeloEtAbonne() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= null;
        IVelo v= null;
        Assert.assertEquals(-1,re.emprunter(a,v,10102022));
    }
    /*@Test
    public void testJRegistreEmpruntPasReussiDejaEmprunter() throws IncorrectNameException {
        JRegistre re= new JRegistre();
        Abonne a= new Abonne("Jean-Mi");
        Abonne b=new Abonne("Marc-Henri");
        IVelo v= new Velo('m');

        Assert.assertEquals(0,re.emprunter(b,v,02102022));
        Assert.assertEquals(-2,re.emprunter(a,v,10102022));
    }*/

    /*@Test
    public void testJRegistreEmpruntPasReussiEmpruntCarDejaEmprunterEtPasRendu() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= new Abonne("Jean-Mi");
        IVelo v= new Velo('m');
        re.emprunter(a,v,02102022);
        Assert.assertEquals(-2,re.emprunter(a,v,10102022));
    }*/
    /*@Test
    public void testJRegistreRetournerReussi() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= new Abonne("Jean-Mi");
        IVelo v= new Velo('m');
        re.emprunter(a,v,02102022);
        Assert.assertEquals(0,re.retourner(v,11102022));
    }*/
    /*@Test
    public void testJRegistreRetournerReussi2() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= new Abonne("Jean-Mi");
        IVelo v= new Velo('m');
        re.emprunter(a,v,02102022);
        Assert.assertEquals(0,re.retourner(v,02102022));
    }*/
    /*@Test
    public void testJRegistreRetournerPasReussiVeloNull() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= new Abonne("Jean-Mi");
        IVelo v= null;
        re.emprunter(a,v,02102022);
        Assert.assertEquals(-1,re.retourner(v,11102022));
    }*/
    /*@Test
    public void testJRegistreRetournerPasReussiPasEmprunter() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= new Abonne("Jean-Mi");
        IVelo v= new Velo('m');
        Assert.assertEquals(-2,re.retourner(v,11102022));
    }*/

    /*@Test
    public void testJRegistreRetournerPasReussiPbDateAnterieur() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= new Abonne("Jean-Mi");
        IVelo v= new Velo('m');
        re.emprunter(a,v,12102022);
        Assert.assertEquals(-3,re.retourner(v,11102022));
    }*/
    /*@Test
    public void testJRegistreRetournerPasReussiPbDateChevauchementempruntVelo() throws IncorrectNameException{
        JRegistre re= new JRegistre();
        Abonne a= new Abonne("Jean-Mi");
        Abonne b=new Abonne("Marc-Henri");
        IVelo v= new Velo('m');
        long dans10minutes = System.currentTimeMillis() + 10 * 60 * 1000;
        long dans20minutes =System.currentTimeMillis() + 20 * 60 * 1000;
        long dans5minutes=System.currentTimeMillis() + 5 * 60 * 1000;
        re.emprunter(a,v,dans10minutes);
        re.retourner(v,dans20minutes);
        re.emprunter(b,v,dans5minutes);
        Assert.assertEquals(-3,re.retourner(v,11102022));
    }*/
    /*@Test
    public void testJRegistreRetournerPasempruntenCours() throws IncorrectNameException{
        IRegistre r = new JRegistre();
        IVelo v = new Velo();
        Abonne a = new Abonne("Jean-Mi");
        r.emprunter(a,v,12102022);
        r.retourner(v,12102023);
        Assert.assertEquals(-2,r.retourner(v,12102024));
    }*/

    /*@Test
    public void testJRegistreEmprunterRetourner() throws IncorrectNameException{
        IRegistre r = new JRegistre();
        IVelo v = new Velo();
        Abonne a = new Abonne("Jean-Mi");
        r.emprunter(a,v,12102022);
        r.retourner(v,12102022);
        Assert.assertEquals(0,r.emprunter(a,v,12102023));
        Assert.assertEquals(0,r.retourner(v,12102024));
    }*/
    /*@Test
    public void testJRegistreNbEmpruntsPasEnCoursEmprunt() throws IncorrectNameException{
        IRegistre r = new JRegistre();
        Abonne a = new Abonne("Jean-Mi");
        Assert.assertEquals(0,r.nbEmpruntsEnCours(a));
    }*/

    /*@Test
    public void testJRegistreNbEmpruntsEnCours() throws IncorrectNameException{
        IRegistre r = new JRegistre();
        IVelo v = new Velo();
        Abonne a = new Abonne("Jean-Mi");
        r.emprunter(a,v,12102022);
        Assert.assertEquals(1,r.nbEmpruntsEnCours(a));
    }*/

    /*@Test
    public void testJRegistreNbEmpruntsEnCours2() throws IncorrectNameException{
        IRegistre r = new JRegistre();
        IVelo v = new Velo();
        IVelo v2 = new Velo();
        Abonne a = new Abonne("Jean-Mi");
        r.emprunter(a,v,12102022);
        r.emprunter(a,v2,12102022);
        Assert.assertEquals(2,r.nbEmpruntsEnCours(a));
    }*/

    /*@Test
    public void testJRegistreNbEmpruntsEnCoursRetourner() throws IncorrectNameException{
        IRegistre r = new JRegistre();
        IVelo v = new Velo();
        IVelo v2 = new Velo();
        Abonne a = new Abonne("Jean-Mi");
        r.emprunter(a,v,12102022);
        r.emprunter(a,v2,12102022);
        r.retourner(v2,12102024);
        Assert.assertEquals(1,r.nbEmpruntsEnCours(a));
    }*/
    /*@Test
    public void testJRegistreFacturation() throws IncorrectNameException{
        IRegistre r = new JRegistre();
        Abonne a = new Abonne("Jean-Mi");
        Assert.assertEquals(r.facturation(a,0,1000),0.0,0.1);
    }*/

    /*@Test
    public void testFacturation_2() throws IncorrectNameException{
        IRegistre r = new JRegistre();
        IVelo v = new Velo();
        Abonne a = new Abonne("Jean-Mi");
        r.emprunter(a,v,1);
        r.retourner(v,99999998);
        Assert.assertEquals(55.5 ,r.facturation(a,0,99999999),0.1);
    }*/

    /*@Test
    public void testJRegistreFacturation3() throws IncorrectNameException{
        IRegistre r = new JRegistre();
        IVelo v = new Velo();
        Abonne a = new Abonne("Jean-Mi");
        r.emprunter(a,v,555555555);
        r.retourner(v,999999996);
        Assert.assertEquals(246.9 ,r.facturation(a,0,999999999),0.1);
    }*/

    /*@Test
    public void testJRegistreFacturation4() throws IncorrectNameException{
        IRegistre r = new JRegistre();
        IVelo v = new Velo();
        Abonne a = new Abonne("Jean-Mi");
        r.emprunter(a,v,1);
        Assert.assertEquals(0.0 ,r.facturation(a,0,999999999),0.1);
    }*/


    /**
     * Test emprunter vélo
     */
    @Test
    public void testEmprunterSucces() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        assertEquals(r.emprunter(a,v,s.maintenant()),0);
    }

    @Test
    public void testEmprunterAbonneeNull() {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        assertEquals(r.emprunter(null,v,s.maintenant()),-1);
    }

    @Test
    public void ck_testEmprunterVeloNull() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        assertEquals(r.emprunter(a,null,s.maintenant()),-1);
    }

    @Test
    public void ck_testEmprunterVeloDejaEmprunte() throws IncorrectNameException{
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a1=new Abonne("Charlotte", "19372-10383-09976354833-37");
        Abonne a2=new Abonne("Ian", "19372-10383-09976354833-37");
        r.emprunter(a2,v,s.maintenant()- 10 * 60 * 1000);
        assertEquals(r.emprunter(a1,v,s.maintenant()),-2);
    }

    @Test
    public void testEmprunterVeloDejaEmprunteMemeDate() throws IncorrectNameException{
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a1=new Abonne("Charlotte", "19372-10383-09976354833-37");
        Abonne a2=new Abonne("Ian", "19372-10383-09976354833-37");
        r.emprunter(a2,v,s.maintenant());
        assertEquals(r.emprunter(a1,v,s.maintenant()),-2);
    }

    @Test
    public void testEmprunterPlusieursVelos() throws IncorrectNameException{
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v1=new Velo();
        IVelo v2=new Velo();
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        r.emprunter(a,v1,s.maintenant()- 10 * 60 * 1000);
        assertEquals(r.emprunter(a,v2,s.maintenant()),0);
    }

    @Test
    public void testEmprunterVeloEmprunteAvant() throws IncorrectNameException{
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        r.emprunter(a,v,s.maintenant()- 10 * 60 * 1000);
        r.retourner(v,s.maintenant());
        assertEquals(r.emprunter(a,v,s.maintenant()+ 10 * 60 * 1000),0);
    }

    @Test
    public void testEmprunterVeloEmprunteAvantAuMemeMoment() throws IncorrectNameException{
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        r.emprunter(a,v,s.maintenant()- 10 * 60 * 1000);
        r.retourner(v,s.maintenant());
        assertEquals(r.emprunter(a,v,s.maintenant()- 10 * 60 * 1000),-2);
    }

    /**
     * Test retourner vélo
     */
    @Test
    public void testRetournerVeloSucces() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        r.emprunter(a,v,s.maintenant());
        assertEquals(r.retourner(v,s.maintenant()+ 10 * 60 * 1000),0);
    }

    @Test
    public void ck_testRetournerVeloNull(){
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        assertEquals(r.retourner(null,s.maintenant()),-1);
    }

    @Test
    public void testRetournerVeloNonEmprunte(){
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        assertEquals(r.retourner(v,s.maintenant()),-2);
    }

    @Test
    public void testRetournerVeloDateAnterieurEmprunt() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        r.emprunter(a,v,s.maintenant()+ 10 * 60 * 1000);
        assertEquals(r.retourner(v,s.maintenant()),-3);
    }

    @Test
    public void testRetournerEntreAutreEmprunt() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        r.emprunter(a,v,s.maintenant()- 10 * 60 * 1000);
        r.retourner(v,s.maintenant()+ 10 * 60 * 1000);
        r.emprunter(a,v,s.maintenant()- 20 * 60 * 1000);
        assertEquals(r.retourner(v,s.maintenant()),-3);
    }

    @Test
    public void testRetournerVeloDateChevauchant() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a1=new Abonne("Charlotte", "19372-10383-09976354833-37");
        Abonne a2=new Abonne("Jade", "19372-10383-09976354833-37");
        r.emprunter(a2,v,s.maintenant());
        r.retourner(v,s.maintenant()+ 20 * 60 * 1000);
        r.emprunter(a1,v,s.maintenant()+ 30 * 60 * 1000);
        assertEquals(r.retourner(v,s.maintenant()+ 10 * 60 * 1000),-3);
    }

    @Test
    public void testRetournerVeloRetourneAvant() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        r.emprunter(a,v,s.maintenant());
        r.retourner(v,s.maintenant()+ 10 * 60 * 1000);
        r.emprunter(a,v,s.maintenant()+ 20 * 60 * 1000);
        assertEquals(r.retourner(v,s.maintenant()+ 30 * 60 * 1000),0);
    }

    @Test
    public void testRetournerVeloNv() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        IVelo v=new Velo();
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        r.emprunter(a,v,s.maintenant());
        r.retourner(v,s.maintenant()+ 10 * 60 * 1000);
        r.emprunter(a,v,s.maintenant()+ 20 * 60 * 1000);
        assertEquals(r.retourner(v,s.maintenant()+ 30 * 60 * 1000),0);
    }

    /**
     * Test nombre d'emprunts en cours
     */
    @Test
    public void testNbEmpruntAbonnee() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        IVelo v1=new Velo();
        IVelo v2=new Velo();
        IVelo v3=new Velo();
        v1.arrimer();
        v2.arrimer();
        v3.arrimer();
        r.emprunter(a,v1,s.maintenant());
        r.emprunter(a,v2,s.maintenant()+ 10 * 60 * 1000);
        r.emprunter(a,v3,s.maintenant()+ 10 * 60 * 1000);
        assertEquals(r.nbEmpruntsEnCours(a),3);
    }

    @Test
    public void testNbEmpruntAbonneeAvecRetour() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        IVelo v1=new Velo();
        IVelo v2=new Velo();
        IVelo v3=new Velo();
        v1.arrimer();
        v2.arrimer();
        v3.arrimer();
        r.emprunter(a,v1,s.maintenant());
        r.emprunter(a,v2,s.maintenant()+ 10 * 60 * 1000);
        r.emprunter(a,v3,s.maintenant()+ 10 * 60 * 1000);
        r.retourner(v2,s.maintenant()+ 20 * 60 * 1000);
        assertEquals(r.nbEmpruntsEnCours(a),2);
    }

    @Test
    public void testNbEmpruntAbonneePasEmprunt() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        assertEquals(r.nbEmpruntsEnCours(a),0);
    }

    @Test
    public void testNbEmpruntAbonneeNull(){
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        assertEquals(r.nbEmpruntsEnCours(null),0);
    }

    /**
     * Tests facturation
     */
    @Test
    public void testFacturationUnVelo() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        IVelo v=new Velo();
        v.arrimer();
        long emprunt=s.maintenant();
        long rendu=s.maintenant()+ 10 * 60 * 1000;
        r.emprunter(a,v,emprunt);
        r.retourner(v,rendu);
        assertEquals(0.333, r.facturation(a,emprunt,rendu), DELTA);
    }

    @Test
    public void testFacturationPlusieursVeloSuite() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        IVelo v1=new Velo();
        IVelo v2=new Velo();
        long emprunt1=s.maintenant();
        long rendu1=s.maintenant()+ 10 * 60 * 1000;
        long emprunt2=s.maintenant()+ 11 * 60 * 1000;
        long rendu2=s.maintenant()+ 21 * 60 * 1000;
        r.emprunter(a,v1,emprunt1);
        r.retourner(v1,rendu1);
        r.emprunter(a,v2,emprunt2);
        r.retourner(v2,rendu2);
        assertEquals(0.666, r.facturation(a,emprunt1,rendu2), DELTA);
    }

    @Test
    public void testFacturationPlusieursVelosParallele() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        IVelo v1=new Velo();
        IVelo v2=new Velo();
        long emprunt1=s.maintenant();
        long rendu1=s.maintenant()+ 10 * 60 * 1000;
        long emprunt2=s.maintenant()+ 5 * 60 * 1000;
        long rendu2=s.maintenant()+ 15 * 60 * 1000;
        r.emprunter(a,v1,emprunt1);
        r.retourner(v1,rendu1);
        r.emprunter(a,v2,emprunt2);
        r.retourner(v2,rendu2);
        assertEquals(0.666, r.facturation(a,emprunt1,rendu2), DELTA);
    }

    @Test
    public void testFacturationVeloEnCours() throws IncorrectNameException {
        Station s = new Station("Station_1", 20102, 12002,10);
        JRegistre r=new JRegistre();
        s.setRegistre(r);
        Abonne a=new Abonne("Charlotte", "19372-10383-09976354833-37");
        IVelo v=new Velo();
        long emprunt=s.maintenant()+ 10 * 60 * 1000;
        long debut_facturation=s.maintenant()+ 5 * 60 * 1000;
        long fin_facturation=s.maintenant()+ 20 * 60 * 1000;
        r.emprunter(a,v,emprunt);
        assertEquals(0, r.facturation(a,debut_facturation,fin_facturation), DELTA);
    }

}
