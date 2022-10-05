package tv.zeekdageek.chanciercubes.config;

public enum EnumBackportValues
{
    ENABLED,
    DISABLED,
    REPLACE;

    public static EnumBackportValues convert(String input) {

        switch (input.toLowerCase()) {
            case "enabled":
                return ENABLED;
            case "replace":
                return REPLACE;
            case "disabled":
            default:
                return DISABLED;
        }

    }

    public static String[] validStringArray() {
        return new String[] {"enable","disable","replace"};
    }
}
