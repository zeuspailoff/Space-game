package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

    public static BufferedImage player;
    public static BufferedImage fuego;
    public static BufferedImage blueLaser, redLaser, greenLaser;
    //asteroides

    public static BufferedImage[] big = new BufferedImage [4];
    public static BufferedImage[] med = new BufferedImage [2];
    public static BufferedImage[] small = new BufferedImage [2];
    public static BufferedImage[] tiny = new BufferedImage [2];
    public static void init()  {

        player = CargarRecursos.CargaImagen("/Recourses/PNG/playerShip3_red.png");
        fuego = CargarRecursos.CargaImagen("/Recourses/PNG/Effects/fire01.png");
        blueLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserBlue06.png");
        redLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserGreen06.png");
        greenLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserRed04.png");


        for(int i = 0; i < big.length; i++) {
            big[i]= CargarRecursos.CargaImagen("Recourses/PNG/Meteors/big" +(i + 1)+ ".png" );
        }
        for(int i = 0; i < med.length; i++) {
            med[i]= CargarRecursos.CargaImagen("Recourses/PNG/Meteors/med" +(i + 1)+ ".png" );
        }
        for(int i = 0; i < small.length; i++) {
            small[i]= CargarRecursos.CargaImagen("Recourses/PNG/Meteors/small" +(i + 1)+ ".png" );
        }
        for(int i = 0; i < tiny.length; i++) {
            tiny[i]= CargarRecursos.CargaImagen("Recourses/PNG/Meteors/tiny" +(i + 1)+ ".png" );
        }
    }
}
