public abstract class GameObject {
    public Vector2 size;
    public Vector2 position;
    public Vector2 velocity;

    public Vector2 getPos() {
        return position;
    }

    public void setPos(Vector2 newPos) {
        this.position = newPos;
    }

    public Vector2 getSize() {
        return size;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 input) {
        velocity = input;
    }

    public void setSize(Vector2 input) {
        size = input;
    }
}
