package UI;

import graphics.Assets;
import graphics.Texto;
import imput.Mause;

import java.awt.*;
import java.awt.image.BufferedImage;
import Math.Vector2d;

public class Boton {

    private BufferedImage mauseOutImage;
    private BufferedImage mauseInImage;
    private boolean mauseIn;
    private Rectangle boundingBox;
    private String text;
    private Action action;

    public Boton(
            BufferedImage mauseOutImage,
            BufferedImage mauseInImage,
            int x , int y,
            String text,
            Action action)
    {
        this.mauseOutImage = mauseOutImage;
        this.mauseInImage = mauseInImage;
        this.text = text;
        boundingBox = new Rectangle(x, y, mauseInImage.getWidth(), mauseInImage.getHeight());
        this.action = action;
    }

    public void actualizar() {

        if(boundingBox.contains(Mause.x, Mause.y)) {
            mauseIn = true;
        }else{
            mauseIn = false;
        }
        if(mauseIn && Mause.MLB){
            action.doAction();
        }

    }
    public void draw(Graphics graphics){

        if(mauseIn){
            graphics.drawImage(mauseInImage, boundingBox.x, boundingBox.y, null);
        }else{
            graphics.drawImage(mauseInImage, boundingBox.x, boundingBox.y, null);
        }

        Texto.drawTexto(
                graphics,
                text,
                new Vector2d(
                        boundingBox.getX() + boundingBox.getWidth() / 2 ,
                        boundingBox.getY() + boundingBox.getHeight() ),
                true,
                Color.BLACK,
                Assets.fuenteMed);

    }
}

