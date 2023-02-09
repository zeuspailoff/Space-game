package ObjetosJuego;

import Estados.EstadoJuego;
import Math.Vector2d;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static graphics.Assets.greenLaser;

public class Laser extends ObjetosMobi{
    public Laser(Vector2d position, Vector2d velocidad, double maxVel, double angulo, BufferedImage textura, EstadoJuego estadoJuego) {
        super(position, velocidad, maxVel, textura, estadoJuego);
        this.angulo = angulo;
        this.velocidad = velocidad.escalar(maxVel);
    }

    @Override
    public void actualizar() {

        position = position.add(velocidad);
        if(position.getX() < 0 || position.getX() > Constantes.WIDTH || position.getY() < 0 || position.getY() > Constantes.HEIGHT ){
            Destruir();

        }
        colisiones();
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        at = AffineTransform.getTranslateInstance(position.getX() - ancho/2, position.getY());
        at.rotate(angulo );

        graphics2D.drawImage(textura, at, null);
    }

    @Override
    public Vector2d getCenter() {

        return new Vector2d(position.getX() + ancho/2, position.getY() + ancho/2);
    }
}
