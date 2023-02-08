package ObjetosJuego;
import Math.Vector2d;
import graphics.Assets;
import imput.Teclado;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends ObjetosMobi{

    private Vector2d direccion;


    public Player(Vector2d position, Vector2d velocidad, BufferedImage textura) {
        super(position, velocidad, textura);
        direccion = new Vector2d(0,1);
    }


    @Override
    public void actualizar() {
        if(Teclado.RIGHT){
            angulo += Math.PI/10;
        }
        if(Teclado.LEFT){
            angulo -= Math.PI/10;
        }

       direccion = direccion.setDireccion(angulo - Math.PI/2);

    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angulo, Assets.player.getWidth()/2, Assets.player.getHeight()/2);
        graphics2D.drawImage(Assets.player, at, null);
    }
}
