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

public class CarreraCamells extends GraphicsProgram {
    protected String ruta = "src/UF4/Camells";
    private GLine LiniaMeta;
    private int daltImg;
    private int daltLinies;
    private int baixLinies;
    private int numero;
    private int posicioEstrella;
    private GImage camellGuanyador;
    private int Guanyador;

    public static void main(String[] args) {
        new CarreraCamells().start(args);
    }

    public void init() {
        setSize(975, 750);
    }

    public void run() {
        ArrayList<String> rutaImatgesCamells = new ArrayList<>();

        rutaImatgesCamells.add(ruta+"/Vermell.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Verd.png");
        rutaImatgesCamells.add(ruta+"/Lila.png");
        rutaImatgesCamells.add(ruta+"/Groc.png");

        ArrayList<Camells> camells = new ArrayList<>();

        for (String actual : rutaImatgesCamells) {
            camells.add(new Camells(actual, 0));
        }

        ArrayList<GImage> ArrayCamellsImatge = new ArrayList<>();

        for (Camells actual : camells) {
            ArrayCamellsImatge.add(actual.getImatgeCamell());
        }

        setPreparaPista(ArrayCamellsImatge);

        setCarrera(ArrayCamellsImatge, LiniaMeta);
    }

    public void setCarrera(ArrayList<GImage> ArrayCamellsImatge, GLine LiniaMeta) {
        for (int i = 1; i <= 100; i++) {
            for (GImage actual : ArrayCamellsImatge) {
                if (actual.getY() >= LiniaMeta.getX()) {
                    posicioEstrella = (int) actual.getY();
                    break;
                } else {
                    if (actual.getX() != getNumeroAleatori(200, 300)) {
                        numero = getNumeroAleatori(1, 15);

                        if (numero != getNumeroAleatori(5, 10)) {
                            actual.move(numero, 0);

                            actual.pause(5);
                        }
                    }
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

    public void setLinies(int esquerra, int dreta, int dalt, int baix) {
        GLine linia1 = new GLine(esquerra, dalt, dreta, baix);
        add(linia1);
    }

    public int getNumeroAleatori(int min, int max) {
        int numero = ThreadLocalRandom.current().nextInt(min, max);

        return numero;
    }

    public void setMostraEstrella(int posicioEstrella) {
        Estrella estrella = new Estrella(ruta+"/star.png");
        estrella.setTamanyLocaltizacioEstrella(posicioEstrella);
        GImage imgEstrella = estrella.getRetornaEstrella();
        add(imgEstrella);
    }
}

class Camells extends GraphicsProgram {
    private String rutaImatge;
    private int tipusCamell;

    public Camells(String rutaImatge, int tipus) {
        this.rutaImatge = rutaImatge;

        this.tipusCamell = tipus;
    }

    public int getTipusCamell() {
        return tipusCamell;
    }

    public GImage getImatgeCamell() {
        GImage camell = new GImage(rutaImatge);

        return camell;
    }
}

class Estrella extends GraphicsProgram {
    private GImage estrella;

    public Estrella(String ruta) {
        estrella = new GImage(ruta);
    }

    public void setTamanyLocaltizacioEstrella(int posicioEstrella) {
        estrella.setSize(50, 50);
        estrella.setLocation(900, posicioEstrella);
    }

    public GImage getRetornaEstrella() {
        return estrella;
    }
}