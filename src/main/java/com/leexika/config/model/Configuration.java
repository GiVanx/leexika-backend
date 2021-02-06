package com.leexika.config.model;

import lombok.ToString;

import java.util.Map;

@ToString
public class Configuration {
    private String name;
    private ConfigurationType type;

    private Map<String, ConfigurationProperty> props;
}
