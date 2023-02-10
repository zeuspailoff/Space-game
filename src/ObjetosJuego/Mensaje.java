package ObjetosJuego;

import Estados.EstadoJuego;
import Math.Vector2d;

import java.awt.*;

public class Mensaje {

    private EstadoJuego estadoJuego;
    private float alpha;
    private String text;
    private Vector2d position;
    private Color color;
    private boolean center;
    private boolean efecto;
    private Font fuente;
    private final float deltaAlpha = 0.1f;

    public Mensaje(EstadoJuego estadoJuego, float alpha, String text,
                   Vector2d position, Color color, boolean center, boolean efecto, Font fuente) {
        this.estadoJuego = estadoJuego;
        this.alpha = alpha;
        this.text = text;
        this.position = position;
        this.color = color;
        this.center = center;
        this.efecto = efecto;
        this.fuente = fuente;

        if(efecto){
            alpha = 1;
        }else {
            alpha = 0;
        }


    }

    public void draw(Graphics2D graphics2D){

    }
}
