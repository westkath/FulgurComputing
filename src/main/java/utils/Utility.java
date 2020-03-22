package utils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Utility {

    public static String getProperty(String property) {
        return getPropertiesFile().getProperty(property);
    }

    public static Properties getPropertiesFile() {
        Properties prop = new Properties();

        try {
            prop.load(Objects.requireNonNull(Utility.class.getClassLoader().getResourceAsStream("db.properties")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return prop;
    }

}
