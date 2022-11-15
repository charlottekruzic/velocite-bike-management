package fr.ufc.l3info.oprog.parser;

import org.junit.Assert;
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
    public void testASTListeStationsListeNull() throws StationParserException, IOException {
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

    @Test
    public void testASTListeStationsNameNull() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationNullName.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();

        n.accept(v);
        assertEquals(1, v.getErrors().size());


    }

    @Test
    public void testASTListeStationsDuplicateName() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationDuplicateName.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();

        n.accept(v);
        assertEquals(1, v.getErrors().size());


    }

    @Test
    public void testASTListeStationsDuplicateDeclaration() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationsDuplicateDeclaration.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();

        n.accept(v);
        assertEquals(1, v.getErrors().size());


    }

    @Test
    public void testASTListeStationsMissingDeclaration() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationsMissingDeclaration.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();

        n.accept(v);
        assertEquals(1, v.getErrors().size());


    }


    @Test
    public void testASTListeStationsWRONG_NUMBER_VALUE() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationsWrongNumberValue.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();

        n.accept(v);
        assertEquals(1, v.getErrors().size());
    }

    @Test
    public void testVisitorStationsOK() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationsOK.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertFalse(cv.getErrors().containsValue(ERROR_KIND.EMPTY_LIST)||cv.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME)||cv.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME)||cv.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION)||cv.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION)||cv.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
    }
    @Test
    public void testVisitorstationvide() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKO_stationvide.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.EMPTY_LIST));
    }
    @Test
    public void testVisitordouble_nom_station() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKO_double_nom_station.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
    }
    @Test
    public void testVisitorstationsKO_double_nom_vide() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKO_double_nom_vide.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
    }
    @Test
    public void testVisitornom_station_vide_espace() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKO_nom_station_vide_espace.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
    }
    @Test
    public void testVisitornom_station_vide() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKO_nom_station_vide.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
    }
    @Test
    public void testVisitordouble_identificateur() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKO_double_identificateur.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
    }
    @Test
    public void testVisitormanque_1_identificateur() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKO_manque_1_identificateur.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
    }
    @Test
    public void testVisitormanque_identificateurs() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKO_manque_identificateurs.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
    }
    @Test
    public void testVisitorcapacite_number_neg() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKO_capacite_number_neg.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
    }
    @Test
    public void testVisitorcapacite_error_number() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKO_capacite_error_number.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
    }
    @Test
    public void testVisitorKOmulti_errors() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "stationsKOmulti_errors.txt"));
        ASTCheckerVisitor cv = new ASTCheckerVisitor();
        n.accept(cv);
        Assert.assertTrue(cv.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE) && cv.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME) && cv.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION) && cv.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
    }
}
