package tv.zeekdageek.chanciercubes.rewards;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tv.zeekdageek.chanciercubes.config.Config;
import tv.zeekdageek.chanciercubes.config.EnumBackportValues;
import tv.zeekdageek.chanciercubes.util.math.RegisteryHelper;

public abstract class BaseChancierBackport implements IChancierCubeBackport
{
    public EnumBackportValues rewardEnabled = EnumBackportValues.DISABLED;

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

    public String getReplacedName() {
        return null;
    }

    public boolean isGiantcube() {
        return false;
    }

    public String getConfigName() {
        return null;
    }

    public String getDesc() {
        return null;
    }

    public String getDefaultState() {
        return null;
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
