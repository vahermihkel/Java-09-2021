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

    public Item(World world, double strength, int durability, String name) {
        generateCoordinates(world);
        this.symbol = 'I';
        this.strength = strength;
        this.durability = durability;
        this.name = name;
    }

    // Constructor Overloading
    public Item(World world, int durability, String name) {
        generateCoordinates(world);
        this.symbol = 'I';
        this.durability = durability;
        this.name = name;
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
        return strength;
    }

    public int getDurability() {
        return durability;
    }

    public void decreaseDurability() {
        durability--;
    }

    public void increaseDurability() {
        durability++;
    }

    public String getName() {
        return name;
    }
}
