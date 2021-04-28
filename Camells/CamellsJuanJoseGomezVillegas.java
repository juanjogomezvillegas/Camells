
import acm.graphics.*;
import acm.program.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CamellsJuanJoseGomezVillegas extends GraphicsProgram {
    private String ruta = "src/UF4/CamellsJuanJoseGomezVillegas";
    private int daltLinies;
    private int baixLinies;
    private int numero;
    private int posicioEstrella;

    public static void main(String[] args) {
        new CamellsJuanJoseGomezVillegas().start(args);
    }

    public void init() {
        setSize(975, 750);
    }

    public void run() {
        Camell camells = new Camell();

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
                if (camellActual.getY() == LiniaMeta.getY()) {
                    posicioEstrella = (int) camellActual.getX();
                    break;
                } else {
                    numero = getNumeroAleatori();

                    camellActual.move(numero, 0);
                }
            }
        }

        GImage estrella = new GImage(ruta+"/star.png");
        estrella.setSize(50, 50);
        estrella.setLocation(900, posicioEstrella);
        add(estrella);
    }

    public void setLinies(int esquerra, int dreta, int dalt, int baix) {
        GLine linia1 = new GLine(esquerra, dalt, dreta, baix);
        add(linia1);
    }

    public int getNumeroAleatori() {
        int numero = ThreadLocalRandom.current().nextInt(1, 15);

        return numero;
    }
}

class Camell extends GraphicsProgram {
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