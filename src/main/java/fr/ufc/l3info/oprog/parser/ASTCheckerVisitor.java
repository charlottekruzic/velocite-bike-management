package fr.ufc.l3info.oprog.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Visiteur réalisant des vérifications sur l'AST du fichier de stations.
 */
public class ASTCheckerVisitor implements ASTNodeVisitor {
    Map<String, ERROR_KIND> errors ;
    List<String> nomsStations = new ArrayList<String>();
    List<String> identificateursStation = new ArrayList<String>();
    String identificateur_de_la_valeur;
    public ASTCheckerVisitor() {
        errors = new HashMap<>();
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
    public Object visit(ASTStation n) {
        int nb_declaration=0;
        identificateursStation.clear();

        if(n.getNumChildren()==0){
            this.errors.put(n.getLCPrefix()+"message erreur",ERROR_KIND.EMPTY_LIST);
        }else {
            for (ASTNode child : n) {
                child.accept(this);
                if (child instanceof ASTDeclaration) {
                    nb_declaration++;
                }
            }
            if(nb_declaration<3){
                this.errors.put(n.getLCPrefix()+"message erreur",ERROR_KIND.MISSING_DECLARATION);
            }
        }
        return null;
    }

    @Override
    public Object visit(ASTDeclaration n) {
        if(n.getNumChildren()==0){
            this.errors.put(n.getLCPrefix()+"message erreur",ERROR_KIND.EMPTY_LIST);
        }else if(n.getNumChildren()!=2){
            this.errors.put(n.getLCPrefix()+"message erreur",ERROR_KIND.WRONG_NUMBER_VALUE);
        }else{
            for (ASTNode child : n) {
                child.accept(this);
            }
        }
        return null;
    }

    @Override
    public Object visit(ASTChaine n) {
        System.out.println("chaine : "+n.value); //nom
        String nom_sans_espace= n.value;
        nom_sans_espace=nom_sans_espace.replaceAll(" ","");
        nom_sans_espace=nom_sans_espace.trim();
        System.out.println("name : "+nom_sans_espace); //nom
        if(nom_sans_espace.length()==2 || nomsStations.contains(n.value.trim())) {
            if (nom_sans_espace.length() == 2) {
                this.errors.put(n.getLCPrefix() + "message erreur", ERROR_KIND.EMPTY_STATION_NAME);
            }
            if (nomsStations.contains(n.value)) {
                this.errors.put(n.getLCPrefix() + "message erreur", ERROR_KIND.DUPLICATE_STATION_NAME);
            }
        }else{
            nomsStations.add(n.value);
            System.out.println("noms : "+nomsStations);
        }

        return null;
    }

    @Override
    public Object visit(ASTIdentificateur n) {
        identificateur_de_la_valeur=n.value;
        String id= n.value;
        id=id.replaceAll("\\s+","");
        id=id.trim();
        if(identificateursStation.contains(id)) {
            this.errors.put(n.getLCPrefix() + "message erreur", ERROR_KIND.DUPLICATE_DECLARATION);
        }else{
            identificateursStation.add(id);
        }

        return null;
    }

    @Override
    public Object visit(ASTNombre n){
        if(identificateur_de_la_valeur.equals("capacite")){
            if(n.value.contains(".")==true){
                this.errors.put(n.getLCPrefix() + "message erreur", ERROR_KIND.WRONG_NUMBER_VALUE);
            }else if(Integer.parseInt(n.value) <=0){
                this.errors.put(n.getLCPrefix() + "message erreur", ERROR_KIND.WRONG_NUMBER_VALUE);
            }
        }
        return null;
    }
}

enum ERROR_KIND {
    EMPTY_LIST,
    EMPTY_STATION_NAME,
    DUPLICATE_STATION_NAME,
    MISSING_DECLARATION,
    DUPLICATE_DECLARATION,
    WRONG_NUMBER_VALUE
}