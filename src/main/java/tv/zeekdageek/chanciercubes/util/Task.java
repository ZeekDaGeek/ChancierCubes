package tv.zeekdageek.chanciercubes.util;

import net.minecraft.entity.player.EntityPlayer;

public abstract class Task {

    public final String name;
    public int delayLeft;
    private boolean forever = false;
    public final int updateTick;

    public Task(String name, int delay) {
        this(name, delay, -1);
    }

    public Task(String name, int delay, int updateTick) {
        this.name = name;
        this.delayLeft = delay;
        if (delay == -1)
            this.forever = true;
        this.updateTick = updateTick;
    }

    public boolean shouldUpdate() {
        return this.updateTick != -1 && (delayLeft % updateTick) == 0;
    }

    public abstract void callback();

    public boolean tickTask() {
        this.delayLeft--;
        return this.delayLeft <= 0 && !forever;
    }

    public abstract void update();

    public void showTimeLeft(EntityPlayer player, String message) {
        // this is probably not used
    }

}
