import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

    public static BufferedImage player;
    public static void init()  {

        player = CargarRecursos.CargaImagen("/Recourses/PNG/playerShip3_red.png");
    }
}
