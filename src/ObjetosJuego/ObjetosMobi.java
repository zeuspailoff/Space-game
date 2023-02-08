package ObjetosJuego;

import Math.Vector2d;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ObjetosMobi extends ObjetosJuego {

    protected Vector2d velocidad;
    protected AffineTransform at;
    protected double angulo;
    public ObjetosMobi(Vector2d position, Vector2d velocidad, BufferedImage textura) {
        super(position, textura);
        this.velocidad = velocidad;
        angulo = 0;
    }

    @Override
    public void actualizar() {

    }

    @Override
    public void draw(Graphics graphics) {

    }
}
