package ObjetosJuego;

import Estados.EstadoJuego;
import Math.Vector2d;
import graphics.Texto;

import java.awt.*;

public class Mensaje {


    private float alpha;
    private String text;
    private Vector2d position;
    private Color color;
    private boolean center;
    private boolean efecto;
    private Font fuente;
    private final float deltaAlpha = 0.01f;
    private boolean dead;

    public Mensaje( String text,
                   Vector2d position, Color color, boolean center, boolean efecto, Font fuente) {


        this.text = text;
        this.position = position;
        this.color = color;
        this.center = center;
        this.efecto = efecto;
        this.fuente = fuente;
        this.dead = false;

        if(efecto){
            alpha = 1;
        }else {
            alpha = 0;
        }




    }

    public void draw(Graphics2D graphics2D){

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        Texto.drawTexto(graphics2D, text, position, center, color, fuente );

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        position.setY(position.getY() - 1);

        if(efecto){
            alpha -= deltaAlpha;

        }else {
            alpha += deltaAlpha;
        }
        if(efecto && alpha < 0 ){
            dead = true;
        }
        if( !efecto && alpha > 1){
            efecto = true;
            alpha =1;
        }
    }

    public boolean isDead(){return dead;}
}
