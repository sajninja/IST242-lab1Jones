import java.awt.*;

public class Star extends GameObject {
    private Color color;
    public boolean sped;

    public Star(Vector2 size, Vector2 position, Vector2 velocity, Color color, boolean sped) {
        this.size = size;
        this.position = position;
        this.velocity = velocity;
        this.color = color;
        this.sped = sped;
    }

    public Color getColor() {
        return color;
    }

}
