package tv.zeekdageek.chanciercubes.util.math;

import chanceCubes.registry.ChanceCubeRegistry;
import chanceCubes.registry.GiantCubeRegistry;
import chanceCubes.rewards.defaultRewards.IChanceCubeReward;
import tv.zeekdageek.chanciercubes.ChancierCubes;
import tv.zeekdageek.chanciercubes.rewards.BaseChancierBackport;

import javax.annotation.Nullable;

public class RegistryHelper
{
    RegistryHelper() {
    }

    /**
     * Inserts the new {reward} into the GiantCubeRegistry and disabled the replaced_reward.
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
        String backported = (reward instanceof BaseChancierBackport) ? "backported " : "";
        ChancierCubes.info(String.format("Registering %sGiant Chance Cube reward: %s", backported, reward.getName()));
        GiantCubeRegistry.INSTANCE.registerReward(reward);
    }

    public static void UnregisterGiantCube(String reward_name) {
        ChancierCubes.info(String.format("Unregistering Giant Chance Cube reward: %s", reward_name));
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
        String backported = (reward instanceof BaseChancierBackport) ? "backported " : "";
        ChancierCubes.info(String.format("Registering %sreward: %s", backported, reward.getName()));
        ChanceCubeRegistry.INSTANCE.registerReward(reward);
    }

    public static void UnregisterCube(String reward_name) {
        ChancierCubes.info(String.format("Unregistering reward: %s", reward_name));
        ChanceCubeRegistry.INSTANCE.unregisterReward(reward_name);
    }
}
