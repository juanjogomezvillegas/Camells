package Uf4.Camells;

/*Importa les següents llibraries*/
import acm.graphics.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Juan José Gómez Villegas
 * @author Jorge Luís Martínez Bermudez
 * @author Edgar Peréz
 * @author Jordi Risco
 * @author Kirill Lupenkov
 * **/

/*Crea la Classe "HerenciaCamells" que tingui amb herencia la classe "CarreraCamells"*/
public class HerenciaCamells extends CarreraCamells {
    private int numero;//Crea la variable "numero"
    private int posicioEstrella;//Crea la variable "posicioEstrella"
    private GImage camellGuanyador;//Crea la variable "camellGuanyador"
    private int Guanyador;//Crea la variable "Guanyador"

    /*Crea el metode main (principal)*/
    public static void main(String[] args) {new HerenciaCamells().start(args);}

    /*Crea el metode setter "init"*/
    public void init() {
        /*Estableix el tamany de la finestra en 975x750*/
        setSize(975, 750);
        /*Estableix el color de fons de la finestra en el color especificat*/
        setBackground(Color.DARK_GRAY);
    }

    /*Crea el metode setter "run", que executara la finestra*/
    public void run() {
        ArrayList<String> rutaImatgesCamells = new ArrayList<>();
        rutaImatgesCamells.add(ruta+"/Vermell.png");
        rutaImatgesCamells.add(ruta+"/Vermell.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Lila.png");
        rutaImatgesCamells.add(ruta+"/Lila.png");

        ArrayList<Integer> tipusCamells = new ArrayList<>();
        tipusCamells.add(1);
        tipusCamells.add(1);
        tipusCamells.add(2);
        tipusCamells.add(2);
        tipusCamells.add(2);
        tipusCamells.add(3);
        tipusCamells.add(3);
        tipusCamells.add(3);
        tipusCamells.add(4);
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

    /*Crea el metode setter "setCarrera2"*/
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
                    numero = getNumeroAleatori(1, 50);
                    ArrayCamellsImatge.get(i).move(numero, 0);
                    numero = getNumeroAleatori(-10, -1);
                    if (ArrayCamellsImatge.get(i).getX() > 10) {
                        ArrayCamellsImatge.get(i).move(numero, 0);
                    } else {
                        if (ArrayCamellsImatge.get(i).getX() > 0) {
                            ArrayCamellsImatge.get(i).move(-1, 0);
                        }
                    }
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