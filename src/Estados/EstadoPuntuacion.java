package Estados;

import IO.JsonParser;
import IO.PuntuacionFecha;
import ObjetosJuego.Constantes;
import UI.Action;
import Math.Vector2d;
import UI.Boton;
import graphics.Assets;
import graphics.Texto;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class EstadoPuntuacion extends Estado{

    private Boton botonRetorno;
    private PriorityQueue<PuntuacionFecha> puntuacionMasAlt;
    private Comparator<PuntuacionFecha> puntuacionComparar;
    private PuntuacionFecha[] auxArray;

    public EstadoPuntuacion(){
        botonRetorno = new Boton(
                Assets.botonYellow,
                Assets.botonYellow,
                Assets.botonYellow.getHeight(),
                Constantes.HEIGHT - Assets.botonYellow.getHeight() * 2,
                Constantes.RETURN,
                new Action() {
                    @Override
                    public void doAction() {
                        Estado.cambiarEstado(new EstadoMenu());
                    }
                }
        );
        puntuacionComparar = new Comparator<PuntuacionFecha>() {

            @Override
            public int compare(PuntuacionFecha o1, PuntuacionFecha o2) {
                return o1.getPuntos() < o2.getPuntos() ? -1 : o1.getPuntos() > o2.getPuntos() ? 1 : 0;
            }
        };
        puntuacionMasAlt = new PriorityQueue<PuntuacionFecha>(10, puntuacionComparar );
        try {
            ArrayList<PuntuacionFecha>dataList = JsonParser.readFile();
            for(PuntuacionFecha d : dataList) {
                puntuacionMasAlt.add(d);
            }

            while (puntuacionMasAlt.size() > 10){
                puntuacionMasAlt.poll();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actualizar() {
        botonRetorno.actualizar();
    }

    @Override
    public void draw(Graphics graphics) {
        botonRetorno.draw(graphics);

        auxArray = puntuacionMasAlt.toArray(new PuntuacionFecha[puntuacionMasAlt.size()]);

        Arrays.sort(auxArray, puntuacionComparar);

        Vector2d puntuacionPos = new Vector2d(
                Constantes.WIDTH / 2 + 200,
                100
        );
        Vector2d fechaPos = new Vector2d(
                Constantes.WIDTH / 2 - 200,
                100
        );

        Texto.drawTexto(graphics, Constantes.SCORE, puntuacionPos, true, Color.RED, Assets.fuenteGrand);
        Texto.drawTexto(graphics, Constantes.DATE, fechaPos, true, Color.RED, Assets.fuenteGrand);

        puntuacionPos.setY( puntuacionPos.getY() + 40);
        fechaPos.setY(fechaPos.getY() + 40);

        for(int i = auxArray.length - 1 ; i > -1; i--){
            PuntuacionFecha d = auxArray[i];

            Texto.drawTexto(graphics, Integer.toString(d.getPuntos()), puntuacionPos, true, Color.RED, Assets.fuenteMed);
            Texto.drawTexto(graphics, d.getFecha(), fechaPos, true, Color.RED, Assets.fuenteMed);

            puntuacionPos.setY( puntuacionPos.getY() + 40);
            fechaPos.setY( fechaPos.getY() + 40);
        }



    }
}
