package Estados;

import IO.JsonParser;
import IO.PuntuacionFecha;
import Math.Vector2d;
import ObjetosJuego.*;
import graphics.Animacion;
import graphics.Assets;
import graphics.Sonido;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class EstadoJuego extends Estado{

    private final Player player;
    private ArrayList<ObjetosMobi> objetosMobi = new ArrayList<ObjetosMobi>();

    private ArrayList<Animacion> explociones = new ArrayList<Animacion>();
    private ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
    private int puntuacion;
    private int asteroides;

    private int vidas = 3;

    private int oleada = 1;
    private Sonido sonidoFondo;
    private Cronometro enemigoSpawun;
    private Cronometro gameOverTimer;
    private boolean gameOver;

    public EstadoJuego() {

        player = new Player (new Vector2d(Constantes.WIDTH/2 - Assets.player.getWidth()/2,
                Constantes.HEIGHT/2 - Assets.player.getHeight()/2), new Vector2d(),
                Constantes.PLAYER_MAX_VEL, Assets.player, this);

        gameOverTimer = new Cronometro();
        gameOver= false;
        objetosMobi.add(player);

        asteroides = 1;
        iniciarOleada();
        sonidoFondo = new Sonido(Assets.musicaFondo);
        sonidoFondo.loop();
        sonidoFondo.cambiarVolumen(-10.0f);

        enemigoSpawun = new Cronometro();
        enemigoSpawun.encendido(Constantes.UFO_SPAWN_RATE);
    }
    public void addPuntuacion(int valor, Vector2d position){
        puntuacion+= valor;
        mensajes.add(new Mensaje( "+" +valor+ "PUNTOS", position,
                Color.WHITE, false, true, Assets.fuenteMed ));
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

        mensajes.add(new Mensaje ( "OLEADA " + oleada,
                new Vector2d(Constantes.WIDTH / 2, Constantes.HEIGHT / 2),
                Color.WHITE, true, true, Assets.fuenteGrand ));
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

        for (int i = 0; i < objetosMobi.size(); i++){

            ObjetosMobi mo = objetosMobi.get(i);

            mo.actualizar();
            if(mo.isDead()) {
                objetosMobi.remove(i);
                i++;
            }
            }

        for (int i = 0; i < explociones.size(); i++){
            Animacion anim = explociones.get(i);
            anim.actualizar();
            if(!anim.isRunning()){
                explociones.remove(i);
            }
        }

        if(gameOver && !gameOverTimer.isActivo()){
            try {
                ArrayList<PuntuacionFecha> dataList = JsonParser.readFile();
                dataList.add(new PuntuacionFecha(puntuacion));
                JsonParser.writeFile(dataList);
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
        if(!enemigoSpawun.isActivo()){
            enemigoSpawun.encendido(Constantes.UFO_SPAWN_RATE);
            apareceEnemigo();
        }


        for (int i = 0; i < objetosMobi.size(); i++)
            if(objetosMobi.get(i) instanceof Asteroides)
                return;

        iniciarOleada();


    }

    public void draw(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        for (int i = 0; i < mensajes.size(); i++){
            mensajes.get(i).draw(graphics2D);

            if(mensajes.get(i).isDead()){
                mensajes.remove(i);
            }
        }


        for (int i = 0; i < objetosMobi.size(); i++){
            objetosMobi.get(i).draw(graphics);
        }


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

        if(vidas< 1)
            return;

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

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public ArrayList<ObjetosMobi> getObjetosMobi() {

        return objetosMobi;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean quitarVida(){
        vidas--;
        return vidas > 0;
    }

    public void gameOver() {

        Mensaje gameOverMag = new Mensaje(

                "GAME OVER",
                new Vector2d(Constantes.WIDTH/2 - Assets.player.getWidth()/2,
                        Constantes.HEIGHT/2 - Assets.player.getHeight()/2),
                Color.WHITE,
                true,
                true,
                Assets.fuenteGrand
        );

        this.mensajes.add(gameOverMag);
        gameOverTimer.encendido(Constantes.GAME_OVER_TIME);
        gameOver = true;
        Estado.cambiarEstado(new EstadoMenu());
    }
}
