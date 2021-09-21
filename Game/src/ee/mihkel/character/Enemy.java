package ee.mihkel.character;

import ee.mihkel.World;

public class Enemy extends Character {
    private EnemyType enemyType;

    public Enemy(World world) {
        super(world, 'Z');
        enemyType = EnemyType.getRandomEnemyType();
        setHealth(determineEnemyType());

    }

    public void generateRandomCoordinates(World world) {
        enemyType = EnemyType.getRandomEnemyType();
        setHealth(determineEnemyType());
        generateCoordinates(world);
        setVisible(true);
    }

    public double determineEnemyType() {
        switch (enemyType) {
            case RAT:
                return 2;
            case CAT:
                return 3;
            case DOG:
                return 4;
            case HORSE:
                return 5;
            case DRAGON:
                return 6;
            case WIZARD:
                return 7;
            default:
                return 1;
        }
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }
}
