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
    private int numero;
    private int posicioEstrella;
    private GImage camellGuanyador;
    private int Guanyador;

    public static void main(String[] args) {
        new HerenciaCamells().start(args);
    }

    public void init() {
        setSize(975, 750);
    }

    public void run() {
        ArrayList<String> rutaImatgesCamells = new ArrayList<>();
        rutaImatgesCamells.add(ruta+"/Vermell.png");
        rutaImatgesCamells.add(ruta+"/Vermell.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Lila.png");

        ArrayList<Integer> tipusCamells = new ArrayList<>();
        tipusCamells.add(1);
        tipusCamells.add(1);
        tipusCamells.add(2);
        tipusCamells.add(2);
        tipusCamells.add(3);
        tipusCamells.add(3);
        tipusCamells.add(3);
        tipusCamells.add(4);

        ArrayList<Camells> camells = new ArrayList<>();

        for (int i = 0, j = 0; i < rutaImatgesCamells.size() && j < tipusCamells.size(); i++, j++) {
            camells.add(new Camells(rutaImatgesCamells.get(i), tipusCamells.get(j)));
        }

        ArrayList<GImage> ArrayCamellsImatge = new ArrayList<>();

        for (Camells actual : camells) {
            ArrayCamellsImatge.add(actual.getImatgeCamell());
        }

        setPreparaPista(ArrayCamellsImatge);

        setCarrera2(ArrayCamellsImatge, camells);
    }

    public void setCarrera2(ArrayList<GImage> ArrayCamellsImatge, ArrayList<Camells> camells) {
        for (int x = 1; x <= 15; x++) {
            for (int i = 0; i < ArrayCamellsImatge.size() && i < camells.size(); i++) {
                if (camells.get(i).getTipusCamell() == 1) {
                    numero = getNumeroAleatori(1, 50);
                    ArrayCamellsImatge.get(i).move((numero * 2), 0);
                    ArrayCamellsImatge.get(i).pause(5);
                } else if (camells.get(i).getTipusCamell() == 2) {
                    numero = getNumeroAleatori(5, 10);
                    ArrayCamellsImatge.get(i).move(numero, 0);
                    ArrayCamellsImatge.get(i).pause(5);
                } else if (camells.get(i).getTipusCamell() == 3) {
                    numero = getNumeroAleatori(1, 100);
                    if ((numero % 2) == 0) {
                        ArrayCamellsImatge.get(i).move(numero, 0);
                    } else {
                        ArrayCamellsImatge.get(i).move(2, 0);
                    }
                    ArrayCamellsImatge.get(i).pause(5);
                } else if (camells.get(i).getTipusCamell() == 4) {
                    ArrayCamellsImatge.get(i).move(1, 0);
                    ArrayCamellsImatge.get(i).pause(5);
                    ArrayCamellsImatge.get(i).move(-1, 0);
                    ArrayCamellsImatge.get(i).pause(5);
                }
            }
        }

        for (int i = 1; i < ArrayCamellsImatge.size(); i++) {
            if (i == 1) {
                if (ArrayCamellsImatge.get(i).getX() > ArrayCamellsImatge.get(i-1).getX()) {
                    camellGuanyador = ArrayCamellsImatge.get(i);
                } else {
                    camellGuanyador = ArrayCamellsImatge.get(i-1);
                }
            }
            if (i > 1 && i < ArrayCamellsImatge.size()-1) {
                if (camellGuanyador.getX() > ArrayCamellsImatge.get(i).getX()) {
                    camellGuanyador = camellGuanyador;
                } else {
                    camellGuanyador = ArrayCamellsImatge.get(i);
                }
            }
            if (i == ArrayCamellsImatge.size()-1) {
                if (camellGuanyador.getX() > ArrayCamellsImatge.get(i).getX()) {
                    posicioEstrella = (int) camellGuanyador.getY();
                } else {
                    posicioEstrella = (int) ArrayCamellsImatge.get(i).getY();
                }
            }
        }

        for (int i = 0; i < ArrayCamellsImatge.size(); i++) {
            if (ArrayCamellsImatge.get(i).getY() == posicioEstrella) {
                Guanyador = i;
                break;
            }
        }

        setMostraEstrella(posicioEstrella);

        JOptionPane.showMessageDialog(null, "GUANYADOR: EL CAMELL "+(Guanyador+1));
    }
}