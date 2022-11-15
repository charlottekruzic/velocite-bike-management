package fr.ufc.l3info.oprog;

public class OptSuspensionArriere extends Option{
    public OptSuspensionArriere(IVelo v){
        super(v,0.5);
    }

    public String toString() {
        String split[] = _originale.toString().split(" - ", 0);
        String cadre=split[0];
        String km=split[1];

        return cadre+","+ " suspension arrière"+" - "+ km;
    }
}
