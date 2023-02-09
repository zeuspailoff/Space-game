package ObjetosJuego;

import Estados.EstadoJuego;
import Math.Vector2d;
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


    public ObjetosMobi(Vector2d position, Vector2d velocidad, double maxVel, BufferedImage textura, EstadoJuego estadoJuego) {
        super(position, textura);
        this.velocidad = velocidad;
        this.maxVel = maxVel;
        this.estadoJuego = estadoJuego;
        ancho = textura.getWidth();
        altura = textura.getHeight();
        angulo = 0;
    }

    protected void colisiones( ){
        ArrayList<ObjetosMobi> objetosMobi = estadoJuego.getObjetosMobi();

        for(int i = 0; i < objetosMobi.size(); i++){

            ObjetosMobi m = objetosMobi.get(i);

            if (m.equals(this))
                continue;

            double distance = m.getCenter().subtrat(getCenter()).getMagnitude();

            if (distance < m.ancho/2 + ancho/2 && objetosMobi.contains(this)){
                objetosColision(m, this);

            }

        }

    }

    private void objetosColision( ObjetosMobi a, ObjetosMobi b){

        if (!(a instanceof Asteroides && b instanceof Asteroides)){
            a.Destruir();
            b.Destruir();
        }
    }
    protected void Destruir() {
        estadoJuego.getObjetosMobi().remove(this);
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
}
