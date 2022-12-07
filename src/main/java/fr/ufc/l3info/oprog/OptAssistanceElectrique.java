package fr.ufc.l3info.oprog;

public class OptAssistanceElectrique extends Option{
    protected OptAssistanceElectrique(IVelo v){
        super(v,2);
    }

    public String toString() {
        String[] split = _originale.toString().split(" - ", 0);
        String cadre=split[0];
        String km=split[1];

        return cadre+","+ " assistance électrique"+" - "+ km;
    }
}
