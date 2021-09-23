package ee.mihkel.character;

import ee.mihkel.World;
import ee.mihkel.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Character  {
    private Direction direction;
    private final List<Item> inventory = new ArrayList<>();
    private final Map<EnemyType, Integer> killedEnemies = new HashMap<>();

    public Player(World world) {
        super(world, 'X'); // parent classi constructor
        this.direction = Direction.UP;
        setHealth(10.0);
    }

    public void addKilledEnemy(EnemyType enemyType) {
        System.out.println("Tapsid vaenlase");
        if (this.killedEnemies.containsKey(enemyType)) {
            this.killedEnemies.put(enemyType,killedEnemies.get(enemyType) + 1 );
        } else {
            this.killedEnemies.put(enemyType,1);
        }
    }

    public void showKilledEnemies() {
        if (this.killedEnemies.isEmpty()) {
            System.out.println("Tapetud vaenlasi ei ole");
        } else {
            System.out.println("Tapetud vaenlased:");
            this.killedEnemies.forEach((key,value)-> System.out.println(key + ": " + value));
        }
    }

    // Kui ei ole eset inventory-s siis lisa
    // Kui on ese juba inventory-s olemas, siis lisa kasutuskord
    // Item.increaseDurability();
    public void addItem(Item item) {
        if (!this.inventory.contains(item)) {
            item.reboost();
            this.inventory.add(item);
        } else {
            item.increaseDurability();
        }
    }

    public void useItem(Item item) {
        item.decreaseDurability();
        if (item.getDurability() == 0) {
            inventory.remove(item);
        }
    }
    // Kui kasutuskord läheb nulli, siis eemalda ese
    // Item.decreaseDurability() võiks käia siit
    // Kui durability on 0, siis eemalda listist


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

    public void showItems() {
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.println(i+1 + ". "+ item.getName() + "(" + item.getStrength() + "), kasutuskordi: " + item.getDurability());
        }
    }

    public Item getItem(int index) {
        return inventory.get(index-1);
    }

    public boolean hasItems() {
        return !this.inventory.isEmpty();
    }

    public void generateRandomCoordinates(World world) {
        generateCoordinates(world);
    }

    public void reboost() {
        setHealth(10);
    }
}
