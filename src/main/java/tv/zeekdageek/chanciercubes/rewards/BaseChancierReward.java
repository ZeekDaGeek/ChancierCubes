package tv.zeekdageek.chanciercubes.rewards;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tv.zeekdageek.chanciercubes.config.Config;
import tv.zeekdageek.chanciercubes.util.math.RegistryHelper;

public class BaseChancierReward implements IChancierCubeReward
{
    public boolean rewardEnabled;

    /**
     * Register the reward and setup configs here.
     */
    BaseChancierReward() {
        if (this.isGiantCube()) {
            this.rewardEnabled = Config.registerGiantCubeConfig(this);
            RegistryHelper.RegisterGiantCube(this);
        } else {
            this.rewardEnabled = Config.registerChanceCubeConfig(this);
            RegistryHelper.RegisterCube(this);
        }
    }


    public boolean isGiantCube() {
        return false;
    }

    public String getConfigName() {
        return null;
    }

    public String getDesc() {
        return null;
    }

    public boolean getDefaultState() {
        return false;
    }

    public void trigger(World world, int x, int y, int z, EntityPlayer entityPlayer) {

    }

    public int getChanceValue() {
        return 0;
    }

    public String getName() {
        return null;
    }
}
