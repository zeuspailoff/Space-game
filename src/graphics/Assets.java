package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

//jugador y laser
    public static BufferedImage player;
    public static BufferedImage fuego;
    public static BufferedImage blueLaser, redLaser, greenLaser;
    //asteroides

    public static BufferedImage[] big = new BufferedImage [4];
    public static BufferedImage[] med = new BufferedImage [2];
    public static BufferedImage[] small = new BufferedImage [2];
    public static BufferedImage[] tiny = new BufferedImage [2];

    // explociones

    public static BufferedImage[] exp = new BufferedImage[9];

    //enemigos
    public static BufferedImage enemigo;

    //numeros

    public static BufferedImage[] numeros = new BufferedImage[11];

    public static BufferedImage vida;

    public static void init()  {
        //jugador
        player = CargarRecursos.CargaImagen("/Recourses/PNG/playerShip3_red.png");
        fuego = CargarRecursos.CargaImagen("/Recourses/PNG/Effects/fire01.png");
        //lasers
        blueLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserBlue06.png");
        redLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserGreen06.png");
        greenLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserRed04.png");

        //Asteroiodes
        for(int i = 0; i < big.length; i++) {
            big[i]= CargarRecursos.CargaImagen("/Recourses/PNG/Meteors/big" +(i + 1)+ ".png" );
        }
        for(int i = 0; i < med.length; i++) {
            med[i]= CargarRecursos.CargaImagen("/Recourses/PNG/Meteors/med" +(i + 1)+ ".png" );
        }
        for(int i = 0; i < small.length; i++) {
            small[i]= CargarRecursos.CargaImagen("/Recourses/PNG/Meteors/small" +(i + 1)+ ".png" );
        }
        for(int i = 0; i < tiny.length; i++) {
            tiny[i]= CargarRecursos.CargaImagen("/Recourses/PNG/Meteors/tiny" +(i + 1)+ ".png" );
        }
        // explociones

        for(int i = 0; i < exp.length; i++){

            exp[i]= CargarRecursos.CargaImagen("/Recourses/PNG/explocions/"+ i + ".png" );
        }
        //enemigos
        enemigo = CargarRecursos.CargaImagen("/Recourses/PNG/ufoYellow.png");

        // vidas y numeros
        vida = CargarRecursos.CargaImagen("/Recourses/PNG/UI/vidas/playerLife2_red.png");

        for (int i = 0; i < numeros.length; i++){
            numeros[i]= CargarRecursos.CargaImagen("/Recourses/PNG/UI/numeros/" +(i + 1)+ ".png" );
        }

    }
}
