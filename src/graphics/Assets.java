package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

    public static BufferedImage player;
    public static BufferedImage fuego;
    public static void init()  {

        player = CargarRecursos.CargaImagen("/Recourses/PNG/playerShip3_red.png");
        fuego = CargarRecursos.CargaImagen("/Recourses/PNG/Effects/fire01.png");
    }
}
