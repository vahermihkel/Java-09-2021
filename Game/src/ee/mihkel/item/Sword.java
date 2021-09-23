package ee.mihkel.item;

import ee.mihkel.World;

public class Sword extends Item {
    public Sword(World world) {
        super(world, 5.0, "Mõõk");
        reboost();
    }

    public void reboost() {
        setDurability(1);
    }
}
