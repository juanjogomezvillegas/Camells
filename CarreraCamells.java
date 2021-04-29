package Uf4.Camells;

import acm.graphics.*;
import acm.program.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Juan José Gómez Villegas
 * @author Jorge Luis Martinez Bermudez
 * @author
 * @author
 * @author
 * **/

public class CarreraCamells extends GraphicsProgram {
    private String ruta = "src/UF4/Camells";
    private int daltLinies;
    private int baixLinies;
    private int numero;
    private int posicioEstrella;
    private GImage camellGuanyador;

    public static void main(String[] args) {
        new CarreraCamells().start(args);
    }

    public void init() {
        setSize(975, 750);
    }

    public void run() {
        Camells camells = new Camells();

        ArrayList<String> rutaImatgesCamells = new ArrayList<>();

        rutaImatgesCamells.add(ruta+"/Vermell.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Verd.png");
        rutaImatgesCamells.add(ruta+"/Lila.png");
        rutaImatgesCamells.add(ruta+"/Groc.png");

        ArrayList<GImage> ArrayCamellsImatge = new ArrayList<>();

        ArrayCamellsImatge = camells.getArrayImatgeCamell(rutaImatgesCamells, ArrayCamellsImatge);

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

        for (int i = 1; i <= 100; i++) {
            for (GImage camellActual : ArrayCamellsImatge) {
                if (camellActual.getY() >= LiniaMeta.getY()) {
                    posicioEstrella = (int) camellActual.getY();
                    break;
                } else {
                    numero = getNumeroAleatori();

                    camellActual.move(numero, 0);
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

        setMostraEstrella(posicioEstrella);
    }

    public void setLinies(int esquerra, int dreta, int dalt, int baix) {
        GLine linia1 = new GLine(esquerra, dalt, dreta, baix);
        add(linia1);
    }

    public int getNumeroAleatori() {
        int numero = ThreadLocalRandom.current().nextInt(1, 15);

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
    private int daltImg;

    public ArrayList<GImage> getArrayImatgeCamell(ArrayList<String> rutaImatgesCamells, ArrayList<GImage> ImatgesCamells) {
        for (String imatgeActual : rutaImatgesCamells) {
            ImatgesCamells.add(getImatgeCamell(imatgeActual));
        }

        daltImg = 0;
        for (GImage imatgeActual : ImatgesCamells) {
            setCamellPosicio(imatgeActual, 50, 50, 0, daltImg);

            daltImg += 50;
        }

        return ImatgesCamells;
    }

    public GImage getImatgeCamell(String rutaImatge) {
        GImage camell = new GImage(rutaImatge);

        return camell;
    }

    public void setCamellPosicio(GImage imatge, int amplada, int alcada, int costat, int dalt) {
        imatge.setSize(amplada, alcada);
        imatge.setLocation(costat, dalt);
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