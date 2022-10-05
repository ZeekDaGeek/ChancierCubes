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
        public static final String general = "all general settings";
        public static final String twitch = "twitch integration";
        public static final String backported_giantcube = "backported giant chancecube rewards";
        public static final String backported_chancecube = "backported chancecube rewards";
        public static final String giantcube = "giant chancecube rewards";
        public static final String chancecube = "chancecube rewards";
    }

    public static EnumBackportValues backportGiantcubeConfig(IChancierCubeBackport reward) {
        EnumBackportValues result = EnumBackportValues.convert(config.get(
            Categories.backported_giantcube,
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

    public static EnumBackportValues backportChancecubeConfig(IChancierCubeBackport reward) {
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

    public static boolean registerGiantcubeConfig(IChancierCubeReward reward) {
        boolean result = config.getBoolean(
            reward.getConfigName(),
            Categories.giantcube,
            reward.getDefaultState(),
            reward.getDesc()
        );

        if (config.hasChanged()) {
            config.save();
        }

        return result;
    }

    public static boolean registerChancecubeConfig(IChancierCubeReward reward) {
        boolean result = config.getBoolean(
            reward.getConfigName(),
            Categories.chancecube,
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
                Categories.general,
                "empty", true,
                "I don't want general to be empty"
            );

        config.setCategoryComment(
                Categories.backported_giantcube,
                "Every backported reward can be set to [enabled/disabled/replace]. Replace will automatically\n" +
                    "disable related reward in the current version. Invalid values are disabled."
            );

        config.setCategoryComment(
                Categories.backported_chancecube,
                "Every backported reward can be set to [enabled/disabled/replace]. Replace will automatically\n" +
                    "disable related reward in the current version. Invalid values are disabled."
            );

        if (config.hasChanged()) {
            config.save();
        }
    }

}
