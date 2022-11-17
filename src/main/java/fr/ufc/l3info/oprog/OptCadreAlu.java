package fr.ufc.l3info.oprog;

public class OptCadreAlu extends Option{
    public OptCadreAlu(IVelo v){
        super(v,0.2);
    }

    public String toString() {
        String[] split = _originale.toString().split(" - ", 0);
        String cadre=split[0];
        String km=split[1];

        return cadre+","+ " cadre aluminium"+" - "+ km;


    }
}
