package ee.mihkel.character;

import ee.mihkel.World;
import ee.mihkel.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character  {
    private Direction direction;
    private final List<Item> inventory = new ArrayList<>();

    public Player(World world) {
        super(world, 'X'); // parent classi constructor
        this.direction = Direction.UP;
    }

    public void addItem(Item item) {
        this.inventory.add(item);
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
