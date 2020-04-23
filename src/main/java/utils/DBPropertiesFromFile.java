package utils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class DBPropertiesFromFile implements DBProperties {

    public DBPropertiesFromFile() {

    }

    @Override
    public Properties getProperties() {
        Properties prop = new Properties();

        try {
            prop.load(Objects.requireNonNull(Utility.class.getClassLoader().getResourceAsStream("db.properties")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return prop;
    }

}
