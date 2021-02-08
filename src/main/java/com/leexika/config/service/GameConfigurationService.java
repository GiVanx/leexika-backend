package com.leexika.config.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.leexika.config.model.ConfigurationProperty;
import com.leexika.config.model.GameConfiguration;
import com.leexika.config.model.GameType;
import com.leexika.config.utils.CustomConfigurationPropertyDeserializer;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;

@Service
public class GameConfigurationService {

    private static final String GAME_CONFIG_PATH = "game-config.json";
    private Gson gson;
    private Map<GameType, GameConfiguration> configMap;

    public GameConfigurationService() {
        Type t = new TypeToken<ConfigurationProperty>() {
        }.getType();
        gson = new GsonBuilder().registerTypeAdapter(t, new CustomConfigurationPropertyDeserializer()).create();
    }

    public void loadConfigurations() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(GAME_CONFIG_PATH);
        Reader reader = new InputStreamReader(is);

        Type type = new TypeToken<Map<GameType, GameConfiguration>>() {
        }.getType();
        configMap = gson.fromJson(reader, type);
    }

    public GameConfiguration get(GameType gameType) {
        return configMap.get(gameType);
    }
}
