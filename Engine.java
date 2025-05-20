public class Engine {
    Size size;
    double horsePower;
    public Engine(Size size, double horsePower) {
        this.size = size;
        this.horsePower = horsePower;
    }

    public void setSize(int index) {
        size = Size.values()[index - 1];
    }

    public Size getSize() {
        return size;
    }

    public void setHorsePower(double input) {
        horsePower = input;
    }

    public double getHorsePower() {
        return horsePower;
    }
}
