package ee.mihkel.character;

import ee.mihkel.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Character {
    private int xCoord;
    private int yCoord;
    private final char symbol;
    private boolean isVisible;

    // 5   1 ja 5
    // 2-4
    // 0 4

    public Character(World world, char symbol) {
        generateCoordinates(world);
        this.symbol = symbol;
        this.isVisible = true;
    }

    protected void generateCoordinates(World world) {
        Random random = new Random();
        this.xCoord = random.nextInt(world.getWidth()-2)+1;
        this.yCoord = random.nextInt(world.getHeight()-2)+1;
        this.checkCoordinaatesUniqueness(world);
    }

    private void checkCoordinaatesUniqueness(World world) {
        List<Character> charactersWithoutThis = world.getCharacterList().stream()
                .filter(c -> c.symbol != this.symbol)
                .collect(Collectors.toList());

//        List<Character> charactersWithoutThis2 = new ArrayList<>();
//        for (Character c:world.getCharacterList()) {
//            if (c.symbol != this.symbol) {
//                charactersWithoutThis2.add(c);
//            }
//        }

        for (Character c: charactersWithoutThis) {
            if (c.xCoord == this.xCoord && c.yCoord == this.yCoord) {
                generateCoordinates(world);
            }
        }
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void changexCoord(boolean isIncrease) {
        if (isIncrease) {
            this.xCoord++;
        } else {
            this.xCoord--;
        }
    }

    public void changeyCoord(boolean isIncrease) {
        if (isIncrease) {
            this.yCoord++;
        } else {
            this.yCoord--;
        }
    }
}
