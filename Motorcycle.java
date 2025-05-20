public class Motorcycle {

    String color;
    String decal;
    Frame frame;
    Engine engine;
    Wheel frontWheel;
    Wheel backWheel;
    Fuel fuel;

    public Motorcycle(String color, Frame frame, String decal, Engine engine, Wheel frontWheel, Wheel backWheel, Fuel fuel) {
        this.color = color;
        this.frame = frame;
        this.decal = decal;
        this.engine = engine;
        this.frontWheel = frontWheel;
        this.backWheel = backWheel;
        this.fuel = fuel;
    }

    public void setColor(String input) { color = input; }

    public String getColor() { return color; }

    public void setDecal(String input) { decal = input; }

    public String getDecal() { return decal; }

    public void setFuel(int index) { fuel = Fuel.values()[index - 1]; }

    public Fuel getFuel() { return fuel; }

    public void printResults() {
        System.out.println("\nYou created a " + frame.getFrameType() + ", " + getColor().toUpperCase() + " motorcycle made of " + frame.getFrameMaterial() + ".");
        System.out.println("It has a " + engine.getSize() + " engine with " + engine.getHorsePower() + " horsepower. It's fueled by " + getFuel() + ".");
        System.out.println("Its front wheel is " + frontWheel.getLength() + " inches and its back wheel is " + backWheel.getLength() + " inches.");
        if (!decal.isEmpty()) {
            System.out.println("Your motorcycle is decorated with " + getDecal().toUpperCase() + ".");
        }
        System.out.println();

        if (frame.getFrameMaterial() == Material.GLASS) {
            System.out.println("The moment you sit on your new motorcycle, its glass frame shatters instantly.");
            return;
        }
        if (frame.getFrameMaterial() == Material.PLASTIC) {
            System.out.println("Your motorcycle warps slightly when you sit on it. The engine melts the frame into a pile of " + color.toUpperCase() + " goop.");
            return;
        }
        if (fuel == Fuel.FISSILE_FUEL) {
            System.out.println("The air tastes like pennies and looks like radio static when you fuel your motorcycle.");
            return;
        }
        if (fuel == Fuel.ROCKET_FUEL) {
            System.out.println("Your motorcycle flies into orbit when you try driving it. Oh well.");
            return;
        }
        if (fuel == Fuel.VEGETABLE_OIL) {
            System.out.println("Your motorcycle's engine never ignites. It must not like veggies.");
            return;
        }
        if (Math.abs(frontWheel.getLength() - backWheel.getLength()) > 50) {
            System.out.println("The wheels differ too much in size, you can't seem to maintain your balance.");
            return;
        }
        if (engine.getHorsePower() > 350) {
            System.out.println("Your motorcycle has too much horsepower. You get pulled over by the police and your bike gets confiscated.");
            return;
        }
        if (engine.getHorsePower() < 15) {
            System.out.println("Your motorcycle has less horsepower than an actual horse. A bicycle would be faster.");
            return;
        }
        if (!decal.isEmpty()) {
            System.out.println("Your sweet decal attracts attention and turns heads your way.");
            return;
        }
    }

}
