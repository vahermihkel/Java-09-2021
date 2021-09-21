package ee.mihkel.item;

import ee.mihkel.World;

import java.util.Random;

public abstract class Item {
    private int xCoord;
    private int yCoord;
    private char symbol;
    private double strength;
    private int durability;
    private String name;
    private ItemType itemType;
    private int level;
    private static double totalStrength;

    public Item(World world, double strength, int durability, String name) {
        generateCoordinates(world);
        this.symbol = 'I';
        this.strength = strength;
        this.durability = durability;
        this.name = name;
        this.itemType = ItemType.BRONZE;
        this.level = 0;
    }

    // Constructor Overloading
    public Item(World world, int durability, String name) {
        generateCoordinates(world);
        this.symbol = 'I';
        this.durability = durability;
        this.name = name;
        this.itemType = ItemType.BRONZE;
    }

    private void generateCoordinates(World world) {
        Random random = new Random();
        this.xCoord = random.nextInt(world.getWidth()-2)+1;
        this.yCoord = random.nextInt(world.getHeight()-2)+1;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public char getSymbol() {
        return symbol;
    }

    public double getStrength() {
        switch (itemType) {
            case SILVER:
                return strength * 1.5;
            case GOLD:
                return strength * 2;
            case PLATINUM:
                return strength * 2.5;
            default:
                return strength;
        }
    }

    public int getDurability() {
        return durability;
    }

    public void decreaseDurability() {
        durability--;
        level++;
        checkItemType();
    }

    private void checkItemType() {
        int newLevel = level/2;
        switch (newLevel) {
            case 0:
                this.itemType = ItemType.BRONZE;
                break;
            case 1:
                this.itemType = ItemType.SILVER;
                break;
            case 2:
                this.itemType = ItemType.GOLD;
                break;
            case 3:
                this.itemType = ItemType.PLATINUM;
                break;
        }
    }

    public void increaseDurability() {
        durability++;
    }

    public String getName() {
        return name;
    }

    public static double getTotalStrength() {
        return totalStrength;
    }

    public static void setTotalStrength(double totalStrength) {
        Item.totalStrength += totalStrength;
    }
}
