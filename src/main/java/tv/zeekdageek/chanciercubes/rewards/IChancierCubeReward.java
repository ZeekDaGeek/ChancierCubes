package tv.zeekdageek.chanciercubes.rewards;

import chanceCubes.rewards.defaultRewards.IChanceCubeReward;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Adds configuration information to Chancecube rewards by default.
 */
public interface IChancierCubeReward extends IChanceCubeReward
{
    /**
     * The name of the reward that shows in the configuration file.
     * @return String
     */
    String getConfigName();

    /**
     * The description of the reward, used in the configuration file.
     * @return String
     */
    String getDesc();

    /**
     * Is the reward enabled or disabled by default.
     * @return boolean
     */
    boolean getDefaultState();



    /**
     * The function called when a chancecube reward happens.
     * @param world
     * @param x
     * @param y
     * @param z
     * @param entityPlayer
     */
    void trigger(World world, int x, int y, int z, EntityPlayer entityPlayer);

    /**
     * The chance that a reward will happen.
     * @return int
     */
    int getChanceValue();

    /**
     * The name of a reward including mod identifier.
     * @return string
     */
    String getName();
}
