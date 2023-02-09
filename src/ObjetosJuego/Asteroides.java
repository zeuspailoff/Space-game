package ObjetosJuego;

import ObjetosJuego.Constantes.*;
import Estados.EstadoJuego;
import Math.Vector2d;
import graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static ObjetosJuego.Constantes.*;

public class Asteroides extends ObjetosMobi{

    private Size size;

    public Asteroides(
            Vector2d position, Vector2d velocidad, double maxVel, BufferedImage textura, EstadoJuego estadoJuego, Size size) {
        super(position, velocidad, maxVel, textura, estadoJuego);
        this.size = size;
    }



    @Override
    public void actualizar(){
            position = position.add(velocidad);

        if(position.getX() > Constantes.WIDTH){
            position.setX(0);
        }
        if(position.getY() > Constantes.HEIGHT){
            position.setY(0);
        }
        if(position.getX()< 0){
            position.setX(Constantes.WIDTH);
        }
        if(position.getY()< 0){
            position.setY(Constantes.HEIGHT);
        }
        angulo+= DELTAANGULO;

    }

    @Override
    public void draw(Graphics graphics){

        Graphics2D graphics2D = (Graphics2D) graphics;

        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        at.rotate(angulo, ancho/2, altura/2);

        graphics2D.drawImage(textura, at, null);
    }

    public Size getSize() {
        return size;
    }

}
