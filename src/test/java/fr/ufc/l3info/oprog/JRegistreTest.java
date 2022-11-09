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


}
