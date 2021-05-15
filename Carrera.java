package Camells;
/**
 * @author Juan José Gómez Villegas
 * @author Kirill Lupenkov
 * @author Jorge Luís Martínez Bermudez
 * @author Edgar Peréz
 * @author Jordi Risco
 * **/

/**
 * We import the following classes:
 * @see acm.graphics
 * @see acm.program
 * @see javax.swing
 * @see java.awt
 * @see java.util.ArrayList
 * @see java.util.concurrent.ThreadLocalRandom
 * **/
import acm.graphics.*;
import acm.program.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Create class "Carrera", inherited from the class "GraphicsProgram"
 * @see GraphicsProgram
 * @version 1
 * **/
public class Carrera extends GraphicsProgram {
    /**Create Variables protected**/
    protected String ruta = "src/Camells";
    protected GLine LiniaMeta;
    /**Create Variables private**/
    private int daltImg;
    private int posicioEstrella;
    private GImage camellGuanyador;
    private int Guanyador;

    /**
     * Create method setter and static main
     * @param args array the Strings
     * **/
    public static void main(String[] args) {new Carrera().start(args);}

    /**
     * Create method setter init
     * **/
    public void init() {
        /*
         * setSize set the size window in 975 the width and 750 the height
         * setBackground set the color window in DARK_GRAY
         * */
        setSize(975, 750);
        setBackground(Color.DARK_GRAY);
    }

    /**
     * Create method setter run
     * **/
    public void run() {
        /*Create ArrayList "rutaImatgesCamells", where we will put the images of the camels*/
        ArrayList<String> rutaImatgesCamells = new ArrayList<>();
        rutaImatgesCamells.add(ruta+"/Vermell.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Verd.png");
        rutaImatgesCamells.add(ruta+"/Lila.png");
        rutaImatgesCamells.add(ruta+"/Groc.png");

        /*Create ArrayList "camells"*/
        ArrayList<Camells> camells = new ArrayList<>();

        /*Add all the camels to the window*/
        for (String actual : rutaImatgesCamells) {
            camells.add(new Camells(actual, 0));
        }

        /*Create ArrayList "ArrayCamellsImatge"*/
        ArrayList<GImage> ArrayCamellsImatge = new ArrayList<>();

        /*converts each camel in image, using method getter "getImatgeCamell" the class "Camells"*/
        for (Camells actual : camells) {
            ArrayCamellsImatge.add(actual.getImatgeCamell());
        }

        /*Run the method setter "setPreparaPista"*/
        setPreparaPista(ArrayCamellsImatge);

        int posicioJugador = 0;
        /*The loop will work while, the value of the variable "posicioJugador" be different to the position X the "LiniaMeta" less 50*/
        while (posicioJugador != (LiniaMeta.getX()-50)) {
            /*Run the method getter "getCarrera"*/
            posicioJugador = getCarrera(ArrayCamellsImatge, LiniaMeta);
            /*and if "posicioJugador" is greater than or equal to the position X the "LiniaMeta" less 50
            * break the loop*/
            if (posicioJugador >= (LiniaMeta.getX()-50)) {
                break;
            }
        }

        /*Run the method setter "setComprovarGuanyador"*/
        setComprovarGuanyador(ArrayCamellsImatge);
    }

    /**
     * Create method getter "getCarrera"
     * @param ArrayCamellsImatge ArrayList the image of the camel
     * @param LiniaMeta The line of meta of the race
     * @return posicioJugador
     * **/
    private int getCarrera(ArrayList<GImage> ArrayCamellsImatge, GLine LiniaMeta) {
        int numero;
        int posicioJugador = 0;
        /*Traverse the ArrayList "ArrayCamellsImatge"*/
        for (GImage actual : ArrayCamellsImatge) {
            /*If the camel is a position X greater than or equal to position X the "LiniaMeta" less 50*/
            if (actual.getX() >= (LiniaMeta.getX()-50)) {
                /*watch the position X and Y the camel in the variables "posicioJugador" and "posicioEstrella"
                * and break the loop*/
                posicioJugador = (int) actual.getX();
                posicioEstrella = (int) actual.getY();
                break;
            } else {/*If not, check if position X the camel is different a number random between 100 and 300*/
                if (actual.getX() != getNumeroAleatori(100, 300)) {
                    numero = getNumeroAleatori(1, 15);/*Generate number random between 1 and 15*/
                    /*And if the number generate is different a number random between 5 and 10*/
                    if (numero != getNumeroAleatori(5, 10)) {
                        /*Move camel to the number random generated previously, and pause time in 5 seconds*/
                        actual.move(numero, 0);
                        actual.pause(5);
                    }
                }
            }
        }
        return posicioJugador;
    }

    /**
     * Create method setter "setComprovarGuanyador"
     * @param ArrayCamellsImatge ArrayList the image of the camel
     * **/
    public void setComprovarGuanyador(ArrayList<GImage> ArrayCamellsImatge) {
        /*Traverse the ArrayList "ArrayCamellsImatge"*/
        for (int i = 1; i < ArrayCamellsImatge.size(); i++) {
            /*If the variable "i" is equals a 1, does the following*/
            if (i == 1) {
                /*The winner of the race is camel of the position "i" or "0"*/
                if (ArrayCamellsImatge.get(i).getX() > ArrayCamellsImatge.get(0).getX()) {
                    camellGuanyador = ArrayCamellsImatge.get(i);
                } else {
                    camellGuanyador = ArrayCamellsImatge.get(0);
                }
            }
            /*If the variable "i" is between 1 and to the length of the ArrayList "ArrayCamellsImatge" less 1, does the following*/
            if (i > 1 && i < ArrayCamellsImatge.size()-1) {
                /*The winner of the race is camel of the position "i" or variable "camellGuanyador"*/
                if (camellGuanyador.getX() <= ArrayCamellsImatge.get(i).getX()) {
                    camellGuanyador = ArrayCamellsImatge.get(i);
                }
            }
            /*If the variable "i" is equals to the length of the ArrayList "ArrayCamellsImatge" less 1, does the following*/
            if (i == ArrayCamellsImatge.size()-1) {
                /*The winner of the race is camel of the position "i" or variable "camellGuanyador"*/
                if (camellGuanyador.getX() > ArrayCamellsImatge.get(i).getX()) {
                    posicioEstrella = (int) camellGuanyador.getY();
                } else {
                    posicioEstrella = (int) ArrayCamellsImatge.get(i).getY();
                }
            }
        }

        /*Traverse the ArrayList "ArrayCamellsImatge"*/
        for (int i = 0; i < ArrayCamellsImatge.size(); i++) {
            /*If the position Y of the camel "i" is equals to the value of the variable "posicioEstrella"
            * winner of the race is "i", and break the loop*/
            if (ArrayCamellsImatge.get(i).getY() == posicioEstrella) {
                Guanyador = i;
                break;
            }
        }

        /*Run the method setter "setMostraEstrella"*/
        setMostraEstrella(posicioEstrella);

        /*And finally, shows the message "GUANYADOR: EL CAMELL" followed the value of the variable "Guanyador" more 1*/
        JOptionPane.showMessageDialog(null, "GUANYADOR: EL CAMELL "+(Guanyador+1));
    }

    /**
     * Create method setter "setPreparaPista"
     * @param ArrayCamellsImatge ArrayList the image of the camel
     * **/
    public void setPreparaPista(ArrayList<GImage> ArrayCamellsImatge) {
        /*Add the line of meta in the position 900 and will have color "LIGHT_GRAY"*/
        LiniaMeta = new GLine(900, 900, 900, 0);
        LiniaMeta.setColor(Color.LIGHT_GRAY);
        add(LiniaMeta);

        /*Create variables next:*/
        int comptador = 1;
        int daltLinies = 50;
        int baixLinies = 50;
        /*Traverse the ArrayList "ArrayCamellsImatge"*/
        for (GImage actual : ArrayCamellsImatge) {
            /*Add label will the value of the "comptador" for each position the ArrayList "ArrayCamellsImatge"*/
            GLabel num = new GLabel(String.valueOf(comptador));
            num.setLocation(3, daltLinies);
            num.setColor(Color.LIGHT_GRAY);
            num.setFont("Arial-30");
            add(num);

            /*Add the camels the ArrayList "ArrayCamellsImatge", in the position "0" of the race, and to assign the size "50x50"*/
            add(actual);
            actual.setSize(50, 50);
            actual.setLocation(0, daltImg);

            /*Add Lines between camel and camel*/
            setLinies(0, 900, daltLinies, baixLinies);

            /*Increases the variables next:*/
            daltImg += 50;
            daltLinies += 50;
            baixLinies += 50;
            comptador++;
        }
    }

    /**
     * Create method setter "setLinies"
     * @param esquerra left position of the line
     * @param dreta right position of the line
     * @param dalt above position of the line
     * @param baix low position of the line
     * **/
    private void setLinies(int esquerra, int dreta, int dalt, int baix) {
        GLine linia1 = new GLine(esquerra, dalt, dreta, baix);
        linia1.setColor(Color.LIGHT_GRAY);
        add(linia1);
    }

    /**
     * Create method getter "getNumeroAleatori"
     * @param min minimum value
     * @param max maximum value
     * @return random number between param "min" and param "max"
     * **/
    public int getNumeroAleatori(int min, int max) {return ThreadLocalRandom.current().nextInt(min, max);}

    /**
     * Create method setter "setMostraEstrella"
     * @param posicioEstrella position where it is located the star
     * **/
    public void setMostraEstrella(int posicioEstrella) {
        Estrella estrella = new Estrella(ruta+"/star.png");
        estrella.setTamanyLocaltizacioEstrella(posicioEstrella);
        GImage imgEstrella = estrella.getRetornaEstrella();
        add(imgEstrella);
    }
}