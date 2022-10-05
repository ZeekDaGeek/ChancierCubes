package tv.zeekdageek.chanciercubes.rewards;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tv.zeekdageek.chanciercubes.config.Config;
import tv.zeekdageek.chanciercubes.util.math.RegisteryHelper;

public class BaseChancierReward implements IChancierCubeReward
{
    public boolean rewardEnabled;

    /**
     * Register the reward and setup configs here.
     */
    BaseChancierReward() {
        if (this.isGiantcube()) {
            this.rewardEnabled = Config.registerGiantcubeConfig(this);
            RegisteryHelper.RegisterGiantCube(this);
        } else {
            this.rewardEnabled = Config.registerChancecubeConfig(this);
            RegisteryHelper.RegisterCube(this);
        }
    }


    @Override
    public boolean isGiantcube() {
        return false;
    }

    @Override
    public String getConfigName() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public boolean getDefaultState() {
        return false;
    }

    @Override
    public void trigger(World world, int x, int y, int z, EntityPlayer entityPlayer) {

    }

    @Override
    public int getChanceValue() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
