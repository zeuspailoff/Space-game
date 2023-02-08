package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

    public static BufferedImage player;
    public static BufferedImage fuego;
    public static BufferedImage blueLaser, redLaser, greenLaser;
    public static void init()  {

        player = CargarRecursos.CargaImagen("/Recourses/PNG/playerShip3_red.png");
        fuego = CargarRecursos.CargaImagen("/Recourses/PNG/Effects/fire01.png");
        blueLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserBlue06.png");
        redLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserGreen06.png");
        greenLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserRed04.png");
    }
}
