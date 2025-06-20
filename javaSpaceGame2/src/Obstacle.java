public class Obstacle extends GameObject {
    public int health;
    public boolean hit;
    public int spritesheetIndex;

    public Obstacle(int health) {
        this.health = health;
    }

    public void changeHealth(int input) {
        health += input;
        if (health > 100) health = 100;
    }
}
