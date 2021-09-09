package ee.mihkel;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final int height;
    private final int width;
    private final List<Player> characterList = new ArrayList<>();

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

    public void addCharacter(Player player) {
        this.characterList.add(player);
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
                for (Player p: characterList) {
                    if (p.getyCoord() == y && p.getxCoord() == x) {
                        symbol = p.getSymbol();
                    }
                }
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
