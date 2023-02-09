package ObjetosJuego;

import Estados.EstadoJuego;
import Math.Vector2d;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemigos extends ObjetosMobi{

    private ArrayList<Vector2d> path;
    private Vector2d node;
    private int index;
    private boolean siguiendo;
    public Enemigos(Vector2d position, Vector2d velocidad, double maxVel, BufferedImage textura, ArrayList<Vector2d> path, EstadoJuego estadoJuego) {
        super(position, velocidad, maxVel, textura, estadoJuego);
        this.path = path;
        index = 0;
        siguiendo = true;
    }

    private Vector2d pathSiguiendo(){
        node = path.get(index);

        double distanciaNode = node.subtrat(getCenter()).getMagnitude();

        if(distanciaNode < Constantes.NODE_RADIO){
            index++;
            if(index >= path.size()){
                siguiendo = false;
            }
        }
        return seekForce(node);
    }

    private Vector2d seekForce(Vector2d target){
        Vector2d velocidadDeseada = target.subtrat(getCenter());
        velocidadDeseada = velocidadDeseada.normalizar().escalar(maxVel);
        return velocidadDeseada.subtrat(velocidad);
    }

    @Override
    public void actualizar() {

        Vector2d pathSiguiendo;
        if(siguiendo){
            pathSiguiendo = pathSiguiendo();

        }else {
            pathSiguiendo = new Vector2d();
        }
        pathSiguiendo = pathSiguiendo.escalar(1/ Constantes.ENEMIGOS_MASA);

        velocidad = velocidad.add(pathSiguiendo);

        velocidad = velocidad.limite(maxVel);

        position = position.add(velocidad);

        if(position.getX() > Constantes.WIDTH || position.getX() > Constantes.HEIGHT ||
                position.getX() < 0 || position.getX() < 0){
            Destruir();
        }
        angulo += 0.05;
        colisiones();

    }

    @Override
    public void draw(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;

        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        at.rotate(angulo, ancho/2, altura/2);

        graphics2D.drawImage(textura, at, null);

        graphics.setColor(Color.RED);

        for (int i = 0; i < path.size(); i++){
            graphics.drawRect((int) path.get(i).getY(), (int) path.get(i).getY(), 5 , 5);
        }

    }
}
