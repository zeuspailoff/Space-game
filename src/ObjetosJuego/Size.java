package ObjetosJuego;

import graphics.Assets;

import java.awt.image.BufferedImage;

public enum Size {
    BIG(2, Assets.med), MED(2, Assets.small), SMALL(2, Assets.tiny), TINY(0, null);

    public int cantidad;
    public BufferedImage[] textura;

    private Size(int cantidad, BufferedImage[] textura) {

        this.cantidad = cantidad;
        this.textura = textura;
    }
}
