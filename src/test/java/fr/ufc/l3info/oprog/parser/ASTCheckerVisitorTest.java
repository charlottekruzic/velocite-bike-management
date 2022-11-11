package fr.ufc.l3info.oprog.parser;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ASTCheckerVisitorTest {
    final String path = "./target/classes/data/";
    final StationParser parser = StationParser.getInstance();


    @Test
    public void testASTListeStations() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationsOK.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();

        n.accept(v);
        assertEquals(0, v.getErrors().size());


    }

    @Test
    public void testASTListeStations2() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationNull.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();

        n.accept(v);
        assertEquals(1, v.getErrors().size());
    }

    @Test
    public void testVisitorStationOK() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsOK.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        assertEquals(0,v.getErrors().size());
    }

    @Test
    public void testVisitorFichierVide() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "vide.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.EMPTY_LIST));
    }

    @Test
    public void testVisitorNomsStationsNonUnique() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "nomStationsNonUnique.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
    }

    @Test
    public void testVisitorNomVide() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "nomStationVide.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.EMPTY_STATION_NAME));
    }

    @Test
    public void testVisitorNomEspace() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "nomStationEspace.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.EMPTY_STATION_NAME));
    }

    @Test
    public void testVisitorNomTabulation() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "nomStationTabulation.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.EMPTY_STATION_NAME));
    }

    @Test
    public void testVisitorIdentificateursManquants() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "identificateursManquants.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);

        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.MISSING_DECLARATION));
    }

    @Test //(expected= StationParserException.class)
    public void testVisitorIdentificateursSimilaires() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "identificateursSimilaires.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
    }

    @Test
    public void testVisitorOrdreIdentificateursDifferent() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "ordreIdentificateursDifferent.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        assertEquals(0,v.getErrors().size());
    }

    @Test
    public void testVisitorCapaciteNegative() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "capaciteNegative.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
    }

    @Test
    public void testVisitorCapaciteNulle() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "capaciteNulle.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
    }

    @Test
    public void testVisitorCapaciteAvecDecimale() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "capaciteAvecDecimale.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
    }

    @Test
    public void testVisitorCapaciteAvecDecimaleNulle() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "capaciteAvecDecimaleNulle.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();
        n.accept(v);
        Map<String, ERROR_KIND> erreurs= v.getErrors();
        assertEquals(1,erreurs.size());
        assertTrue(erreurs.containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
    }
}
