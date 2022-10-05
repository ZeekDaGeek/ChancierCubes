package tv.zeekdageek.chanciercubes.rewards;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tv.zeekdageek.chanciercubes.config.Config;
import tv.zeekdageek.chanciercubes.config.EnumBackportValues;
import tv.zeekdageek.chanciercubes.util.math.RegistryHelper;

public abstract class BaseChancierBackport implements IChancierCubeBackport
{
    public EnumBackportValues rewardEnabled = EnumBackportValues.DISABLED;

    /**
     * Register the reward and setup configs here.
     */
    public BaseChancierBackport() {
        if (this.isGiantCube()) {
            this.rewardEnabled = Config.backportGiantCubeConfig(this);
            RegistryHelper.BackportGiantCube(this, this.getReplacedName());
        } else if (!this.isGiantCube()) {
            this.rewardEnabled = Config.backportChanceCubeConfig(this);
            RegistryHelper.BackportCube(this, this.getReplacedName());
        }
    }

    public String getReplacedName() {
        return null;
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

    public String getDefaultState() {
        return null;
    }

    public int getChanceValue() {
        return 0;
    }

    public String getName() {
        return null;
    }

    public void trigger(World world, int x, int y, int z, EntityPlayer entityPlayer) {

    }
}
