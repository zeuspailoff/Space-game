package graphics;

import Math.Vector2d;
import java.awt.*;

public class Texto {

    public static void drawTexto(Graphics graphics, String text, Vector2d pos, boolean center, Color color, Font fuente ){
        graphics.setColor(color);
        graphics.setFont(fuente);
        Vector2d positio = new Vector2d(pos.getX(), pos.getY());

        if(center){
            FontMetrics fm = graphics.getFontMetrics();
            positio.setX(positio.getX() - fm.stringWidth(text) / 2);
            positio.setY(positio.getY() - fm.getHeight() / 2);
        }

        graphics.drawString(text, (int)positio.getX(), (int)positio.getY());
    }
}
