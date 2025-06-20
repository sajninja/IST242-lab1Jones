public class Player extends GameObject {
    public final double ACCELERATION = 2;
    public static final int PLAYER_SCALE = 3;
    public static final int PLAYER_WIDTH = 16 * PLAYER_SCALE;
    public static final int PLAYER_HEIGHT = 16 * PLAYER_SCALE;
    public static final int PLAYER_SPEED = 1;
    public int health;
    public int shield;
    public int laserDamage;

    public Player(Vector2 position, Vector2 size, Vector2 velocity) {
        this.position = position;
        this.size = size;
        this.velocity = velocity;
    }

    public void changeHealth(int input) {
        health += input;
        if (health > 100) health = 100;
        if (health < 0) health = 0;
    }

    public void changeShield(int input) {
        shield += input;
        if (shield > 100) shield = 100;
        if (shield < 0) shield = 0;
    }

    public void changeLaser(int input) {
        laserDamage += input;
    }
}
