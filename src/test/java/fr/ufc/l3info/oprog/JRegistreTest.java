package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;



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
        Assert.assertEquals(-1, registre.emprunter(null, velo,s.maintenant()));

    }

    @Test
    public void testEmprunterVeloNull() throws IncorrectNameException{
        // création d'un registre, d'un abonne et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification de l'emprunt avec velo null
        Assert.assertEquals(-1, registre.emprunter(abonne, null,s.maintenant()));

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
        Assert.assertEquals(-2, registre.emprunter(abonne, velo,s.maintenant()));

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
        Assert.assertEquals(-2, registre.emprunter(abonne,velo,apres10minutes));
    }



    /*Test retourner*/

    @Test
    public void testRetournerVeloNull(){
        // création d'un registre et d'une station
        IRegistre registre = new JRegistre();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du retour avec velo null
        Assert.assertEquals(-1, registre.retourner(null,s.maintenant()));

    }

    @Test
    public void testRetournerVeloPasEmprunte(){
        // création d'un registre, d'un velo et d'une station
        IRegistre registre = new JRegistre();
        IVelo velo = new Velo();
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du retour sans emprunt
        Assert.assertEquals(-2, registre.retourner(velo,s.maintenant()));

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
        Assert.assertEquals(-3, registre.retourner(velo,avant10minutes));

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
        Assert.assertEquals(-3, registre.retourner(velo,apres10minutes));

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
        Assert.assertEquals(0, registre.retourner(velo2,apres10minutes));

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
        Assert.assertEquals(0, registre.retourner(velo2,apres20minutes));

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
        Assert.assertEquals(2, registre.nbEmpruntsEnCours(abonne));

    }

    @Test
    public void testNbEmpruntsEnCours0() throws IncorrectNameException{
        // création d'un registre, d'un abonne et d'une station
        IRegistre registre = new JRegistre();
        Abonne abonne = new Abonne("jade");
        Station s = new Station("Gare Viotte",  47.24650155142733,6.022715427111734,10);

        // vérification du nombre d'emprunt en cours
        Assert.assertEquals(0, registre.nbEmpruntsEnCours(abonne));

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
        Assert.assertEquals(1, registre.nbEmpruntsEnCours(abonne));

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
        Assert.assertEquals(0.666, registre.facturation(abonne, maintenant,fin),DELTA);

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
        Assert.assertEquals(0, registre.facturation(abonne, maintenant,fin),DELTA);

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
        Assert.assertEquals(0, registre.facturation(abonne, maintenant,fin),DELTA);

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


}
