package ee.mihkel;

import ee.mihkel.character.Enemy;
import ee.mihkel.character.Player;
import ee.mihkel.character.QuestMaster;
import ee.mihkel.item.Dagger;
import ee.mihkel.item.Hammer;
import ee.mihkel.item.Item;
import ee.mihkel.item.Sword;

import java.util.Scanner;

// encapsulation - private/public
// panen automaatselt kõik muutujad klassis private-ks
// ligipääsu teen üldiselt getteri ja setterite kaudu
// saan oma tinigmused luua kuidas ligi pääsen

// inheritance - extends ja super()
// iga kord saab ainult 1 kord millestki pärineda
// subclass saab endale KÕIK muutujad ja funktsioonid
// 1. koondan koodi kokku ühte parent classi
// 2. saan nad panna samasse listi

// abstraction - abstraktsioon;

// stream - avab List tüüpi muutuja ja teeb selle streamiks
// pärast peab ta tagasi Listiks collectima
// 1. efektiivne
// 2. loetav

public class Main {

    public static void main(String[] args) {
	    World world = new World(5,5);

	    Player player = new Player(world);
	    world.addCharacter(player);
	    Enemy enemy = new Enemy(world);
	    world.addCharacter(enemy);
        QuestMaster questMaster = new QuestMaster(world);
        world.addCharacter(questMaster);

        Dagger dagger = new Dagger(world);
        world.addItem(dagger);
        Hammer hammer = new Hammer(world);
        world.addItem(hammer);
        Sword sword = new Sword(world);
        world.addItem(sword);

        world.printMap();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("end")) {
            player.move(input, world);
            if (player.getxCoord() == enemy.getxCoord() && player.getyCoord() == enemy.getyCoord()) {
                enemy.setVisible(false);
            }
            if (player.getxCoord() == questMaster.getxCoord() && player.getyCoord() == questMaster.getyCoord()) {
                enemy.setVisible(true);
                enemy.generateRandomCoordinates(world);
                questMaster.setVisible(false);
            } else if (!questMaster.isVisible()) {
                questMaster.setVisible(true);
            }

            checkIfPlayerTakesItem(player, dagger);
            checkIfPlayerTakesItem(player, hammer);
            checkIfPlayerTakesItem(player, sword);

            world.printMap();
            input = scanner.nextLine();
        }
    }

    private static void checkIfPlayerTakesItem(Player player, Item item) {
        if (player.getxCoord() == item.getxCoord() && player.getyCoord() == item.getyCoord()) {
            player.addItem(item);
        }
    }
}
