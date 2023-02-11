package IO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PuntuacionFecha {
    private String fecha;
    private int puntos;

    public PuntuacionFecha(int puntos){
        this.puntos = puntos;

        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        fecha = sdf.format(today);

    }

    public PuntuacionFecha(){

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
