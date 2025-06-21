/*
 * Project: Lab 1
 * Purpose Details: Create a frame for a motorcycle
 * Course: IST 242
 * Author: Karis Jones
 * Date Developed: 5/19
 * Last Date Changed: 5/23
 * Rev: 5/23
 */

public class Frame {

    /*
     * The engine's material and frame type
     */
    private Material material;
    private Size frameType;

    /*
       A frame object
       @param material: The material enum value the frame should have
       @param frameType: The size enum value the frame should have
   */
    public Frame(Material material, Size frameType) {
        this.material = material;
        this.frameType = frameType;
    }

    /*
        Sets the frame's size
        @param input: The size enum value to be used
    */
    public void setSize(Size input) {
        frameType = input;
    }

    /*
        Returns the value of the frame's size
    */
    public Size getSize() { return frameType; }

    /*
        Sets the frame's material
        @param input: The material enum value to be used
    */
    public void setFrameMaterial(Material input) {
        material = input;
    }

    /*
        Returns the value of the frame's material
    */
    public Material getFrameMaterial() { return material; }
}
