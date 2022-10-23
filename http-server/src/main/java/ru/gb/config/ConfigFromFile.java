package ru.gb.config;

import java.io.IOException;
import java.util.Properties;

public class ConfigFromFile implements Config{
    private final String wwwHome;
    private final int port;

    public ConfigFromFile(String wwwHome) {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(wwwHome));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.wwwHome = properties.getProperty("www.home");
        this.port = Integer.parseInt(properties.getProperty("port"));
    }

    @Override
    public String getWwwHome() {
        return this.wwwHome;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
