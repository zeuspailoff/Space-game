package ObjetosJuego;

import Estados.EstadoJuego;
import Math.Vector2d;
import graphics.Assets;
import graphics.Sonido;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjetosMobi extends ObjetosJuego {

    protected Vector2d velocidad;
    protected AffineTransform at;
    protected double angulo;
    protected double maxVel;
    protected int ancho;
    protected int altura;
    protected EstadoJuego estadoJuego;

    private Sonido texplociones;
    protected boolean Dead;


    public ObjetosMobi(Vector2d position, Vector2d velocidad, double maxVel, BufferedImage textura, EstadoJuego estadoJuego) {
        super(position, textura);
        this.velocidad = velocidad;
        this.maxVel = maxVel;
        this.estadoJuego = estadoJuego;
        ancho = textura.getWidth();
        altura = textura.getHeight();
        angulo = 0;
        texplociones = new Sonido(Assets.explociones);
        Dead = false;
    }

    protected void colisiones( ){
        ArrayList<ObjetosMobi> objetosMobi = estadoJuego.getObjetosMobi();

        for(int i = 0; i < objetosMobi.size(); i++){

            ObjetosMobi m = objetosMobi.get(i);

            if (m.equals(this))
                continue;

            double distance = m.getCenter().subtrat(getCenter()).getMagnitude();

            if (distance < m.ancho/2 + ancho/2 && objetosMobi.contains(this) && !m.Dead && !Dead){
                objetosColision(m, this);

            }

        }

    }

    private void objetosColision( ObjetosMobi a, ObjetosMobi b){

        if( a instanceof Player && ((Player) a).isApareciendo()){
            return;
        }
        if( b instanceof Player && ((Player) b).isApareciendo()) {
            return;
        }
        if (!(a instanceof Asteroides && b instanceof Asteroides)){
            estadoJuego.playExplociones(getCenter());
            a.Destruir();
            b.Destruir();
        }
    }
    protected void Destruir() {

        Dead = true;
        if(!(this instanceof Laser)){
            texplociones.play();
        }
    }

    public Vector2d getCenter() {

        return new Vector2d(position.getX() + ancho/2, position.getY() + altura/2);
    }

    @Override
    public void actualizar() {

    }

    @Override
    public void draw(Graphics graphics) {

    }

    public boolean isDead() {return Dead;}
}
