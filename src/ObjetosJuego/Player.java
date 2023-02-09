package ObjetosJuego;
import Estados.EstadoJuego;
import Math.Vector2d;
import graphics.Assets;
import imput.Teclado;
import ventana.Ventana;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static ObjetosJuego.Constantes.DELTAANGULO;

public class Player extends ObjetosMobi{

    private Vector2d direccion;
    private Vector2d acceleracion;
    private final double ACC = 0.2;

    private boolean accelerando = false;

    private  Cronometro ratioDisparo;




    public Player(Vector2d position, Vector2d velocidad, double maxVel, BufferedImage textura, EstadoJuego estadoJuego) {
        super(position, velocidad, maxVel,textura, estadoJuego);
        this.estadoJuego = estadoJuego;
        direccion = new Vector2d(0,1);
        acceleracion = new Vector2d();
        ratioDisparo = new Cronometro();

    }


    @Override
    public void actualizar() {

        if(Teclado.SHOOT && !ratioDisparo.isActivo()){
            estadoJuego.getObjetosMobi().add(new Laser(
                    getCenter().add(direccion.escalar(ancho)),
                    direccion,
                    10,
                    angulo,
                    Assets.greenLaser,
                    estadoJuego
            ));
            ratioDisparo.encendido(Constantes.RATIODISPARO);
        }

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
        ratioDisparo.actualizar();
        colisiones();
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
