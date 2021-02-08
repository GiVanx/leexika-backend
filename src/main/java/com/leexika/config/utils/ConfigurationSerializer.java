package com.leexika.config.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.leexika.config.model.Configuration;
import com.leexika.config.model.ConfigurationProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ConfigurationSerializer extends JsonSerializer<Configuration> {

    @Override
    public void serialize(Configuration configuration, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", configuration.getName());
        jsonGenerator.writeObjectFieldStart("props");

        for (String key : configuration.getProps().keySet()) {
            ConfigurationProperty prop = configuration.getProps().get(key);
            switch (prop.getType()) {
                case STRING:
                    jsonGenerator.writeStringField(key, prop.getStringValue());
                    break;
                case INTEGER:
                    jsonGenerator.writeNumberField(key, prop.getIntValue());
                    break;
            }
        }
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }
}
