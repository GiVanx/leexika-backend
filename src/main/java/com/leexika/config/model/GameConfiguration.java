package com.leexika.config.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
public class GameConfiguration {
    private String name;
    private Map<String, Configuration> configs;

    public Configuration getConfig(String name) {
        return configs.get(name);
    }
}
