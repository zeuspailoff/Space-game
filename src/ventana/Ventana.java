package ventana;

import Estados.Estado;
import Estados.EstadoMenu;
import ObjetosJuego.Constantes;
import ObjetosJuego.Constantes.*;
import Estados.EstadoJuego;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import graphics.Assets;
import graphics.CargarRecursos;
import imput.Mause;
import imput.Teclado;


public class Ventana extends JFrame implements Runnable{


    private final Canvas canvas;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics graphics;
    private boolean running = false;

    private final int FPS = 60;
    private double TARGETTIME = 1000000000/FPS;
    private double delta = 0;
    private int AVERAGEFPS = FPS;



    private Teclado teclado;
    private Mause mouseInput;
    private BufferedImage fondo;



    public Ventana() {
        // preparamos ventana de juego
        setTitle("Space game ");
        setSize(Constantes.WIDTH, Constantes.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);




        canvas= new Canvas();
        teclado = new Teclado();
        mouseInput = new Mause();

        canvas.setPreferredSize(new Dimension(Constantes.WIDTH,  Constantes.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constantes.WIDTH,  Constantes.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constantes.WIDTH,  Constantes.HEIGHT));
        canvas.setFocusable(true);

        add(canvas);


        canvas.addKeyListener(teclado);
        canvas.addMouseListener(mouseInput);
        canvas.addMouseMotionListener(mouseInput);
        setVisible(true);
    }


    private void actualizar(){
        teclado.actualizar();
        Estado.getEstadoActual().actualizar();

    }
    private void draw(  ){
        bs = canvas.getBufferStrategy();

        if(bs == null){
                canvas.createBufferStrategy(3);
                return;
            }
        graphics = bs.getDrawGraphics();
        //zona para dibujar

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,Constantes.WIDTH, Constantes.HEIGHT);

        Estado.getEstadoActual().draw(graphics);

        graphics.drawString(""+AVERAGEFPS, 10, 20);

        //------------------------------------------------

        graphics.dispose();
        bs.show();
    }
    private void init() {

        Assets.init();
        Estado.cambiarEstado(new EstadoMenu());
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
