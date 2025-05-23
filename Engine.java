/*
 * Project: Lab 1
 * Purpose Details: Create an engine for a motorcycle
 * Course: IST 242
 * Author: Karis Jones
 * Date Developed: 5/19
 * Last Date Changed: 5/23
 * Rev: 5/23
 */

public class Engine {

    /*
     * The engine's size and horsepower
     */
    Size size;
    double horsePower;

    /*
       An engine object
       @param frameType: The size enum value the frame should have
       @param horsePower: The amount of horsepower the engine should have
   */
    public Engine(Size size, double horsePower) {
        this.size = size;
        this.horsePower = horsePower;
    }

    /*
        Sets the engine's size
        @param input: The size enum value to be used
    */
    public void setSize(Size input) { size = input; }

    /*
        Returns the value of the engine's size
    */
    public Size getSize() { return size; }

    /*
        Sets the engine's material
        @param input: The double to be used
    */
    public void setHorsePower(double input) {
        horsePower = input;
    }

    /*
        Returns the value of the engine's horsepower
    */
    public double getHorsePower() {
        return horsePower;
    }
}
