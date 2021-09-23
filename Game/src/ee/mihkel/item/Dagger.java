package ee.mihkel.item;

import ee.mihkel.World;

public class Dagger extends Item {
    public Dagger(World world) {
        super(world, 2.0, "Pistoda");
        reboost();
    }

    public void reboost() {
        setDurability(4);
    }
}
