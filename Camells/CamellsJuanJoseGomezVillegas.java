//package Uf4.CamellsJuanJoseGomezVillegas;

import acm.graphics.*;
import acm.program.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CamellsJuanJoseGomezVillegas extends GraphicsProgram {
    private int daltImg;
    int daltLinies;
    int baixLinies;
    private int numero;

    public static void main(String[] args) {
        new CamellsJuanJoseGomezVillegas().start(args);
    }

    public void run() {
        GDimension dimension = new GDimension(900, 1000);

        ArrayList<GImage> ArrayCamellsImatge = new ArrayList<>();

        getArrayCamellsImatge(ArrayCamellsImatge);

        GLine LiniaMeta = new GLine(900, 900, 900, 0);
        add(LiniaMeta);

        daltLinies = 50;
        baixLinies = 50;
        for (GImage camellActual : ArrayCamellsImatge) {
            add(camellActual);
            setLinies(0, 900, daltLinies, baixLinies);
            daltLinies += 50;
            baixLinies += 50;
        }

        for (GImage camellActual : ArrayCamellsImatge) {
            while (camellActual.getY() <= 900) {
                if (camellActual.getY() == 900) {
                    break;
                } else {
                    daltImg = 0;
                    for (GImage camellActual2 : ArrayCamellsImatge) {
                        numero = getNumeroAleatori();

                        camellActual2.setLocation(numero, daltImg);

                        daltImg += 50;
                    }
                }
            }
        }
    }

    public void setLinies(int esquerra, int dreta, int dalt, int baix) {
        GLine linia1 = new GLine(esquerra, dalt, dreta, baix);
        add(linia1);
    }

    public ArrayList<GImage> getArrayCamellsImatge(ArrayList<GImage> camells) {
        ArrayList<String> imatges = new ArrayList<>();

        imatges.add("src/UF4/CamellsJuanJoseGomezVillegas/Vermell.png");
        imatges.add("src/UF4/CamellsJuanJoseGomezVillegas/Blau.png");
        imatges.add("src/UF4/CamellsJuanJoseGomezVillegas/Marro.png");
        imatges.add("src/UF4/CamellsJuanJoseGomezVillegas/Verd.png");
        imatges.add("src/UF4/CamellsJuanJoseGomezVillegas/Lila.png");
        imatges.add("src/UF4/CamellsJuanJoseGomezVillegas/Groc.png");

        daltImg = 0;
        for (String imatgeActual : imatges) {
            camells.add(getCamells(imatgeActual, 50, 50, 0, daltImg));
            daltImg += 50;
        }

        return camells;
    }

    public GImage getCamells(String imatge, int amplada, int alcada, int costat, int dalt) {
        GImage camell1 = new GImage(imatge);
        camell1.setSize(amplada, alcada);
        camell1.setLocation(costat, dalt);

        return camell1;
    }

    public int getNumeroAleatori() {
        int numero = ThreadLocalRandom.current().nextInt(1, 15);

        return numero;
    }
}