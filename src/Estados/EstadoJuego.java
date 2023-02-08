package Estados;
import Math.Vector2d;
import ObjetosJuego.ObjetosMobi;
import ObjetosJuego.Player;
import graphics.Assets;

import java.awt.*;
import java.util.ArrayList;

public class EstadoJuego {

    private Player player;
    private ArrayList<ObjetosMobi> objetosMobi = new ArrayList<ObjetosMobi>();

    public EstadoJuego() {

        player = new Player(new Vector2d(100, 500), new Vector2d(0, 0), 8, Assets.player, this);
        objetosMobi.add(player);
    }

    public void actualizar() {

        for (int i = 0; i < objetosMobi.size(); i++)
            objetosMobi.get(i).actualizar();

    }

    public void draw(Graphics graphics) {

        for (int i = 0; i < objetosMobi.size(); i++)
            objetosMobi.get(i).draw(graphics);
    }

    public ArrayList<ObjetosMobi> getObjetosMobi() {
        return objetosMobi;
    }

    public void setObjetosMobi(ArrayList<ObjetosMobi> objetosMobi) {
        this.objetosMobi = objetosMobi;
    }
}
