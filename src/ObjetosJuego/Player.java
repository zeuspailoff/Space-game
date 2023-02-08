package ObjetosJuego;
import Math.Vector2d;
import graphics.Assets;
import imput.Teclado;
import ventana.Ventana;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends ObjetosMobi{

    private Vector2d direccion;
    private Vector2d acceleracion;
    private final double ACC = 0.2;
    private final double DELTAANGULO = 0.1;
    private boolean accelerando = false;


    public Player(Vector2d position, Vector2d velocidad,double maxVel, BufferedImage textura) {
        super(position, velocidad, maxVel,textura);
        direccion = new Vector2d(0,1);
        acceleracion = new Vector2d();
    }


    @Override
    public void actualizar() {
        if(Teclado.RIGHT){
            angulo += DELTAANGULO;
        }
        if(Teclado.LEFT){
            angulo -= DELTAANGULO;
        }
        if(Teclado.UP){
            acceleracion= direccion.escalar(ACC);
            accelerando = true;
        }else {
            if (velocidad.getMagnitude() != 0){
                acceleracion = (velocidad.escalar(-1).normalizar()).escalar(ACC/2);
                accelerando = false;
            }
        }
        velocidad = velocidad.add(acceleracion);
        velocidad= velocidad.limite(maxVel);
        direccion = direccion.setDireccion(angulo - Math.PI/2);
        position = position.add(velocidad);
        if(position.getX() > Ventana.WIDTH){
            position.setX(0);
        }
        if(position.getY() > Ventana.HEIGHT){
            position.setY(0);
        }
        if(position.getX()< 0){
            position.setX(Ventana.WIDTH);
        }
        if(position.getY()< 0){
            position.setY(Ventana.HEIGHT);
        }
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;


        AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX()+ ancho/2 - 6
                , position.getY() + altura/2 + 10);


        at1.rotate(angulo, +6, -10);


        if(accelerando){
            graphics2D.drawImage(Assets.fuego, at1, null);

        }




        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angulo, ancho/2, altura/2);
        graphics2D.drawImage(Assets.player, at, null);
    }
}
