package tv.zeekdageek.chanciercubes.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import tv.zeekdageek.chanciercubes.rewards.IChancierCubeBackport;
import tv.zeekdageek.chanciercubes.rewards.IChancierCubeReward;

import java.io.File;

public class Config {

    public static Configuration config;
    public static File folder;

    public static class Categories {
        public static final String GENERAL = "all general settings";
        public static final String TWITCH = "twitch integration";
        public static final String BACKPORT_GIANT_CUBE = "backported giant chance cube rewards";
        public static final String BACKPORT_CHANCE_CUBE = "backported normal chance cube rewards";
        public static final String GIANT_CUBE = "giant chance cube rewards";
        public static final String CHANCE_CUBE = "normal chance cube rewards";
    }

    public static EnumBackportValues backportGiantCubeConfig(IChancierCubeBackport reward) {
        EnumBackportValues result = EnumBackportValues.convert(config.get(
            Categories.BACKPORT_GIANT_CUBE,
            reward.getConfigName(),
            reward.getDefaultState(),
            reward.getDesc(),
            EnumBackportValues.validStringArray()
        ).toString());

        if (config.hasChanged()) {
            config.save();
        }

        return result;
    }

    public static EnumBackportValues backportChanceCubeConfig(IChancierCubeBackport reward) {
        EnumBackportValues result = EnumBackportValues.convert(config.get(
            Categories.BACKPORT_CHANCE_CUBE,
            reward.getConfigName(),
            reward.getDefaultState(),
            reward.getDesc(),
            EnumBackportValues.validStringArray()
        ).toString());

        if (config.hasChanged()) {
            config.save();
        }

        return result;
    }

    public static boolean registerGiantCubeConfig(IChancierCubeReward reward) {
        boolean result = config.getBoolean(
            reward.getConfigName(),
            Categories.GIANT_CUBE,
            reward.getDefaultState(),
            reward.getDesc()
        );

        if (config.hasChanged()) {
            config.save();
        }

        return result;
    }

    public static boolean registerChanceCubeConfig(IChancierCubeReward reward) {
        boolean result = config.getBoolean(
            reward.getConfigName(),
            Categories.CHANCE_CUBE,
            reward.getDefaultState(),
            reward.getDesc()
        );

        if (config.hasChanged()) {
            config.save();
        }

        return result;
    }

    public static void synchronizeConfiguration(File configFile) {
        folder = new File(configFile.getParentFile().getAbsolutePath() + "/ChanceCubes");
        folder.mkdirs();
        config = new Configuration(new File(folder + "/" + configFile.getName()));
        config.load();

        Property backport_replace = config.get(
                Categories.GENERAL,
                "empty", true,
                "I don't want general to be empty"
            );

        config.setCategoryComment(
                Categories.BACKPORT_GIANT_CUBE,
                "Every backported reward can be set to [enabled/disabled/replace]. Replace will automatically\n" +
                    "disable related reward in the current version. Invalid values are disabled."
            );

        config.setCategoryComment(
                Categories.BACKPORT_CHANCE_CUBE,
                "Every backported reward can be set to [enabled/disabled/replace]. Replace will automatically\n" +
                    "disable related reward in the current version. Invalid values are disabled."
            );

        if (config.hasChanged()) {
            config.save();
        }
    }

}
