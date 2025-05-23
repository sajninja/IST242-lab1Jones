/*
 * Project: Lab 1
 * Purpose Details: Collect various values and objects to build a motorcycle
 * Course: IST 242
 * Author: Karis Jones
 * Date Developed: 5/19
 * Last Date Changed: 5/23
 * Rev: 5/23
 */

public class Motorcycle {

    /*
     * The motorcycle's color,
     * decorations,
     * frame object,
     * engine object,
     * front wheel object,
     * back wheel object,
     * and fuel enum value.
     */
    String color;
    String decal;
    Frame frame;
    Engine engine;
    Wheel frontWheel;
    Wheel backWheel;
    Fuel fuel;

    /*
        A motorcycle object
        @param color: The string for the bike's color
        @param frame: The frame object this bike's frame
        @param decal: The string for this bike's decoration
        @param engine: The bike's engine object
        @param frontWheel: The bike's front wheel object
        @param backWheel: The bike's back wheel object
        @param fuel: The bike's fuel enum
    */
    public Motorcycle(String color, Frame frame, String decal, Engine engine, Wheel frontWheel, Wheel backWheel, Fuel fuel) {
        this.color = color;
        this.frame = frame;
        this.decal = decal;
        this.engine = engine;
        this.frontWheel = frontWheel;
        this.backWheel = backWheel;
        this.fuel = fuel;
    }

    /*
        Sets the color from a string
        @param input: The string to be used
    */
    public void setColor(String input) { color = input; }

    /*
        Sets the color from a string
        @param input: The string to be used
    */
    public String getColor() { return color; }

    /*
        Sets the decal from a string
        @param input: The string to be used
    */
    public void setDecal(String input) { decal = input; }

    /*
        Returns the value of the decal string
    */
    public String getDecal() { return decal; }

    /*
        Sets the fuel from a string
        @param input: The fuel type value to be used
    */
    public void setFuel(Fuel input) { fuel = input; }

    /*
        Returns the fuel type value of the decal string
    */
    public Fuel getFuel() { return fuel; }



}
