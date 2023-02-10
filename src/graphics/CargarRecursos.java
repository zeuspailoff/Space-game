package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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

    public static Clip cargarSonido(String path){


        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(CargarRecursos.class.getResource(path)));
            return clip;
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
           e.printStackTrace();
        }
        return null;
    }
    }

