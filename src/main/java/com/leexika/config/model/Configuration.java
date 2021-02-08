package com.leexika.config.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
public class Configuration {
    private String name;
    private ConfigurationType type;
    private Map<String, ConfigurationProperty> props;
}
