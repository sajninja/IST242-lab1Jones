/*
 * Project: Lab 1
 * Purpose Details: Create a wheel for a motorcycle
 * Course: IST 242
 * Author: Karis Jones
 * Date Developed: 5/19
 * Last Date Changed: 5/23
 * Rev: 5/23
 */

public class Wheel {
    /*
     * The length of this wheel
     */
    double length;

    /*
       A wheel object
       @param length: The length the wheel should have
   */
    public Wheel(double length) {
        this.length = length;
    }

    /*
        Sets the wheel's length from a double
        @param input: The double to be used
    */
    public void setLength(double input) {
        this.length = input;
    }

    /*
        Returns the value of the wheel's length
    */
    public double getLength() {
        return length;
    }
}
