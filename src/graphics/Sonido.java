package graphics;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sonido {
    private Clip clip;
    private FloatControl volumen;

    public Sonido(Clip clip) {
        this.clip = clip;
        volumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    }

    public void play(){

        clip.setFramePosition(0);
        clip.start();
    }

    public void loop(){
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void parar(){
        clip.stop();
    }

    public int getFramePosition(){
        return clip.getFramePosition();
    }

    public void cambiarVolumen(float valor){
        volumen.setValue(valor);
    }
}
