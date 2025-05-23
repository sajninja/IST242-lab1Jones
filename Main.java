/*
 * Project: Lab 1
 * Purpose Details: Making some motorcycles
 * Course: IST 242
 * Author: Karis Jones
 * Date Developed: 5/19
 * Last Date Changed: 5/23
 * Rev: 5/23
 */

public class Main {
    public static void main(String[] args) {
        System.out.println();
        Motorcycle purpleBike = new Motorcycle("green", new Frame(Material.STEEL, Size.HUGE), "flowers", new Engine(Size.SMALL, 150.0), new Wheel(20), new Wheel(20), Fuel.ROCKET_FUEL);
        purpleBike.setColor("purple");
        purpleBike.frame.setFrameMaterial(Material.ALUMINUM);
        purpleBike.frame.setSize(Size.STANDARD);
        purpleBike.setDecal("flowers");
        purpleBike.engine.setSize(Size.SMALL);
        purpleBike.engine.setHorsePower(150.0);
        purpleBike.frontWheel.setLength(15);
        purpleBike.backWheel.setLength(10);
        purpleBike.setFuel(Fuel.ROCKET_FUEL);
        printResults(purpleBike);

        Motorcycle yellowBike = new Motorcycle("highlighter yellow", new Frame(Material.STEEL, Size.HUGE), "bananas", new Engine(Size.BIG, 300), new Wheel(50), new Wheel(40), Fuel.DIESEL);
        yellowBike.setColor("purple");
        yellowBike.frame.setFrameMaterial(Material.STEEL);
        yellowBike.frame.setSize(Size.HUGE);
        yellowBike.setDecal("lemons");
        yellowBike.engine.setSize(Size.SMALL);
        yellowBike.engine.setHorsePower(150.0);
        yellowBike.frontWheel.setLength(20);
        yellowBike.backWheel.setLength(25);
        yellowBike.setFuel(Fuel.GASOLINE);
        printResults(yellowBike);

        Motorcycle whiteBike = new Motorcycle("midnight black", new Frame(Material.PLASTIC, Size.TINY), "bats", new Engine(Size.HUGE, 400), new Wheel(10), new Wheel(10), Fuel.FISSILE_FUEL);
        whiteBike.setColor("snow white");
        whiteBike.frame.setFrameMaterial(Material.PLASTIC);
        whiteBike.frame.setSize(Size.TINY);
        whiteBike.setDecal("snowballs");
        whiteBike.engine.setSize(Size.BIG);
        whiteBike.engine.setHorsePower(350.0);
        whiteBike.frontWheel.setLength(5);
        whiteBike.backWheel.setLength(5);
        whiteBike.setFuel(Fuel.VEGETABLE_OIL);
        printResults(whiteBike);
    }

    /*
        Takes the motorcycle object as inputs and prints its data
        @param inputMotorcycle: The motorcycle object to be described
    */
    private static void printResults(Motorcycle inputMotorcycle) {
        System.out.println("This is a " + inputMotorcycle.frame.getSize() + ", " + inputMotorcycle.getColor().toUpperCase() + " motorcycle made of " + inputMotorcycle.frame.getFrameMaterial() + ".");
        System.out.println("It has a " + inputMotorcycle.engine.getSize() + " engine with " + inputMotorcycle.engine.getHorsePower() + " horsepower. It's fueled by " + inputMotorcycle.getFuel() + ".");
        System.out.println("Its front wheel is " + inputMotorcycle.frontWheel.getLength() + " inches and its back wheel is " + inputMotorcycle.backWheel.getLength() + " inches.");
        if (!inputMotorcycle.decal.isEmpty()) {
            System.out.println("It is decorated with " + inputMotorcycle.getDecal().toUpperCase() + ".");
        }
        System.out.println();
    }
}
