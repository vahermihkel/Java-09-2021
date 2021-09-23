package ee.mihkel;

import ee.mihkel.character.Enemy;
import ee.mihkel.character.Healer;
import ee.mihkel.character.Player;
import ee.mihkel.character.QuestMaster;
import ee.mihkel.item.*;
import ee.mihkel.thread.ThreadController;

import java.util.Scanner;
import java.util.Timer;

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
//        ThreadController.startNewThreads();

	    World world = new World(5,5);
	    Timer timer = new Timer();
	    GameController.startTimer(timer);

	    Player player = new Player(world);
	    world.addCharacter(player);
	    Enemy enemy = new Enemy(world);
	    world.addCharacter(enemy);
        QuestMaster questMaster = new QuestMaster(world);
        world.addCharacter(questMaster);
        Healer healer = new Healer(world);
        world.addCharacter(healer);

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

        try {
            while(!input.equals("end")) {
                player.move(input, world);
                GameController.checkPlayerInteractions(world, player, enemy, questMaster, healer, dagger, hammer, sword, teleporter, scanner);
                world.printMap();
                input = scanner.nextLine();
            }
            GameController.endGame(player, timer);
        } catch (GameOverException e) {
            GameController.endGame(player, timer);
        }
    }

}

// 1. Enemyl on erinevad tüübid: ENUM, uue suvalise ENUMi väärtuse genereerimine
// 2. Uut enemyt tehes genereeritakse talle suvaline ENUMi tüüp 7
// 3. randomiseCoordinates uus suvaline ENUMi tüüp
// 4. switch-case milline ENUMi tüüp on, vastavalt sellele elud
// 5. kui playerilt elusid võetakse siis võetakse ka ENUMi tüübi järgi
// 6. HashMapis on nii Tüüp kui ka arv mitu on tapetud
// 7. HashMapi lisamine - list, kus on võti väärtus paarid
//          võti on ENUMi TÜÜP ja väärtus on number mitu korda on tapetud
// 8. HashMapi kuvamine - forEach((key,value)-> TEEN MIDAGI)

// 1. Player.takeHealth, siis võtab kasutusele eseme tugevuse
// 2. Itemil on ka ENUM ja tüüp - bronze, silver, gold, platinum
// 3. Level, mis tõuseb iga kord kui seda kasutatakse
// 4. Kui ma tõstan levelit, siis lähen koheselt kontrollima kas saan suurendada tüüpi
// 5. Kui eseme tugevust võtan, siis annan tüübi võrra tugevusele tugevust juurde

// STATIC

// Interfaces
// Threads
// Geneerika