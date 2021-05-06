package Camells;

/*Importa les següents llibraries*/
import acm.graphics.*;
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
        /*Creem el ArrayList "rutaImatgesCamells", on posarem la ruta de les imatges dels camells*/
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

        /*Creem el ArrayList "tipusCamells", on posarem el tipus dels camells, poden ser un dels següents tipus:
         * 1 : Camells Ràpids
         * 2 : Camells Fondistes
         * 3 : Camells AntiSenars
         * 4 : Camells Flipats*/
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

        /*Creem el ArrayList "camells"*/
        ArrayList<Camells> camells = new ArrayList<>();

        /*Instanciem el metode "Camells", afegint un valor més al ArrayList "rutaImatgesCamells"*/
        for (int i = 0; i < rutaImatgesCamells.size() && i < tipusCamells.size(); i++) {
            camells.add(new Camells(rutaImatgesCamells.get(i), tipusCamells.get(i)));
        }

        /*Creem el ArrayList "ArrayCamellsImatge"*/
        ArrayList<GImage> ArrayCamellsImatge = new ArrayList<>();

        /*I per cada valor del ArrayList "camells", executa el metode getter "getImatgeCamell" (que retornara un GImage)*/
        for (Camells actual : camells) {
            ArrayCamellsImatge.add(actual.getImatgeCamell());
        }

        /*Executa el metode setter "setPreparaPista"*/
        setPreparaPista(ArrayCamellsImatge);

        int posicioJugador = 0;//Crea la variable "posicioJugador"
        /*El bucle while funcionara mentre la variable "posicioJugador" sigui diferent a la posicio X de "LiniaMeta" menys 100*/
        while (posicioJugador != (LiniaMeta.getX()-100)) {
            /*Executa el metode getter "getCarrera2"*/
            posicioJugador = getCarrera2(ArrayCamellsImatge, camells);
            /*Si la variable "posicioJugador" es igual a la posicio X de "LiniaMeta" menys 100, trenca el bucle while*/
            if (posicioJugador >= (LiniaMeta.getX()-100)) {
                break;
            }
        }

        /*I Executa el metode setter "setComprovarGuanyador"*/
        setComprovarGuanyador(ArrayCamellsImatge);
    }

    /*Crea el metode getter "getCarrera2"*/
    public int getCarrera2(ArrayList<GImage> ArrayCamellsImatge, ArrayList<Camells> camells) {
        int posicioJugador = 0;//Crea la variable "posicioJugador"
        /*I recorrera els ArrayLists "ArrayCamellsImatge" i "camells"*/
        for (int i = 0; i < ArrayCamellsImatge.size() && i < camells.size(); i++) {
            /*Si el camell "actual" esta a una posicio X major o igual que la posicio X de "LiniaMeta" menys 100*/
            if (ArrayCamellsImatge.get(i).getX() >= (LiniaMeta.getX()-100)) {
                /*Guarda la posicio del camell en la variable "posicioJugador", i trenca el bucle for*/
                posicioJugador = (int) ArrayCamellsImatge.get(i).getX();
                break;
            } else {/*Si nom fara el següent*/
                if (camells.get(i).getTipusCamell() == 1) {//Si el camell es Ràpid (1)
                    /*Executa el metode getter "getNumeroAleatori", per generar un numero aleatori entre 1 i 50*/
                    numero = getNumeroAleatori(1, 40);
                    /*I abancara el numero que hagui sortit aleatori multiplicat per 2*/
                    ArrayCamellsImatge.get(i).move((numero * 2), 0);
                } else if (camells.get(i).getTipusCamell() == 2) {//Si el camell es Fondista (2)
                    /*Executa el metode getter "getNumeroAleatori", per generar un numero aleatori entre 5 i 10*/
                    numero = getNumeroAleatori(5, 10);
                    /*I abancara el numero que hagui sortit aleatori*/
                    ArrayCamellsImatge.get(i).move(numero, 0);
                } else if (camells.get(i).getTipusCamell() == 3) {//Si el camell es AntiSenar (3)
                    /*Executa el metode getter "getNumeroAleatori", per generar un numero aleatori entre 1 i 100*/
                    numero = getNumeroAleatori(1, 40);
                    /*I només abancara si el numero que ha sortit aleatori es parell*/
                    if ((numero % 2) == 0) {
                        ArrayCamellsImatge.get(i).move(numero, 0);
                    } else {
                        /*Si el numero es senar, abancara només 2*/
                        ArrayCamellsImatge.get(i).move(2, 0);
                    }
                } else if (camells.get(i).getTipusCamell() == 4) {//Si el camell es Flipats (4)
                    /*Executa el metode getter "getNumeroAleatori", per generar un numero aleatori entre 1 i 50*/
                    numero = getNumeroAleatori(1, 40);
                    /*I abancara el numero que hagui sortit aleatori*/
                    ArrayCamellsImatge.get(i).move(numero, 0);

                    /*Executa el metode getter "getNumeroAleatori", per generar un numero aleatori entre -10 i -1*/
                    numero = getNumeroAleatori(-10, -1);
                    /*Si el camell està a la posicio X major que 10*/
                    if (ArrayCamellsImatge.get(i).getX() > 10) {
                        /*Retrocedeix el numero que hagui sortit aleatori*/
                        ArrayCamellsImatge.get(i).move(numero, 0);
                    } else {
                        /*Si no, si el camell està a la posicio X major que 0*/
                        if (ArrayCamellsImatge.get(i).getX() > 0) {
                            /*Retrocedeix -1*/
                            ArrayCamellsImatge.get(i).move(-1, 0);
                        }
                    }
                }
            }
            ArrayCamellsImatge.get(i).pause(7);/*I pausa en 7 segons*/
        }

        return posicioJugador;/*Retorna el valor de "posicioJugador"*/
    }
}