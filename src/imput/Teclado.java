package imput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    private boolean[] keys = new boolean[256];

    public static boolean UP, LEFT, RIGHT, SHOOT;

    public Teclado() {

        UP = false;
        LEFT = false;
        RIGHT = false;
        SHOOT = false;

    }
    public void actualizar(){
        UP = keys[KeyEvent.VK_W];
        LEFT = keys[KeyEvent.VK_A];
        RIGHT = keys[KeyEvent.VK_D];
        SHOOT = keys[KeyEvent.VK_UP];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
}
