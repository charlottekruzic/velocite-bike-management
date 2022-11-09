package fr.ufc.l3info.oprog.parser;



import java.util.HashMap;
import java.util.Map;

/**
 * Visiteur réalisant des vérifications sur l'AST du fichier de stations.
 */
public class ASTCheckerVisitor implements ASTNodeVisitor {
    Map<String, ERROR_KIND> errors =  new HashMap<>();
    public ASTCheckerVisitor() {



        // TODO
    }

    public Map<String, ERROR_KIND> getErrors() {
        return errors;
    }

    @Override
    public Object visit(ASTNode n) { return null; } //on laisse null

    @Override
    public Object visit(ASTListeStations n) {
        if(n.getNumChildren()==0){

            errors.put(n.getLCPrefix() + " message error", ERROR_KIND.EMPTY_LIST);

        }else{
            for(ASTNode child : n){
                child.accept(this);
            }
        }

        return null;
    }

    @Override
    public Object visit(ASTStation n) { return null; }

    @Override
    public Object visit(ASTDeclaration n) { return null; }

    @Override
    public Object visit(ASTChaine n) { return null; }

    @Override
    public Object visit(ASTIdentificateur n) { return null; }

    @Override
    public Object visit(ASTNombre n){ return null; }
}

enum ERROR_KIND {
    EMPTY_LIST,
    EMPTY_STATION_NAME,
    DUPLICATE_STATION_NAME,
    MISSING_DECLARATION,
    DUPLICATE_DECLARATION,
    WRONG_NUMBER_VALUE
}