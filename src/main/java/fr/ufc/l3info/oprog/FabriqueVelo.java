package fr.ufc.l3info.oprog;


public class FabriqueVelo{
    private static FabriqueVelo instance = null;

    private FabriqueVelo() {}

    public static synchronized FabriqueVelo getInstance() {
        if (instance == null) {
            instance = new FabriqueVelo();
        }
        return instance;
    }

    public IVelo construire(char cadre,String... options){
        IVelo velo = new Velo(cadre);

        for(String option : options){
            switch (option){
                case "CADRE_ALUMINIUM":
                    velo= new OptCadreAlu(velo);
                    break;
                case "SUSPENSION_AVANT":
                    velo= new OptSuspensionAvant(velo);
                    break;
                case "SUSPENSION_ARRIERE":
                    velo= new OptSuspensionArriere(velo);
                    break;
                case "FREINS_DISQUE":
                    velo= new OptFreinsDisque(velo);
                    break;
                case "ASSISTANCE_ELECTRIQUE":
                    velo= new OptAssistanceElectrique(velo);
                    break;

            }




        }
        return velo;
    }




}