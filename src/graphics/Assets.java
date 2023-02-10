package graphics;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

//jugador y laser
    public static BufferedImage player;
    public static BufferedImage fuego;
    public static BufferedImage blueLaser, redLaser, greenLaser;
    //asteroides

    public static BufferedImage[] big = new BufferedImage [4];
    public static BufferedImage[] med = new BufferedImage [2];
    public static BufferedImage[] small = new BufferedImage [2];
    public static BufferedImage[] tiny = new BufferedImage [2];

    // explociones

    public static BufferedImage[] exp = new BufferedImage[9];

    //enemigos
    public static BufferedImage enemigo;

    //numeros

    public static BufferedImage[] numero = new BufferedImage[10];

    public static BufferedImage vida;

    //Fuentes

    public static Font fuenteGrand;
    public static Font fuenteMed;

    public static Font fuentePeque;

    //sonidos

    public static Clip musicaFondo, explociones, playerMuere, laser, enemigoLaser;

    //botones

    public static BufferedImage botonRed, botonBlue, botonGreen, botonYellow;

    //Fondo

    public static BufferedImage fondo;


    public static void init()  {
        //jugador
        player = CargarRecursos.CargaImagen("/Recourses/PNG/playerShip3_red.png");
        fuego = CargarRecursos.CargaImagen("/Recourses/PNG/Effects/fire01.png");
        //lasers
        blueLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserBlue06.png");
        redLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserGreen06.png");
        greenLaser = CargarRecursos.CargaImagen("/Recourses/PNG/Lasers/laserRed04.png");

        //Asteroiodes
        for(int i = 0; i < big.length; i++) {
            big[i]= CargarRecursos.CargaImagen("/Recourses/PNG/Meteors/big" +(i + 1)+ ".png" );
        }
        for(int i = 0; i < med.length; i++) {
            med[i]= CargarRecursos.CargaImagen("/Recourses/PNG/Meteors/med" +(i + 1)+ ".png" );
        }
        for(int i = 0; i < small.length; i++) {
            small[i]= CargarRecursos.CargaImagen("/Recourses/PNG/Meteors/small" +(i + 1)+ ".png" );
        }
        for(int i = 0; i < tiny.length; i++) {
            tiny[i]= CargarRecursos.CargaImagen("/Recourses/PNG/Meteors/tiny" +(i + 1)+ ".png" );
        }
        // explociones

        for(int i = 0; i < exp.length; i++){

            exp[i]= CargarRecursos.CargaImagen("/Recourses/PNG/explocions/"+ i + ".png" );
        }
        //enemigos
        enemigo = CargarRecursos.CargaImagen("/Recourses/PNG/ufoYellow.png");

        // vidas y numeros
        vida = CargarRecursos.CargaImagen("/Recourses/PNG/UI/vidas/playerLife2_red.png");

        for(int i = 0; i < numero.length; i++){
            numero[i]= CargarRecursos.CargaImagen("/Recourses/PNG/numeros/" + i + ".png" );
        }
        //fuente

        fuenteGrand = CargarRecursos.cargarFuente("/Recourses/Bonus/kenvector_future.ttf", 42);
        fuenteMed = CargarRecursos.cargarFuente("/Recourses/Bonus/kenvector_future.ttf", 20);
        fuentePeque = CargarRecursos.cargarFuente("/Recourses/Bonus/kenvector_future.ttf", 12);

        //sonidos

        musicaFondo = CargarRecursos.cargarSonido("/Recourses/Bonus/Instrumental-electronic-music.wav");
        explociones = CargarRecursos.cargarSonido("/Recourses/Bonus/sfx_twoTone.wav");
        playerMuere = CargarRecursos.cargarSonido("/Recourses/Bonus/sfx_lose.wav");
        laser = CargarRecursos.cargarSonido("/Recourses/Bonus/sfx_laser1.wav");
        enemigoLaser = CargarRecursos.cargarSonido("/Recourses/Bonus/sfx_zap.wav");

        //botones

        botonRed = CargarRecursos.CargaImagen("/Recourses/PNG/UI/buttonRed.png");
        botonGreen = CargarRecursos.CargaImagen("/Recourses/PNG/UI/buttonGreen.png");
        botonBlue = CargarRecursos.CargaImagen("/Recourses/PNG/UI/buttonBlue.png");
        botonYellow = CargarRecursos.CargaImagen("/Recourses/PNG/UI/buttonYellow.png");

        // fondo

        fondo = CargarRecursos.CargaImagen("/Recourses/Backgrounds/purple.png");

    }
}
