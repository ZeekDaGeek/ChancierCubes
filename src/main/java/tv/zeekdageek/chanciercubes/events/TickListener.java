package tv.zeekdageek.chanciercubes.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import tv.zeekdageek.chanciercubes.util.Scheduler;

public class TickListener {

    //private static MinecraftServer server;

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Scheduler.tickTasks();

            /* debug
            if (server == null)
                server = MinecraftServer.getServer();

            if (server.getTickCounter() % 20 == 0) {
                ChancierCubes.info("Tick listened to.");
            }
             */
        }
    }

}
