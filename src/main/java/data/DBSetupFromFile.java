package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static utils.Utility.getProperty;

public class DBSetupFromFile implements DBSetup {

    private String filename;

    public DBSetupFromFile(String filename) {
        this.filename = filename;
    }

    public List<String> readDatabaseSetup() {
        List<String> commands = new ArrayList<>();

        try (InputStream inStream = getClass().getClassLoader().getResourceAsStream(getProperty(filename));
             InputStreamReader inStreamReader = new InputStreamReader(Objects.requireNonNull(inStream), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inStreamReader)) {
            reader.lines().forEach(commands::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commands;
    }

}
