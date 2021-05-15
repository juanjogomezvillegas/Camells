package Camells;
/**
 * @author Juan José Gómez Villegas
 * @author Jorge Luís Martínez Bermudez
 * @author Kirill Lupenkov
 * @author Edgar Peréz
 * @author Jordi Risco
 * **/

/**
 * We import the following classes:
 * @see acm.graphics
 * @see acm.program
 * **/
import acm.graphics.*;
import acm.program.*;

/**
 * Create class "Camells", inherited from the class "GraphicsProgram"
 * @see GraphicsProgram
 * @version 1
 * **/
class Camells extends GraphicsProgram {
    /**Create Variables private and final**/
    private final String rutaImatge;
    private final int tipusCamell;

    /**
     * Create method constructor the class "Camells"
     * @param rutaImatge path the image of the camel
     * @param tipusCamell type the image of the camel
     * **/
    public Camells(String rutaImatge, int tipusCamell) {
        this.rutaImatge = rutaImatge;

        this.tipusCamell = tipusCamell;
    }

    /**
     * Create method getter "getTipusCamell"
     * @return type of the camel
     * **/
    public int getTipusCamell() {return tipusCamell;}

    /**
     * Create method getter "getImatgeCamell"
     * @return Image of the camel
     * **/
    public GImage getImatgeCamell() {return new GImage(rutaImatge);}
}