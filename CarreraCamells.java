package Camells;
/**
 * @author Juan José Gómez Villegas
 * @author Jorge Luís Martínez Bermudez
 * @author Edgar Peréz
 * @author Jordi Risco
 * @author Kirill Lupenkov
 * **/

/**
 * Importem les següents classes:
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
 * Crea la Classe "CarreraCamells", heretada de la classe "GraphicsProgram"
 * @see GraphicsProgram
 * @version 1
 * **/
public class CarreraCamells extends GraphicsProgram {
    /**Crea les variables de tipus protected**/
    protected String ruta = "src/Camells";/*variable "ruta"*/
    protected GLine LiniaMeta;/*variable "LiniaMeta"*/
    /**Crea les variables de tipus private**/
    private int daltImg;/*variable "daltImg"*/
    private int posicioEstrella;/*variable "posicioEstrella"*/
    private GImage camellGuanyador;/*variable "camellGuanyador"*/
    private int Guanyador;/*variable "Guanyador"*/

    /**
     * Crea el metode main (Principal)
     * @param args
     */
    public static void main(String[] args) {new CarreraCamells().start(args);}

    /**
     * Crea el metode setter "init"
     * **/
    public void init() {
        /**
         * setSize: Estableix el tamany de la finestra en 975x750
         * setBackground: Estableix el color de fons de la finestra en el color especificat
         * **/
        setSize(975, 750);
        setBackground(Color.DARK_GRAY);
    }

    /**
     * Crea el metode setter "run"
     * **/
    public void run() {
        /*Creem l'ArrayList "rutaImatgesCamells", on posarem la ruta de les imatges dels camells*/
        ArrayList<String> rutaImatgesCamells = new ArrayList<>();
        rutaImatgesCamells.add(ruta+"/Vermell.png");
        rutaImatgesCamells.add(ruta+"/Blau.png");
        rutaImatgesCamells.add(ruta+"/Marro.png");
        rutaImatgesCamells.add(ruta+"/Verd.png");
        rutaImatgesCamells.add(ruta+"/Lila.png");
        rutaImatgesCamells.add(ruta+"/Groc.png");

        /*Crea el ArrayList "camells"*/
        ArrayList<Camells> camells = new ArrayList<>();

        /*Recorrer l'ArrayList "rutaImatgesCamells", i per cada valor de "rutaImatgesCamells" instancia el metode "Camells"*/
        for (String actual : rutaImatgesCamells) {
            camells.add(new Camells(actual, 0));
        }

        /*Crea el ArrayList "ArrayCamellsImatge"*/
        ArrayList<GImage> ArrayCamellsImatge = new ArrayList<>();

        /*Recorrer l'ArrayList "camells", i per cada valor de "camells" executa el metode getter "getImatgeCamell"*/
        for (Camells actual : camells) {
            ArrayCamellsImatge.add(actual.getImatgeCamell());
        }

        /*Executa el metode setter "setPreparaPista"*/
        setPreparaPista(ArrayCamellsImatge);

        /*El bucle while funcionara mentre la variable "posicioJugador" sigui diferent a la posicio X de "LiniaMeta" menys 100*/
        int posicioJugador = 0;
        while (posicioJugador != (LiniaMeta.getX()-50)) {
            /*Executa el metode getter "getCarrera"*/
            posicioJugador = getCarrera(ArrayCamellsImatge, LiniaMeta);
            /*I si la variable "posicioJugador" es major o igual a la posicio X de "LiniaMeta" menys 100, trenca el bucle while*/
            if (posicioJugador >= (LiniaMeta.getX()-50)) {
                break;
            }
        }

        /*Executa el metode setter "setComprovarGuanyador"*/
        setComprovarGuanyador(ArrayCamellsImatge);
    }

    /**
     * Crea el metode getter "getCarrera"
     * @param ArrayCamellsImatge
     * @param LiniaMeta
     * @return posicioJugador
     * **/
    private int getCarrera(ArrayList<GImage> ArrayCamellsImatge, GLine LiniaMeta) {
        /*Crea les variables "numero" i "posicioJugador"*/
        int numero;
        int posicioJugador = 0;
        /*I recorrem l'ArrayList "ArrayCamellsImatge"*/
        for (GImage actual : ArrayCamellsImatge) {
            /*Si el camell "actual" esta a una posicio X major o igual que la posicio X de "LiniaMeta" menys 100*/
            if (actual.getX() >= (LiniaMeta.getX()-50)) {
                /*Guarda la posicio del camell en les variables "posicioJugador" i "posicioEstrella", i trenca el bucle for*/
                posicioJugador = (int) actual.getX();
                posicioEstrella = (int) actual.getY();
                break;
            } else {/*Si no, comprovara si el camell no està en una posicio aletoria entre 100 i 300*/
                if (actual.getX() != getNumeroAleatori(100, 300)) {
                    /*Si es compleix executa el metode getter "getNumeroAleatori"*/
                    numero = getNumeroAleatori(1, 15);
                    /*I si el numero generat aleatoriament es diferent a un numero aleatori entre 5 i 10*/
                    if (numero != getNumeroAleatori(5, 10)) {
                        /*Mou al camell el numero que hagui sortit i pausa 5 segons*/
                        actual.move(numero, 0);
                        actual.pause(5);
                    }
                }
            }
        }
        /*Retorna el valor de "posicioJugador"*/
        return posicioJugador;
    }

    /**
     * Crea el metode setter "setComprovarGuanyador"
     * @param ArrayCamellsImatge
     * **/
    public void setComprovarGuanyador(ArrayList<GImage> ArrayCamellsImatge) {
        /*Recorrer l'ArrayList "ArrayCamellsImatge"*/
        for (int i = 1; i < ArrayCamellsImatge.size(); i++) {
            /*Si la "i" es igual a 1*/
            if (i == 1) {
                /*Si la posicio X del valor de "i" es major a la posicio X del valor "i-1"*/
                if (ArrayCamellsImatge.get(i).getX() > ArrayCamellsImatge.get(0).getX()) {
                    /*"camellGuanyador" sera igual al valor de "i"*/
                    camellGuanyador = ArrayCamellsImatge.get(i);
                } else {/*Si no, "camellGuanyador" sera igual al valor de "i-1"*/
                    camellGuanyador = ArrayCamellsImatge.get(0);
                }
            }
            /*Si la "i" esta entre 1 i la longitud del ArrayList "ArrayCamellsImatge" menys 1*/
            if (i > 1 && i < ArrayCamellsImatge.size()-1) {
                /*Si la posicio X del valor de "camellGuanyador" es major a la posicio X del valor de "i"*/
                if (camellGuanyador.getX() <= ArrayCamellsImatge.get(i).getX()) {
                    camellGuanyador = ArrayCamellsImatge.get(i);
                }
            }
            /*Si la "i" es igual a la longitud del ArrayList "ArrayCamellsImatge" menys 1*/
            if (i == ArrayCamellsImatge.size()-1) {
                /*Si la posicio X del valor de "camellGuanyador" es major a la posicio X del valor de "i"*/
                if (camellGuanyador.getX() > ArrayCamellsImatge.get(i).getX()) {
                    /*"posicioEstrella" sera igual a la posicio Y de "camellGuanyador"*/
                    posicioEstrella = (int) camellGuanyador.getY();
                } else {
                    /*Si no, "posicioEstrella" sera igual a la posicio Y del valor de "i"*/
                    posicioEstrella = (int) ArrayCamellsImatge.get(i).getY();
                }
            }
        }

        /*I recorrem l'ArrayList "ArrayCamellsImatge"*/
        for (int i = 0; i < ArrayCamellsImatge.size(); i++) {
            /*Quan trobi el camell que tingui la posicio Y igual al valor de "posicioEstrella", "Guanyador" sera igual a "i" i trenca el bucle*/
            if (ArrayCamellsImatge.get(i).getY() == posicioEstrella) {
                Guanyador = i;
                break;
            }
        }

        /*Executa el metode setter "setMostraEstrella"*/
        setMostraEstrella(posicioEstrella);

        /*Mostra un missatge informant de quin camell a guanyat*/
        JOptionPane.showMessageDialog(null, "GUANYADOR: EL CAMELL "+(Guanyador+1));
    }

    /**
     * Crea el metode setter "setPreparaPista"
     * @param ArrayCamellsImatge
     * **/
    public void setPreparaPista(ArrayList<GImage> ArrayCamellsImatge) {
        /*Crea la LiniaMeta que estara a la posicio 900 i tindra el color "LIGHT_GRAY"*/
        LiniaMeta = new GLine(900, 900, 900, 0);
        LiniaMeta.setColor(Color.LIGHT_GRAY);
        add(LiniaMeta);

        /*Crea les variables "comptador", "daltLinies" i "baixLinies"*/
        int comptador = 1;
        int daltLinies = 50;
        int baixLinies = 50;
        /*I Recorrem l'ArrayList "ArrayCamellsImatge"*/
        for (GImage actual : ArrayCamellsImatge) {
            /*Creara una etiqueta amb el valor de "comptador" per cada posicio que recorri el bucle foreach*/
            GLabel num = new GLabel(String.valueOf(comptador));
            num.setLocation(3, daltLinies);
            /*Les linies tindran el color "LIGHT_GRAY" i tipus "Arial-30"*/
            num.setColor(Color.LIGHT_GRAY);
            num.setFont("Arial-30");
            add(num);

            /*Afegira els camells a la posicio inicial "0", i li assignara la mida "50x50"*/
            add(actual);
            actual.setSize(50, 50);
            actual.setLocation(0, daltImg);

            /*Separa als camells per Linies, per crear les linies executara el metode setter "setLinies"*/
            setLinies(0, 900, daltLinies, baixLinies);

            /*Incrementa les variables següents:*/
            daltImg += 50;
            daltLinies += 50;
            baixLinies += 50;
            comptador++;
        }
    }

    /**
     * Crea el metode setter "setLinies", que creara una linia en la posicio especificada en els parametres
     * @param esquerra
     * @param dreta
     * @param dalt
     * @param baix
     * **/
    private void setLinies(int esquerra, int dreta, int dalt, int baix) {
        GLine linia1 = new GLine(esquerra, dalt, dreta, baix);
        linia1.setColor(Color.LIGHT_GRAY);
        add(linia1);
    }

    /**
     * Crea el metode getter "getNumeroAleatori", que retornara un numero aleatori entre el "min" i el "max" (parametres)
     * @param min
     * @param max
     * @return ThreadLocalRandom.current().nextInt(min, max)
     * **/
    public int getNumeroAleatori(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /**
     * Crea el metode setter "setMostraEstrella", que creara una estrella que sera a la posicio especificada en el parametre
     * @param posicioEstrella
     * **/
    public void setMostraEstrella(int posicioEstrella) {
        Estrella estrella = new Estrella(ruta+"/star.png");
        estrella.setTamanyLocaltizacioEstrella(posicioEstrella);
        GImage imgEstrella = estrella.getRetornaEstrella();
        add(imgEstrella);
    }
}

/**
 * Crea la Classe "Camells", heretada de la classe "GraphicsProgram"
 * @see GraphicsProgram
 * @version 1
 * **/
class Camells extends GraphicsProgram {
    /**Crea les variables següents de tipus private final**/
    private final String rutaImatge;
    private final int tipusCamell;

    /**
     * Crea el metode constructor "Camells"
     * @param rutaImatge
     * @param tipusCamell
     * **/
    public Camells(String rutaImatge, int tipusCamell) {
        this.rutaImatge = rutaImatge;

        this.tipusCamell = tipusCamell;
    }

    /**
     * Crea el metode getter "getTipusCamell", que retornara el valor de "tipusCamell"
     * @return tipusCamell
     * **/
    public int getTipusCamell() {return tipusCamell;}

    /**
     * Crea el metode getter "getImatgeCamell"
     * @return GImage(rutaImatge)
     * **/
    public GImage getImatgeCamell() {
        return new GImage(rutaImatge);
    }
}

/**
 * Crea la Classe "Estrella" que tingui amb herencia la classe "GraphicsProgram"
 * @see GraphicsProgram
 * @version 1
 * **/
class Estrella extends GraphicsProgram {
    /**Crea les variables següents de tipus private final**/
    private final GImage estrella;

    /**
     * Crea el metode onstructor "Estrella"
     * @param ruta
     * **/
    public Estrella(String ruta) {estrella = new GImage(ruta);}

    /**
     * Crea el metode setter "setTamanyLocaltizacioEstrella"
     * @param posicioEstrella
     * **/
    public void setTamanyLocaltizacioEstrella(int posicioEstrella) {
        /**
         * estrella.setSize: Estableix a la imatge "estrella" el tamany "50x50"
         * estrella.setLocation: Afegeix la estrella a la posicio "900" i al valor del parametre "posicioEstrella"
         * */
        estrella.setSize(50, 50);
        estrella.setLocation(900, posicioEstrella);
    }

    /**
     * Crea el metode getter "getRetornaEstrella"
     * @return estrella
     * **/
    public GImage getRetornaEstrella() {return estrella;}
}