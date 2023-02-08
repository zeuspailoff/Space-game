package ObjetosJuego;
import Math.Vector2d;
import imput.Teclado;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends ObjetosJuego{

    public Player(Vector2d position, BufferedImage textura) {
        super(position, textura);
    }

    @Override
    public void actualizar() {

        if(Teclado.RIGHT){
            position.setX(position.getX() + 1);
        }

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(textura, (int)position.getX(),(int)position.getY(), null);
    }
}
