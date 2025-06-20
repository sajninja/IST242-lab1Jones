import java.awt.*;

public class ThrusterParticles extends GameObject {
    public Color color;

    public void setColor(int r, int g, int b) {
        color = new Color(r, g, b);
    }

    public void setAlpha(int a) {
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
    }
}
