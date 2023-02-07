import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

public static final int WIDTH = 900, HEIGHT = 700;

    public Ventana() {
        setTitle("Space game ");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(getIconImage());

        setVisible(true);
    }
    public Image getIconImage() {

        return Toolkit.getDefaultToolkit()
                .getImage(ClassLoader.getSystemResource("Recourses/PNG/playerShip3_blue.png"));
    }

}
