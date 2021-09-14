package ee.mihkel.item;

import ee.mihkel.World;

import java.util.Random;

public abstract class Item {
    private int xCoord;
    private int yCoord;
    private char symbol;
    private double strength;
    private int durability;

    public Item(World world, double strength, int durability) {
        generateCoordinates(world);
        this.symbol = 'I';
        this.strength = strength;
        this.durability = durability;
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
}
