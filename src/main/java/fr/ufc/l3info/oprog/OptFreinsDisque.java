package fr.ufc.l3info.oprog;

public class OptFreinsDisque extends Option{
    public OptFreinsDisque(IVelo v){
        super(v,0.3);
    }

    public String toString() {
        String split[] = _originale.toString().split("-", 0);
        String cadre=split[0];
        String km=split[1];

        return cadre+","+ " freins à disque"+" - "+ km;
    }
}
