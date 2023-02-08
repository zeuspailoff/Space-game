package Math;

public class Vector2d {
    private double x, y;


    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2d(){
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
