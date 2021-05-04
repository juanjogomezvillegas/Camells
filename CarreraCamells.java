package Uf4.Camells;

/*Importa les següents llibraries*/
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
 * @author Kirill Lupenkov
 * **/

/*Crea la Classe "CarreraCamells" que tingui amb herencia la classe "GraphicsProgram"*/
public class CarreraCamells extends GraphicsProgram {
    protected String ruta = "src/UF4/Camells";//Crea la variable "ruta"
    private GLine LiniaMeta;//Crea la variable "LiniaMeta"
    private int daltImg;//Crea la variable "daltImg"
    private int daltLinies;//Crea la variable "daltLinies"
    private int baixLinies;//Crea la variable "baixLinies"
    private int numero;//Crea la variable "numero"
    private int posicioEstrella;//Crea la variable "posicioEstrella"
    private GImage camellGuanyador;//Crea la variable "camellGuanyador"
    private int Guanyador;//Crea la variable "Guanyador"

    /*Crea el metode main (principal)*/
    public static void main(String[] args) {
        new CarreraCamells().start(args);
    }

    /*Crea el metode setter "init", que establira el tamany de la finestra en 975x750*/
    public void init() {
        setSize(975, 750);
    }

    /*Crea el metode setter "run", que executara la finestra*/
    public void run() {
        /*Creem el ArrayList "rutaImatgesCamells", on posarem la ruta de les imatges dels camells*/
        ArrayList<String> rutaImatgesCamells = new ArrayList<>();
        rutaImatgesCamells.add(ruta+"/Vermell.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Verd.png");
        rutaImatgesCamells.add(ruta+"/Lila.png");
        rutaImatgesCamells.add(ruta+"/Groc.png");

        /*Crea el ArrayList "camells"*/
        ArrayList<Camells> camells = new ArrayList<>();

        /*Instanciem el metode "Camells", per cada valor del ArrayList "rutaImatgesCamells"*/
        for (String actual : rutaImatgesCamells) {
            camells.add(new Camells(actual, 0));
        }

        /*Crea el ArrayList "ArrayCamellsImatge"*/
        ArrayList<GImage> ArrayCamellsImatge = new ArrayList<>();

        /*I per cada valor del ArrayList "camells", executa el metode getter "getImatgeCamell" (que retornara un GImage)*/
        for (Camells actual : camells) {
            ArrayCamellsImatge.add(actual.getImatgeCamell());
        }

        /*Executa el metode setter "setPreparaPista"*/
        setPreparaPista(ArrayCamellsImatge);

        /*Executa el metode setter "setCarrera"*/
        setCarrera(ArrayCamellsImatge, LiniaMeta);
    }

    /*Crea el metode setter "setCarrera"*/
    public void setCarrera(ArrayList<GImage> ArrayCamellsImatge, GLine LiniaMeta) {
        for (int i = 1; i <= 105; i++) {
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

    /*Crea el metode setter "setPreparaPista"*/
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

    /*Crea el metode setter "setLinies"*/
    public void setLinies(int esquerra, int dreta, int dalt, int baix) {
        GLine linia1 = new GLine(esquerra, dalt, dreta, baix);
        add(linia1);
    }

    /*Crea el metode getter "getNumeroAleatori"*/
    public int getNumeroAleatori(int min, int max) {
        int numero = ThreadLocalRandom.current().nextInt(min, max);

        return numero;
    }

    /*Crea el metode setter "setMostraEstrella"*/
    public void setMostraEstrella(int posicioEstrella) {
        Estrella estrella = new Estrella(ruta+"/star.png");
        estrella.setTamanyLocaltizacioEstrella(posicioEstrella);
        GImage imgEstrella = estrella.getRetornaEstrella();
        add(imgEstrella);
    }
}

/*Crea la Classe "Camells" que tingui amb herencia la classe "GraphicsProgram"*/
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

/*Crea la Classe "Estrella" que tingui amb herencia la classe "GraphicsProgram"*/
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