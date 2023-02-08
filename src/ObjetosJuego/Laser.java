package ObjetosJuego;

import Math.Vector2d;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Laser extends ObjetosMobi{
    public Laser(Vector2d position, Vector2d velocidad, double maxVel, double angulo, BufferedImage textura) {
        super(position, velocidad, maxVel, textura);
        this.angulo = angulo;
        this.velocidad = velocidad.escalar(maxVel);
    }

    @Override
    public void actualizar() {

        position = position.add(velocidad);
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angulo);

        graphics2D.drawImage(textura, at, null);
    }
}
