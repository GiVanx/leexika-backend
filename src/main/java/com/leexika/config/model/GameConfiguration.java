package com.leexika.config.model;

import lombok.ToString;

import java.util.Map;

@ToString
public class GameConfiguration {
    private Map<String, Configuration> configs;
}
