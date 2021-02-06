package com.leexika.config.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.leexika.config.model.ConfigurationProperty;
import com.leexika.config.model.ConfigurationPropertyType;

import java.lang.reflect.Type;

public class CustomConfigurationPropertyDeserializer implements JsonDeserializer<ConfigurationProperty> {

    private static final String TYPE_FIELD = "type";
    private static final String VALUE_FIELD = "value";

    @Override
    public ConfigurationProperty deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        ConfigurationProperty configurationProperty = new ConfigurationProperty();

        setConfigType(configurationProperty, jsonElement);
        setValue(configurationProperty, jsonElement);

        return configurationProperty;
    }

    private void setValue(ConfigurationProperty configurationProperty, JsonElement jsonElement) {
        JsonElement valueField = jsonElement.getAsJsonObject().get(VALUE_FIELD);
        switch (configurationProperty.getType()) {
            case INTEGER:
                configurationProperty.setIntValue(valueField.getAsInt());
                break;
            case STRING:
                configurationProperty.setStringValue(valueField.getAsString());
                break;
        }
    }

    private void setConfigType(ConfigurationProperty configurationProperty, JsonElement jsonElement) {
        ConfigurationPropertyType configType = this.getType(jsonElement);
        configurationProperty.setType(configType);
    }

    private ConfigurationPropertyType getType(JsonElement jsonElement) {
        String configType = jsonElement.getAsJsonObject().get(TYPE_FIELD).getAsString();
        return ConfigurationPropertyType.valueOf(configType);
    }
}
