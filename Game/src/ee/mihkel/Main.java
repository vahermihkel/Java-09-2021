package ee.mihkel;

import ee.mihkel.character.Enemy;
import ee.mihkel.character.Player;
import ee.mihkel.character.QuestMaster;
import ee.mihkel.item.*;

import java.util.Random;
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
        Teleporter teleporter = new Teleporter(world);
        world.addItem(teleporter);

        world.printMap();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("end")) {
            player.move(input, world);
            if (player.getxCoord() == enemy.getxCoord() && player.getyCoord() == enemy.getyCoord() && enemy.isVisible()) {
                enemy.setVisible(false);
                player.showItems();
                if (player.hasItems()) {
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
                                System.out.println("Ütle üks number 1-3");
                                int userNumber = 0;
                                while (userNumber == 0) {
                                    try {
                                        input = scanner.nextLine();
                                        userNumber = Integer.parseInt(input);
                                        // while userNumber == 0
                                        // sisestasid numbri asemel mingi muu sümboli
                                        // try catch
                                        if (userNumber < 1 || userNumber > 3) {
                                            throw new TooBigOrSmallNumberException();
                                        }
                                        Random random = new Random();
                                        int enemyNumber = random.nextInt(3)+1;
                                        if (userNumber == enemyNumber) {
                                            // kaotab enemy elu
                                            System.out.println("ENemy kaotab elu");
                                        } else {
                                            // kaotab player elu
                                            System.out.println("Mängija kaotab elu");
                                        }
                                    } catch (TooBigOrSmallNumberException exception) {
                                        exception.printStackTrace();
//                                        System.out.println("Sisestasid liiga suure või väikse numbri, sisesta uuesti: ");
                                    } catch (NumberFormatException e) {
                                        System.out.println("Sisestasid numbri asemel muu sümboli, sisesta uuesti: ");
                                    }
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Sisestasid numbri asemel muu sümboli, sisesta uuesti: ");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Sisestasid liiga suure või väikse numbri, sisesta uuesti: ");
                        }
                    }
                } else {
                    System.out.println("Esemeid ei ole millega võidelda, mine korja!");
                }
                // 1. näita kõiki esemeid
                // 2. vali üks kindel ese --- try-catch
                // 3. esemelt võtan ära kasutuskorra
                // 4. player peab ütlema ühe numbri 1-st 3-ni ja enemy genereerib ka numbri 1-3  --- try-catch
                            // custom exceptionit
                // 5. kui sai pihta, siis võtab enemy-lt elu, kui paneb mööda, võetakse sinult elu --- char-s lisada elud
                // 6. käib nii kaua kuni elud otsa saavad
                // 7. kui mängijal saavad elud otsa, siis on läbi --- throw new exception
                // 8. kui vastasel elud otsa saavad, siis kogun ta listi ja saan mängu läbi saades näha
                //          kelle olen kätte saanud --- HashMap, omab endas elemente milles on võti ja väärtus
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
            checkIfPlayerTakesItem(player, teleporter);

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
