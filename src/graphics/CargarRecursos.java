package graphics;

import java.awt.*;
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

    public static Font cargarFuente(String path, int size)  {

        try {
            return Font.createFont(Font.TRUETYPE_FONT,
                    CargarRecursos.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    }

