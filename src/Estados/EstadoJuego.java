package Estados;
import Math.Vector2d;
import ObjetosJuego.*;
import graphics.Animacion;
import graphics.Assets;
import graphics.Texto;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static graphics.Assets.enemigo;
import static graphics.Assets.vida;

public class EstadoJuego {

    private Player player;
    private ArrayList<ObjetosMobi> objetosMobi = new ArrayList<ObjetosMobi>();

    private ArrayList<Animacion> explociones = new ArrayList<Animacion>();
    private int puntuacion;
    private int asteroides;

    private int vidas = 3;

    private int oleada = 1;

    public EstadoJuego() {

        player = new Player (new Vector2d(Constantes.WIDTH/2 - Assets.player.getWidth()/2,
                Constantes.HEIGHT/2 - Assets.player.getHeight()/2), new Vector2d(),
                Constantes.PLAYER_MAX_VEL, Assets.player, this);
        objetosMobi.add(player);

        asteroides = 1;
        iniciarOleada();
    }
    public void addPuntuacion(int valor){
        puntuacion+= valor;
    }

    public void dividirAsteroide(Asteroides asteroides) {
        Size size = asteroides.getSize();
        BufferedImage[] textura = size.textura;

        Size newSize = null;

        switch (size) {
            case BIG -> newSize = Size.MED;
            case MED -> newSize = Size.SMALL;
            case SMALL -> newSize = Size.TINY;
            default -> {
                return;
            }
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
        apareceEnemigo();

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
        drawPuntuacion((Graphics2D) graphics);
        drawVidas((Graphics2D)graphics);
        Texto.drawTexto(graphics, "OLEADA " + oleada, new Vector2d(Constantes.WIDTH/2, Constantes.HEIGHT /2),
                true, Color.WHITE, Assets.fuenteGrand);
    }

    private void drawPuntuacion(Graphics2D graphics){

        Vector2d pos = new Vector2d(850,25);
        String puntuacionToString = Integer.toString(puntuacion);

        for(int i = 0; i < puntuacionToString.length(); i++){

            graphics.drawImage(Assets.numero
                    [Integer.parseInt(puntuacionToString.substring(i, i+1))],
                    (int)pos.getX(), (int)pos.getY(),null);
            pos.setX(pos.getX() + 20);
        }
    }

    private void drawVidas(Graphics graphics){
        Vector2d vidaPos = new Vector2d(25 , 25);
        graphics.drawImage(Assets.vida, (int) vidaPos.getX(), (int) vidaPos.getY() ,null);


        String vidasToString = Integer.toString(vidas);

        Vector2d pos = new Vector2d(vidaPos.getX(), vidaPos.getY());

        for (int i = 0; i <vidasToString.length(); i++){
            int numero = Integer.parseInt(vidasToString.substring(i, i+1));

            if(numero <= 0){
                break;
            }
            graphics.drawImage(Assets.numero[numero], (int) pos.getX() + 60, (int)pos.getY() + 5, null);
            pos.setX(pos.getX() + 20);
        }
    }


    public ArrayList<ObjetosMobi> getObjetosMobi() {

        return objetosMobi;
    }

    public Player getPlayer() {
        return player;
    }

    public void quitarVida(){
        vidas--;
    }
}
