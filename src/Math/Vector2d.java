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

    public Vector2d subtrat(Vector2d v) {
        return new Vector2d(x - v.getX(), y - v.getY());
    }

    public Vector2d escalar(double valor) {
        return new Vector2d(x*valor, y*valor);
    }
    public Vector2d limite(double valor) {
       if(getMagnitude() > valor){
           return this.normalizar().escalar(valor);
       }
       return this;
    }
    public Vector2d normalizar(){

        double magnitude = getMagnitude();

        return new Vector2d(x/ magnitude, y/ magnitude);
    }

    public double getMagnitude() {

        return Math.sqrt(x * x + y * y);
    }

    public Vector2d setDireccion(double angulo){
        double magnitude = getMagnitude();

        return new Vector2d(Math.cos(angulo)* magnitude, Math.sin(angulo)* magnitude);
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
