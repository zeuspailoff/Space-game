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
    public Vector2d add(Vector2d v) {
        return new Vector2d(x + v.getX(), y + v.getY());
    }
    public Vector2d escalar(double valor) {
        return new Vector2d(x*valor, y*valor);
    }
    public void limite(double valor) {
        if(x > valor){
            x = valor;
        }
        if (x < -valor){
            x = -valor;
        }
        if(y > valor){
            y = valor;
        }
        if (y < -valor){
            y = -valor;
        }
    }
    public Vector2d normalizar(){
        return new Vector2d(x/ getMagnitude(), y/ getMagnitude());
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
