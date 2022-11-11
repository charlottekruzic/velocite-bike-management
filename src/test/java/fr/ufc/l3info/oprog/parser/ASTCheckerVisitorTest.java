package fr.ufc.l3info.oprog.parser;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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
    public void testASTListeStationsNameNull() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationNullName.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();

        n.accept(v);
        assertEquals(1, v.getErrors().size());


    }

    @Test
    public void testASTListeStationsDuplicateName() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationD uplicateName.txt"));
        ASTCheckerVisitor v = new ASTCheckerVisitor();

        n.accept(v);
        assertEquals(1, v.getErrors().size());


    }
}
