import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;


public class Ventana extends JFrame implements Runnable{

    public static final int WIDTH = 900, HEIGHT = 700;
    private final Canvas canvas;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics graphics;
    private boolean running = false;

    private final int FPS = 60;
    private double TARGETTIME = 1000000000/FPS;
    private double delta = 0;
    private int AVERAGEFPS = FPS;




    public Ventana() {
        // preparamos ventana de juego
        setTitle("Space game ");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(getIconImage());

        setVisible(true);

        canvas= new Canvas();

        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(true);

        add(canvas);
    }



    private void actualizar(){


    }
    private void draw(  ){
        bs = canvas.getBufferStrategy();

        if(bs == null){
                canvas.createBufferStrategy(3);
                return;
            }
        graphics = bs.getDrawGraphics();
        //zona para dibujar

        graphics.fillRect(0,0,WIDTH,HEIGHT);
        graphics.setColor(Color.black);
        graphics.drawString(""+AVERAGEFPS, 10, 20);

        //------------------------------------------------

        graphics.dispose();
        bs.show();
    }
    private void init() {
        Assets.init();
    }
    @Override
    public void run() {
        while (running) {
            long now = 0;
            long lastTime = System.nanoTime();
            int frames = 0;
            long time = 0;

            init();

            while (running) {
                now = System.nanoTime();
                delta += (now - lastTime) / TARGETTIME;
                time += (now - lastTime);
                lastTime = now;


                if (delta >= 1) {
                    actualizar();
                    draw();
                    delta--;
                    frames++;
                }
                if (time >= 1000000000) {
                    AVERAGEFPS = frames;
                    frames = 0;
                    time = 0;

                }


            }
        }
        stop();

    }
    public void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public void stop(){
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
