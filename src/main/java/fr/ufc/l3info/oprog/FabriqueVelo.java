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
        IVelo v = new Velo(cadre);
        boolean cadre_alu=false, suspension_av=false, suspension_ar=false, freins_disque=false, assistance_elec=false;

        for(String o : options){
            if(o=="CADRE_ALUMINIUM" && !cadre_alu){
                v=new OptCadreAlu(v);
                cadre_alu=true;
            }else if(o=="SUSPENSION_AVANT" && !suspension_av){
                v=new OptSuspensionAvant(v);
                suspension_av=true;
            }else if(o=="SUSPENSION_ARRIERE" && !suspension_ar){
                v=new OptSuspensionArriere(v);
                suspension_ar=true;
            }else if(o=="FREINS_DISQUE" && !freins_disque){
                v=new OptFreinsDisque(v);
                freins_disque=true;
            }else if(o=="ASSISTANCE_ELECTRIQUE" && !assistance_elec){
                v=new OptAssistanceElectrique(v);
                assistance_elec=true;
            }
        }
        return v;
    }
}