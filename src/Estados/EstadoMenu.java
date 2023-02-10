package Estados;

import ObjetosJuego.Constantes;
import UI.Action;
import UI.Boton;
import graphics.Assets;

import java.awt.*;
import java.util.ArrayList;

public class EstadoMenu extends Estado{

    private ArrayList<Boton> botones;

    public EstadoMenu() {

        botones = new ArrayList<Boton>();

        botones.add(new Boton(
                Assets.botonBlue,
                Assets.botonGreen,
                Constantes.WIDTH / 2 - Assets.botonYellow.getWidth() / 2,
                Constantes.HEIGHT / 2 - Assets.botonYellow.getHeight() * 2 ,
                Constantes.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        Estado.cambiarEstado(new EstadoJuego());
                    }
                }
        ));
        botones.add(new Boton(
                Assets.botonRed,
                Assets.botonGreen,
                Constantes.WIDTH / 2 - Assets.botonRed.getWidth() / 2,
                Constantes.HEIGHT / 2 - Assets.botonRed.getHeight() / 2,
                Constantes.EXIT,
                new Action() {
                    @Override
                    public void doAction() {
                        System.exit(0);
                    }
                }
        ));


    }

    @Override
    public void actualizar() {

        for( Boton b : botones){
            b.actualizar();
        }

    }

    @Override
    public void draw(Graphics graphics) {

        for( Boton b : botones){
            b.draw(graphics);
        }

    }
}
