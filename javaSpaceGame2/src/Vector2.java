public class Vector2 {

    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(double input) {
        this.x = input;
        this.y = input;
    }

    public Vector2 getVector2() {
        return this;
    }

    public void setX(double input) {
        x = input;
    }

    public void setY(double input) {
        y = input;
    }
}
