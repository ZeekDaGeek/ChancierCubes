package tv.zeekdageek.chanciercubes.proxy;

import cpw.mods.fml.common.event.*;
import tv.zeekdageek.chanciercubes.ChancierCubes;
import tv.zeekdageek.chanciercubes.Tags;
import tv.zeekdageek.chanciercubes.commands.ServerCommands;
import tv.zeekdageek.chanciercubes.config.Config;
import tv.zeekdageek.chanciercubes.rewards.backported.giantRewards.FluidSphereReward;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        ChancierCubes.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {}

    // postInit "Handle interaction with other mods, complete your setup based on this."
    public void postInit(FMLPostInitializationEvent event) {
        ChancierCubes.info("Injecting ChancierCube Rewards.");

        new FluidSphereReward();
    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {}

    // register server commands in this event handler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new ServerCommands());
    }

    public void serverStarted(FMLServerStartedEvent event) {}

    public void serverStopping(FMLServerStoppingEvent event) {}

    public void serverStopped(FMLServerStoppedEvent event) {}
}
