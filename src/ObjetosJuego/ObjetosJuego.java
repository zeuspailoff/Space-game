package ObjetosJuego;
import Math.Vector2d;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ObjetosJuego {
    protected BufferedImage textura;
    protected Vector2d position;

    public ObjetosJuego(Vector2d position, BufferedImage textura) {

        this.position = position;
        this.textura = textura;
    }
    public abstract void actualizar();
    public abstract void draw(Graphics graphics);

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }
}
