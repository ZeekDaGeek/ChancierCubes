package tv.zeekdageek.chanciercubes.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;
import tv.zeekdageek.chanciercubes.ChancierCubes;
import tv.zeekdageek.chanciercubes.Tags;
import tv.zeekdageek.chanciercubes.commands.ServerCommands;
import tv.zeekdageek.chanciercubes.config.Config;
import tv.zeekdageek.chanciercubes.events.TickListener;
import tv.zeekdageek.chanciercubes.rewards.backported.giantRewards.*;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc., and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        TickListener listener = new TickListener();
        FMLCommonHandler.instance().bus().register(listener);
        //MinecraftForge.EVENT_BUS.register(listener);

        ChancierCubes.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {}

    // postInit "Handle interaction with other mods, complete your setup based on this."
    public void postInit(FMLPostInitializationEvent event) {
        ChancierCubes.info("Injecting ChancierCube Rewards.");

        // Load in all the ChanceCube Rewards
        // Giant Chance Cubes
        new FluidSphereReward();
        new MixedFluidSphereReward();
        new BeaconArenaReward();
        new BlockInfectionReward();
        new FireworkShowReward();

        // Chance Cubes
    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
    }

    // register server commands in this event handler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new ServerCommands());
    }

    public void serverStarted(FMLServerStartedEvent event) {
    }

    public void serverStopping(FMLServerStoppingEvent event) {}

    public void serverStopped(FMLServerStoppedEvent event) {}
}
