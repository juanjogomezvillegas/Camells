package Uf4.Camells;

/*Importa les següents llibraries*/
import acm.graphics.*;
import acm.program.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static void main(String[] args) {new CarreraCamells().start(args);}

    /*Crea el metode setter "init"*/
    public void init() {
        /*Estableix el tamany de la finestra en 975x750*/
        setSize(975, 750);
        /*Estableix el color de fons de la finestra en el color especificat*/
        setBackground(Color.DARK_GRAY);
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
        /*Repetira el següent bucle for 105 vegades*/
        for (int i = 1; i <= 105; i++) {
            /*I amb el següent bucle foreach recorrera l'ArrayList "ArrayCamellsImatge"*/
            for (GImage actual : ArrayCamellsImatge) {
                /*Si el camell esta o la superat a la posicio "Y" de la LiniaMeta, guarda la posicio del camell en la variable "posicioEstrella"*/
                if (actual.getY() >= LiniaMeta.getY()) {
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
        }

        /*Amb el següent bucle recorrer l'ArrayList "ArrayCamellsImatge"*/
        for (int i = 1; i < ArrayCamellsImatge.size(); i++) {
            /*Si la "i" es igual a 1*/
            if (i == 1) {
                /*Si la posicio X del valor de "i" es major a la posicio X del valor "i-1"*/
                if (ArrayCamellsImatge.get(i).getX() > ArrayCamellsImatge.get(i-1).getX()) {
                    /*"camellGuanyador" sera igual al valor de "i"*/
                    camellGuanyador = ArrayCamellsImatge.get(i);
                } else {/*Si no, "camellGuanyador" sera igual al valor de "i-1"*/
                    camellGuanyador = ArrayCamellsImatge.get(i-1);
                }
            }
            /*Si la "i" esta entre 1 i la longitud del ArrayList "ArrayCamellsImatge" menys 1*/
            if (i > 1 && i < ArrayCamellsImatge.size()-1) {
                /*Si la posicio X del valor de "camellGuanyador" es major a la posicio X del valor de "i"*/
                if (camellGuanyador.getX() > ArrayCamellsImatge.get(i).getX()) {
                    /*"camellGuanyador" sera igual a "camellGuanyador"*/
                    camellGuanyador = camellGuanyador;
                } else {/*Si no, "camellGuanyador" sera igual al valor de "i"*/
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

        /*Amb el següent bucle recorrer l'ArrayList "ArrayCamellsImatge"*/
        for (int i = 0; i < ArrayCamellsImatge.size(); i++) {
            /*Quan trobi el camell que tingui la posicio Y igual al valor de "posicioEstrella", "Guanyador" sera igual a "i" i trenca el bucle*/
            if (ArrayCamellsImatge.get(i).getY() == posicioEstrella) {
                Guanyador = i;
                break;
            }
        }

        /*Executa el metode setter "setMostraEstrella"*/
        setMostraEstrella(posicioEstrella);

        /*Mostra el camell guanyador (1, 2, 3, 4, 5, etc...)*/
        JOptionPane.showMessageDialog(null, "GUANYADOR: EL CAMELL "+(Guanyador+1));
    }

    /*Crea el metode setter "setPreparaPista"*/
    public void setPreparaPista(ArrayList<GImage> ArrayCamellsImatge) {
        /*Crea la LiniaMeta que estara a la posicio 900*/
        LiniaMeta = new GLine(900, 900, 900, 0);
        LiniaMeta.setColor(Color.LIGHT_GRAY);
        add(LiniaMeta);

        int comptador = 1;//Crea la variable "comptador"
        daltLinies = 50;//Assigna a "daltLinies" el valor 50
        baixLinies = 50;//Assigna a "baixLinies" el valor 50
        /*I amb el següent bucle foreach, recorrer l'ArrayList "ArrayCamellsImatge"*/
        for (GImage actual : ArrayCamellsImatge) {
            /*Creara etiquetes amb el valor de "comptador"*/
            GLabel num = new GLabel(String.valueOf(comptador));
            num.setLocation(3, daltLinies);
            num.setColor(Color.LIGHT_GRAY);
            num.setFont("Arial-30");
            add(num);

            /*Afegira els camells a la posicio inicial "0", i li assignara la mida "50x50"*/
            add(actual);
            actual.setSize(50, 50);
            actual.setLocation(0, daltImg);

            /*Separa als camells per Linies, per crear les linies executara el metode setter "setLinies"*/
            setLinies(0, 900, daltLinies, baixLinies);

            daltImg += 50;//Incrementa "daltImg" en 50
            daltLinies += 50;//Incrementa "daltLinies" en 50
            baixLinies += 50;//Incrementa "baixLinies" en 50
            comptador++;//Incrementa "comptador" en 1
        }
    }

    /*Crea el metode setter "setLinies"*/
    public void setLinies(int esquerra, int dreta, int dalt, int baix) {
        /*Crea una linia en la posicio especificada en els parametres*/
        GLine linia1 = new GLine(esquerra, dalt, dreta, baix);
        linia1.setColor(Color.LIGHT_GRAY);
        add(linia1);
    }

    /*Crea el metode getter "getNumeroAleatori"*/
    public int getNumeroAleatori(int min, int max) {
        /*Genera un numero aleatori entre el "min" i el "max" (parametres) i el retorna*/
        int numero = ThreadLocalRandom.current().nextInt(min, max);

        return numero;
    }

    /*Crea el metode setter "setMostraEstrella"*/
    public void setMostraEstrella(int posicioEstrella) {
        /*Crea una estrella que sera a la posicio especificada en el parametre*/
        Estrella estrella = new Estrella(ruta+"/star.png");
        estrella.setTamanyLocaltizacioEstrella(posicioEstrella);
        GImage imgEstrella = estrella.getRetornaEstrella();
        add(imgEstrella);
    }
}

/*Crea la Classe "Camells" que tingui amb herencia la classe "GraphicsProgram"*/
class Camells extends GraphicsProgram {
    private String rutaImatge;//Crea la variable "rutaImatge"
    private int tipusCamell;//Crea la variable "tipusCamell"

    /*Crea el metode constructor "Camells"*/
    public Camells(String rutaImatge, int tipus) {
        this.rutaImatge = rutaImatge;//assigna a "rutaImatge" el valor del parametre "rutaImatge"

        this.tipusCamell = tipus;//assigna a "tipusCamell" el valor del parametre "tipus"
    }

    /*Crea el metode getter "getTipusCamell", que retornara el valor de "tipusCamell"*/
    public int getTipusCamell() {return tipusCamell;}

    /*Crea el metode getter "getImatgeCamell"*/
    public GImage getImatgeCamell() {
        /*Crea una imatge amb el valor de "rutaImatge" com a ruta*/
        GImage camell = new GImage(rutaImatge);
        /*I retorna la imatge "camell"*/
        return camell;
    }
}

/*Crea la Classe "Estrella" que tingui amb herencia la classe "GraphicsProgram"*/
class Estrella extends GraphicsProgram {
    private GImage estrella;//Crea la variable "estrella"

    /*Crea el metode constructor "Estrella", que li assigni a la imatge "estrella" la ruta del parametre "ruta"*/
    public Estrella(String ruta) {estrella = new GImage(ruta);}

    /*Crea el metode setter "setTamanyLocaltizacioEstrella"*/
    public void setTamanyLocaltizacioEstrella(int posicioEstrella) {
        /*estableix a la imatge "estrella" el tamany "50x50"*/
        estrella.setSize(50, 50);
        /*I afegeix la estrella a la posicio "900" i al valor del parametre "posicioEstrella"*/
        estrella.setLocation(900, posicioEstrella);
    }

    /*Crea el metode getter "getRetornaEstrella", que retorni la imatge "estrella"*/
    public GImage getRetornaEstrella() {return estrella;}
}