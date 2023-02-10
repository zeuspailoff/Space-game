package Estados;

import java.awt.*;

public abstract class Estado {

    public static Estado estadoActual = null;

    public static Estado getEstadoActual() {
        return estadoActual;
    }

    public static void cambiarEstado(Estado newEstado) {
        estadoActual = newEstado;
    }

    public abstract void actualizar();
    public abstract void draw(Graphics graphics);
}
