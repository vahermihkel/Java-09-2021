package ee.mihkel.item;

import ee.mihkel.World;

public class Hammer extends Item {

    public Hammer(World world) {
        super(world, 3.0, "Haamer");
        reboost();
    }

    public void reboost() {
        setDurability(3);
    }
}
