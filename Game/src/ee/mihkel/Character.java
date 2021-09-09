package ee.mihkel;

public class Character {
    private int xCoord;
    private int yCoord;
    private final char symbol;

    public Character(char symbol) {
        this.xCoord = 2;
        this.yCoord = 2;
        this.symbol = symbol;
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
