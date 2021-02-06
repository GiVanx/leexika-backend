package com.leexika.config.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ConfigurationProperty {
    private ConfigurationPropertyType type;
    private int intValue;
    private String stringValue;

    public ConfigurationProperty() {
    }
}
