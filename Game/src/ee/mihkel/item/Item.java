package ee.mihkel.item;

import ee.mihkel.World;
import ee.mihkel.WorldObject;

import java.util.Random;

public abstract class Item implements WorldObject {
    private int xCoord;
    private int yCoord;
    private char symbol;
    private double strength;
    private int durability;
    private String name;
    private ItemType itemType;
    private int level;
    private static double totalStrength;

    public Item(World world, double strength, String name) {
        generateCoordinates(world);
        this.symbol = 'I';
        this.strength = strength;
        this.name = name;
        this.itemType = ItemType.BRONZE;
        this.level = 0;
    }

    // Constructor Overloading
    public Item(World world, String name) {
        generateCoordinates(world);
        this.symbol = 'I';
        this.name = name;
        this.itemType = ItemType.BRONZE;
    }

    @Override
    public void generateCoordinates(World world) {
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

    public abstract void reboost();

    public void increaseDurability() {
        durability++;
    }

    protected void setDurability(int durability) {
        this.durability = durability;
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
