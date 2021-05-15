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
 * **/
import acm.graphics.*;
import acm.program.*;

/**
 * Create class "Estrella", inherited from the class "GraphicsProgram"
 * @see GraphicsProgram
 * @version 1
 * **/
class Estrella extends GraphicsProgram {
    /**Create Variables private and final**/
    private final GImage estrella;

    /**
     * Create method constructor the class "Estrella"
     * @param ruta path the image of the star
     * **/
    public Estrella(String ruta) {estrella = new GImage(ruta);}

    /**
     * Create method setter "setTamanyLocaltizacioEstrella"
     * @param posicioEstrella position where it is located the star
     * **/
    public void setTamanyLocaltizacioEstrella(int posicioEstrella) {
        /*
         * estrella.setSize Set the size of the image of the star in "50x50"
         * estrella.setLocation Set the star in the position "900" and the value the parameter "posicioEstrella"
         * */
        estrella.setSize(50, 50);
        estrella.setLocation(900, posicioEstrella);
    }

    /**
     * Create method getter "getRetornaEstrella"
     * @return image of the star
     * **/
    public GImage getRetornaEstrella() {return estrella;}
}