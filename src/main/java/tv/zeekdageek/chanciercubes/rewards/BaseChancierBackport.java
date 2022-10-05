package tv.zeekdageek.chanciercubes.rewards;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tv.zeekdageek.chanciercubes.config.Config;
import tv.zeekdageek.chanciercubes.config.EnumBackportValues;
import tv.zeekdageek.chanciercubes.util.math.RegisteryHelper;

public class BaseChancierBackport implements IChancierCubeBackport
{
    public EnumBackportValues rewardEnabled;

    /**
     * Register the reward and setup configs here.
     */
    public BaseChancierBackport() {
        if (this.isGiantcube()) {
            this.rewardEnabled = Config.backportGiantcubeConfig(this);
            RegisteryHelper.BackportGiantCube(this, this.getReplacedName());
        } else if (!this.isGiantcube()) {
            this.rewardEnabled = Config.backportChancecubeConfig(this);
            RegisteryHelper.BackportCube(this, this.getReplacedName());
        }
    }

    @Override
    public String getReplacedName() {
        return null;
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
    public String getDefaultState() {
        return null;
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
