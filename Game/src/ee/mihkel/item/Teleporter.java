package ee.mihkel.item;

import ee.mihkel.World;

public class Teleporter extends Item {

    public Teleporter(World world) {
        super(world, "Teleporteerija");
        reboost();
    }

    public void reboost() {
        setDurability(3);
    }
}
