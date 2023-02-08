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

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2d setDireccion(double angulo){
        return new Vector2d(Math.cos(angulo)* getMagnitude(), Math.sin(angulo)* getMagnitude());
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
