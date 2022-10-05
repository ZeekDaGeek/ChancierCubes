package tv.zeekdageek.chanciercubes.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Config {

    public static Configuration config;
    public static File folder;

    private static class Defaults {
        public static final String greeting = "Hello World";
    }

    private static class Categories {
        public static final String general = "general";
    }

    public static String greeting = Defaults.greeting;

    public static void synchronizeConfiguration(File configFile) {
        folder = new File(configFile.getParentFile().getAbsolutePath() + "/ChanceCubes");
        folder.mkdirs();
        config = new Configuration(new File(folder + "/" + configFile.getName()));
        config.load();

        Property greetingProperty =
                config.get(Categories.general, "greeting", Defaults.greeting, "How shall I greet?");
        greeting = greetingProperty.getString();

        if (config.hasChanged()) {
            config.save();
        }
    }

}
