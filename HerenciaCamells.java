package Uf4.Camells;

import acm.graphics.*;
import acm.program.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Juan José Gómez Villegas
 * @author Jorge Luís Martínez Bermudez
 * @author Edgar Peréz
 * @author Jordi Risco
 * **/

public class HerenciaCamells extends CarreraCamells {
    private GLine LiniaMeta;
    private int daltImg;
    private int daltLinies;
    private int baixLinies;

    public static void main(String[] args) {
        new HerenciaCamells().start(args);
    }

    public void init() {
        setSize(975, 750);
    }

    public void run() {
        ArrayList<String> rutaImatgesCamells = new ArrayList<>();

        rutaImatgesCamells.add(ruta+"/Vermell.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Lila.png");
        rutaImatgesCamells.add(ruta+"/Groc.png");

        ArrayList<Camells> camells = new ArrayList<>();

        for (String actual : rutaImatgesCamells) {
            camells.add(new Camells(actual, getNumeroAleatori(1, 4)));
        }

        ArrayList<GImage> ArrayCamellsImatge = new ArrayList<>();

        ArrayList<GImage> imatgeCamellsRapids = new ArrayList<>();
        ArrayList<GImage> imatgeCamellsFondistes = new ArrayList<>();
        ArrayList<GImage> imatgeCamellsAntiSenars = new ArrayList<>();
        ArrayList<GImage> imatgeCamellsFlipats = new ArrayList<>();

        for (Camells actual : camells) {
            if (actual.getTipusCamell() == 1) {
                imatgeCamellsRapids.add(actual.getImatgeCamell());
            } else if (actual.getTipusCamell() == 2) {
                imatgeCamellsFondistes.add(actual.getImatgeCamell());
            } else if (actual.getTipusCamell() == 3) {
                imatgeCamellsAntiSenars.add(actual.getImatgeCamell());
            } else if (actual.getTipusCamell() == 4) {
                imatgeCamellsFlipats.add(actual.getImatgeCamell());
            }
        }
    }

    public void setPreparaPista(ArrayList<GImage> ArrayCamellsImatge) {
        LiniaMeta = new GLine(900, 900, 900, 0);
        add(LiniaMeta);

        int comptador = 1;
        daltLinies = 50;
        baixLinies = 50;
        for (GImage actual : ArrayCamellsImatge) {
            GLabel num = new GLabel(String.valueOf(comptador));
            num.setLocation(3, daltLinies);
            num.setFont("Arial-25");
            add(num);

            add(actual);
            actual.setSize(50, 50);
            actual.setLocation(0, daltImg);

            setLinies(0, 900, daltLinies, baixLinies);

            daltImg += 50;
            daltLinies += 50;
            baixLinies += 50;
            comptador++;
        }
    }
}