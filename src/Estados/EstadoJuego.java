package Estados;
import Math.Vector2d;
import ObjetosJuego.*;
import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EstadoJuego {

    private Player player;
    private ArrayList<ObjetosMobi> objetosMobi = new ArrayList<ObjetosMobi>();
    private int asteroides;

    public EstadoJuego() {

        player = new Player(new Vector2d(100, 500), new Vector2d(0, 0), 8, Assets.player, this);
        objetosMobi.add(player);

        asteroides = 1;
        iniciarOleada();
    }
    public void iniciarOleada() {
        double x, y ;

        for(int i = 0; i < asteroides; i++) {
            x = i % 2 == 0 ? Math.random() * Constantes.WIDTH : 0;
            y = i % 2 == 0 ? 0 : Math.random() * Constantes.HEIGHT;

            BufferedImage textura = Assets.big[(int) (Math.random() * Assets.big.length)];

            objetosMobi.add(new Asteroides(
                    new Vector2d(x,y),
                    new Vector2d(0 , 1).setDireccion(Math.random()* Math.PI*2),
                    Constantes.ASTEROID_VEL * Math.random() + 1 ,
                    textura,
                    this,
                    Size.BIG
            ));
        }
    }

    public void actualizar() {

        for (int i = 0; i < objetosMobi.size(); i++)
            objetosMobi.get(i).actualizar();

    }

    public void draw(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        for (int i = 0; i < objetosMobi.size(); i++)
            objetosMobi.get(i).draw(graphics);
    }

    public ArrayList<ObjetosMobi> getObjetosMobi() {

        return objetosMobi;
    }


}
