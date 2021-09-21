package ee.mihkel.character;

import java.util.Random;

public enum EnemyType {
    ANT, RAT, CAT, DOG, HORSE, DRAGON, WIZARD;

    public static EnemyType getRandomEnemyType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];

    //    return values()[(int)(Math.random()*values().length)];
    }
}
