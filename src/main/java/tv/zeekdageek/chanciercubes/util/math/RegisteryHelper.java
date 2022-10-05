package tv.zeekdageek.chanciercubes.util.math;

import chanceCubes.registry.ChanceCubeRegistry;
import chanceCubes.registry.GiantCubeRegistry;
import chanceCubes.rewards.defaultRewards.IChanceCubeReward;

import javax.annotation.Nullable;

public class RegisteryHelper
{
    public void RegisteryHelper() {
    }

    /**
     * Inserts the new {reward} into the GiantCubeRegistery and disabled the replaced_reward.
     * @param reward
     * @param replaced_reward
     */
    public static void BackportGiantCube(IChanceCubeReward reward, @Nullable String replaced_reward) {
        RegisterGiantCube(reward);
        if (replaced_reward != null) {
            UnregisterGiantCube(replaced_reward);
        }
    }

    public static void RegisterGiantCube(IChanceCubeReward reward) {
        GiantCubeRegistry.INSTANCE.registerReward(reward);
    }

    public static void UnregisterGiantCube(String reward_name) {
        GiantCubeRegistry.INSTANCE.unregisterReward(reward_name);
    }

    /**
     * Inserts the new {reward} into the CubeRegistry and disabled the replaced_reward.
     * @param reward
     * @param replaced_reward
     */
    public static void BackportCube(IChanceCubeReward reward, @Nullable String replaced_reward) {
        RegisterCube(reward);
        if (replaced_reward != null) {
            UnregisterCube(replaced_reward);
        }
    }

    public static void RegisterCube(IChanceCubeReward reward) {
        ChanceCubeRegistry.INSTANCE.registerReward(reward);
    }

    public static void UnregisterCube(String reward_name) {
        ChanceCubeRegistry.INSTANCE.unregisterReward(reward_name);
    }
}
