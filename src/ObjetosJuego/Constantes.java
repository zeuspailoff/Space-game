package ObjetosJuego;

import javax.swing.filechooser.FileSystemView;

public class Constantes {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final double DELTAANGULO = 0.1;
    public static final int RATIODISPARO = 300;
    public static final double ASTEROID_VEL = 2.0;
    public static final int NODE_RADIO = 180;
    public static final double ENEMIGOS_MASA = 70;
    public static final double ENEMIGO_VELMAX = 3;
    public static long RATIO_DISPATO = 1000;
    public static final double LASER_VEL = 15.0;
    public static double UFO_ANGLE_RANGE = Math.PI / 2;
    public static final int ENEMIGO_PUNTOS = 40;
    public static final int ASTEROIDES_PUNTOS = 20;
    public static final long SPAWN_TIME = 3000;
    public static final long PARPADEO_TIME = 200;
    public static final double PLAYER_MAX_VEL = 8.0;
    public static final String PLAY = "DALE";

    public static final String EXIT = "HUYE";
    public static final long UFO_SPAWN_RATE = 10000;
    public static final int LOADING_BAR_WIDTH = 500;
    public static final int LOADING_BAR_HEIGHT = 50;
    public static final long GAME_OVER_TIME = 3000;
    public static final String RETURN = "VOLVER";
    public static final String HIGH_SCORES = "PUNTUACIONES";

    public static final String SCORE = "PUNTOS";
    public static final String DATE = "FECHA";


    public static final String SCORE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()
            +"D:\\proyectos open bootcamp\\java\\Game\\data.json";




}
