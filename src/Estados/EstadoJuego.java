package Estados;
import Math.Vector2d;
import ObjetosJuego.Player;
import graphics.Assets;

import java.awt.*;

public class EstadoJuego {

    private Player player;

    public EstadoJuego(){

        player = new Player(new Vector2d(100,500), new Vector2d(0,0),Assets.player);
    }

    public void actualizar(){

        player.actualizar();

    }

    public void draw(Graphics graphics){

        player.draw(graphics);
    }
}
