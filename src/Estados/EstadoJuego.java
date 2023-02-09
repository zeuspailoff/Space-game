package Estados;
import Math.Vector2d;
import ObjetosJuego.*;
import graphics.Animacion;
import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static graphics.Assets.enemigo;

public class EstadoJuego {

    private Player player;
    private ArrayList<ObjetosMobi> objetosMobi = new ArrayList<ObjetosMobi>();

    private ArrayList<Animacion> explociones = new ArrayList<Animacion>();
    private int asteroides;

    public EstadoJuego() {

        player = new Player(new Vector2d(100, 500), new Vector2d(0, 0), 8, Assets.player, this);
        objetosMobi.add(player);

        asteroides = 1;
        iniciarOleada();
    }

    public void dividirAsteroide(Asteroides asteroides) {
        Size size = asteroides.getSize();
        BufferedImage[] textura = size.textura;

        Size newSize = null;

        switch (size){
            case BIG:
                newSize = Size.MED;
                break;
            case MED:
                newSize = Size.SMALL;
                break;
            case SMALL:
                newSize = Size.TINY;
                break;
            default:
                return;

        }

        for(int i = 0; i < size.cantidad; i++){
            objetosMobi.add(new Asteroides(
                    asteroides.getPosition(),
                    new Vector2d(0 , 1).setDireccion(Math.random()* Math.PI*2),
                    Constantes.ASTEROID_VEL * Math.random() + 1 ,
                    textura[(int)(Math.random() * textura.length)],
                    this,
                    newSize
            ));

        }
    }

    private void apareceEnemigo(){
        int rand = (int) (Math.random()*2);

        double x = rand == 0 ? (Math.random() * Constantes.WIDTH): 0;
        double y = rand == 0 ? 0 : (Math.random() * Constantes.HEIGHT);

        ArrayList<Vector2d> path = new ArrayList<Vector2d>();

        double posX, posY;

        posX = Math.random()*Constantes.WIDTH/2;
        posY = Math.random()*Constantes.HEIGHT/2;
        path.add(new Vector2d(posX, posY));

        posX = Math.random()*(Constantes.WIDTH/2) + Constantes.WIDTH/2;
        posY = Math.random()*Constantes.HEIGHT/2;
        path.add(new Vector2d(posX, posY));

        posX = Math.random()*Constantes.WIDTH/2;
        posY = Math.random()*(Constantes.HEIGHT/2) + Constantes.HEIGHT/2;
        path.add(new Vector2d(posX, posY));

        posX = Math.random()*(Constantes.WIDTH/2) + Constantes.WIDTH/2;
        posY = Math.random()*(Constantes.HEIGHT/2) + Constantes.HEIGHT/2;
        path.add(new Vector2d(posX, posY));

        objetosMobi.add(new Enemigos(
                new Vector2d(x, y),
                new Vector2d(),
                Constantes.ENEMIGO_VELMAX,
                Assets.enemigo,
                path,
                this
        ));
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
        asteroides++;
        apareceEnemigo();
    }

    public void playExplociones(Vector2d position){
        explociones.add(new Animacion(
                Assets.exp,
                50,
                position.subtrat(new Vector2d(Assets.exp[0].getWidth()/2, Assets.exp[0].getHeight()/2))
        ));
    }

    public void actualizar() {

        for (int i = 0; i < objetosMobi.size(); i++)
            objetosMobi.get(i).actualizar();

        for (int i = 0; i < explociones.size(); i++){
            Animacion anim = explociones.get(i);
            anim.actualizar();
            if(!anim.isRunning()){
                explociones.remove(i);
            }
        }


        for (int i = 0; i < objetosMobi.size(); i++)
            if(objetosMobi.get(i) instanceof Asteroides)
                return;

        iniciarOleada();

    }

    public void draw(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        for (int i = 0; i < objetosMobi.size(); i++)
            objetosMobi.get(i).draw(graphics);

        for (int i = 0; i < explociones.size(); i++) {
            Animacion anim = explociones.get(i);
            graphics2D.drawImage(
                    anim.getFotogramaActual(),
                    (int) anim.getPosition().getX(),
                    (int)anim.getPosition().getY(),
                    null );
        }
    }
    public ArrayList<ObjetosMobi> getObjetosMobi() {

        return objetosMobi;
    }

    public Player getPlayer() {
        return player;
    }
}
