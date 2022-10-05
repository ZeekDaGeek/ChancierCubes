package tv.zeekdageek.chanciercubes.proxy;

import chanceCubes.registry.GiantCubeRegistry;
import cpw.mods.fml.common.event.*;
import tv.zeekdageek.chanciercubes.ChancierCubes;
import tv.zeekdageek.chanciercubes.Tags;
import tv.zeekdageek.chanciercubes.backportedrewards.giantRewards.FluidSphereReward;
import tv.zeekdageek.chanciercubes.config.Config;
import tv.zeekdageek.chanciercubes.util.math.RegisteryHelper;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        ChancierCubes.info(Config.greeting);
        ChancierCubes.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {}

    // postInit "Handle interaction with other mods, complete your setup based on this."
    public void postInit(FMLPostInitializationEvent event) {
        ChancierCubes.info("Injecting ChancierCube Rewards.");
        RegisteryHelper.RegisterGiantCube(new FluidSphereReward());
        GiantCubeRegistry.INSTANCE.registerReward(new FluidSphereReward());
    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {}

    // register server commands in this event handler
    public void serverStarting(FMLServerStartingEvent event) {}

    public void serverStarted(FMLServerStartedEvent event) {}

    public void serverStopping(FMLServerStoppingEvent event) {}

    public void serverStopped(FMLServerStoppedEvent event) {}
}
