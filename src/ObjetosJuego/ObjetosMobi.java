package ObjetosJuego;

import Estados.EstadoJuego;
import Math.Vector2d;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ObjetosMobi extends ObjetosJuego {

    protected Vector2d velocidad;
    protected AffineTransform at;
    protected double angulo;
    protected double maxVel;
    protected int ancho;
    protected int altura;
    protected EstadoJuego estadoJuego;


    public ObjetosMobi(Vector2d position, Vector2d velocidad, double maxVel, BufferedImage textura, EstadoJuego estadoJuego) {
        super(position, textura);
        this.velocidad = velocidad;
        this.maxVel = maxVel;
        this.estadoJuego = estadoJuego;
        ancho = textura.getWidth();
        altura = textura.getHeight();
        angulo = 0;
    }

    @Override
    public void actualizar() {

    }

    @Override
    public void draw(Graphics graphics) {

    }
}
