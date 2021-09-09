package ee.mihkel;

import java.util.Scanner;

// encapsulation - private/public
// inheritance - extends ja super()

public class Main {

    public static void main(String[] args) {
	    World world = new World(5,5);

	    Player player = new Player();
	    world.addCharacter(player);

        world.printMap();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("end")) {
            player.move(input, world);
            world.printMap();
            input = scanner.nextLine();
        }
    }
}
