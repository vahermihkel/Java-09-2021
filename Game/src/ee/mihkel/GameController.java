package ee.mihkel;

import ee.mihkel.character.Enemy;
import ee.mihkel.character.Player;
import ee.mihkel.character.QuestMaster;
import ee.mihkel.item.*;

import java.util.Random;
import java.util.Scanner;

public abstract class GameController {
    protected static void checkPlayerInteractions(World world, Player player, Enemy enemy, QuestMaster questMaster, Dagger dagger, Hammer hammer, Sword sword, Teleporter teleporter, Scanner scanner) throws GameOverException {
        GameController.checkIfPlayerAndEnemyMet(world, player, enemy, scanner);
        GameController.checkIfPlayerAndQuestMasterMet(world, player, enemy, questMaster);

        GameController.checkIfPlayerTakesItem(player, dagger);
        GameController.checkIfPlayerTakesItem(player, hammer);
        GameController.checkIfPlayerTakesItem(player, sword);
        GameController.checkIfPlayerTakesItem(player, teleporter);
    }

    private static void checkIfPlayerAndEnemyMet(World world, Player player, Enemy enemy, Scanner scanner) throws GameOverException {
        if (player.getxCoord() == enemy.getxCoord() && player.getyCoord() == enemy.getyCoord() && enemy.isVisible()) {
            enemy.setVisible(false);
            player.showItems();
            if (player.hasItems()) {
                GameController.chooseItem(world, player, enemy, scanner);
            } else {
                System.out.println("Esemeid ei ole millega võidelda, mine korja!");
            }
        }
    }

    private static void checkIfPlayerAndQuestMasterMet(World world, Player player, Enemy enemy, QuestMaster questMaster) {
        if (player.getxCoord() == questMaster.getxCoord() && player.getyCoord() == questMaster.getyCoord()) {
            enemy.generateRandomCoordinates(world);
            questMaster.setVisible(false);
        } else if (!questMaster.isVisible()) {
            questMaster.setVisible(true);
        }
    }

    private static void chooseItem(World world, Player player, Enemy enemy, Scanner scanner) throws GameOverException {
        String input;
        System.out.println("Ütle number millist eset tahad: ");
        Item item = null;
        while (item == null) {
            try {
                input = scanner.nextLine();
                item = player.getItem(Integer.parseInt(input));
                //item.decreaseDurability();
                player.useItem(item);
                System.out.println("Valisid eseme: " + item.getName());
                if (item.getName().equals("Teleporteerija")) {
                    player.generateRandomCoordinates(world);
                } else {
                    GameController.getFightNumber(player, enemy, scanner, item);
                }
            } catch (NumberFormatException e) {
                System.out.println("Sisestasid numbri asemel muu sümboli, sisesta uuesti: ");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sisestasid liiga suure või väikse numbri, sisesta uuesti: ");
            }
        }
    }

    private static void getFightNumber(Player player, Enemy enemy, Scanner scanner, Item item) throws GameOverException {
        String input;// while (!(player.getHealth() <= 0 || enemy.getHealth() <= 0)) {
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println("Ütle üks number 1-3");
            int userNumber = 0;
            while (userNumber == 0) {
                try {
                    input = scanner.nextLine();
                    userNumber = Integer.parseInt(input);
                    // while userNumber == 0
                    // sisestasid numbri asemel mingi muu sümboli
                    // try catch
                    GameController.fightWithEnemy(player, enemy, item, userNumber);
                } catch (TooBigOrSmallNumberException e) {
                    System.out.println("Sisestasid liiga suure või väikse numbri, sisesta uuesti: ");
                } catch (NumberFormatException e) {
                    System.out.println("Sisestasid numbri asemel muu sümboli, sisesta uuesti: ");
                }
            }
        }
        if (enemy.getHealth() <= 0) {
            player.addKilledEnemy(enemy.getEnemyType());
            Item.setTotalStrength(enemy.getHealth());
        } else if (player.getHealth() <= 0 ) {
            throw new GameOverException();
        }
    }

    private static void fightWithEnemy(Player player, Enemy enemy, Item item, int userNumber) throws TooBigOrSmallNumberException {
        if (userNumber < 1 || userNumber > 3) {
            throw new TooBigOrSmallNumberException();
        }
        Random random = new Random();
        int enemyNumber = random.nextInt(3)+1;
        if (userNumber == enemyNumber) {
            enemy.takeHealth(item.getStrength());
            System.out.println("Vaenlane kaotas elu, elusid alles: " + enemy.getHealth());
        } else {
            player.takeHealth(enemy.determineEnemyType());
            System.out.println("Mängija kaotas elu, elusid alles: " + player.getHealth());
        }
    }

    private static void checkIfPlayerTakesItem(Player player, Item item) {
        if (player.getxCoord() == item.getxCoord() && player.getyCoord() == item.getyCoord()) {
            player.addItem(item);
        }
    }

    protected static void endGame(Player player) {
        System.out.println("Mäng läbi!");
        player.showKilledEnemies();
        System.out.println("Kokku kogutud punkte: " + Item.getTotalStrength());
    }
}
