package ee.mihkel;

public class Player extends Character {
    private Direction direction;

    public Player() {
        super('X');
        this.direction = Direction.UP;
    }

    public void move(String input, World world) {
        switch (input) {
            case "w":
                this.direction = Direction.UP;
                break;
            case "a":
                this.direction = Direction.LEFT;
                break;
            case "s":
                this.direction = Direction.DOWN;
                break;
            case "d":
                this.direction = Direction.RIGHT;
                break;
        }
        switch (direction) {
            case UP:
                if (getyCoord() > 1) {
                    changeyCoord(false);
                }
                break;
            case LEFT:
                if (getxCoord() > 1) {
                    changexCoord(false);
                }
                break;
            case DOWN:
                if (getyCoord() < world.getHeight()-2) { // 1,2,3    3
                    changeyCoord(true);
                }
                break;
            case RIGHT:
                if (getxCoord() < world.getWidth()-2) {
                    changexCoord(true);
                }
                break;
        }
    }
}
