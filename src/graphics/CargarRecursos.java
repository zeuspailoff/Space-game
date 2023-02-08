package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CargarRecursos{

    public static BufferedImage CargaImagen(String path)  {
        try {
            return ImageIO.read(CargarRecursos.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
