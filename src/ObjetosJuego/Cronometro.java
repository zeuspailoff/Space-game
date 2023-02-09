package ObjetosJuego;

public class Cronometro {
    private long delta, lastTime;
    private long time;
    private boolean activo;

    public Cronometro() {
        delta = 0;
        lastTime = 0;
        activo = false;
    }

    public void encendido (long time){
        activo = true;
        this.time = time;
    }

    public  void actualizar() {
        if(activo){
            delta += System.currentTimeMillis() - lastTime;
        }
        if(delta >= time){
            activo = false;
            delta = 0;
        }
        lastTime = System.currentTimeMillis();
    }

    public boolean isActivo() {
        return activo;
    }
}
