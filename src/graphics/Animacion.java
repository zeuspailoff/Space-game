package graphics;

import Math.Vector2d;
import java.awt.image.BufferedImage;

public class Animacion {

    private BufferedImage[] frames;
    private int velocidad;
    private int index;
    private boolean running;
    private Vector2d position;
    private long time, lastTime;


    public Animacion(BufferedImage[] frames, int velocidad, Vector2d position){
        this.frames = frames;
        this.velocidad = velocidad;
        this.position = position;
        index = 0;
        running = true;
        time = 0;
        lastTime = System.currentTimeMillis();

    }

    public void actualizar() {
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(time >= velocidad){
            time = 0;
            index++;
            if(index >= frames.length){
                running = false;
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public Vector2d getPosition() {
        return position;
    }

    public BufferedImage getFotogramaActual() {

        return frames[index];

    }
}
