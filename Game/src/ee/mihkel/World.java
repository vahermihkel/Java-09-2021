package ee.mihkel;

import ee.mihkel.character.Character;
import ee.mihkel.item.Item;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final int height;
    private final int width;
    private final List<Character> characterList = new ArrayList<>();
    private final List<Item> itemList = new ArrayList<>();

    public World(int _height, int _width) {
        this.height = _height;
        this.width = _width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<Character> getCharacterList() {
        return characterList;
    }

    public void addCharacter(Character character) {
        this.characterList.add(character);
    }

    public void addItem(Item item) {
        this.itemList.add(item);
    }

    public void printMap() {
        char symbol;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || y == height-1) { // VÕI - ükskõik kumb on tõene, siis läheb if-i sisse
                    symbol = '-';
                } else if (x == 0 || x == width-1) {
                    symbol = '|';
                } else {
                    symbol = ' ';
                }
                for (Item i: itemList) {
                    if (i.getyCoord() == y && i.getxCoord() == x) {
                        symbol = i.getSymbol();
                    }
                }
                for (Character c: characterList) {
                    if (c.getyCoord() == y && c.getxCoord() == x && c.isVisible()) {
                        symbol = c.getSymbol();
                    }
                }
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
