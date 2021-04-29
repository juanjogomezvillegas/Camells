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
    private int posicioEstrella;
    private int numero;

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

        for (Camells actual : camells) {
            ArrayCamellsImatge.add(actual.getImatgeCamell());
        }

        setPreparaPista(ArrayCamellsImatge);
    }
}