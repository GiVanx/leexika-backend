package com.leexika.template.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.leexika.template.model.TemplateStage;
import com.leexika.template.model.TemplateStageConfiguration;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class TemplateStageSerializer extends JsonSerializer<TemplateStage> {

    @Override
    public void serialize(TemplateStage templateStage, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        if (templateStage.getId() != null) {
            jsonGenerator.writeNumberField("id", templateStage.getId());
        }
        jsonGenerator.writeStringField("type", templateStage.getType().toString());
        jsonGenerator.writeStringField("name", templateStage.getName());
        jsonGenerator.writeArrayFieldStart("configs");

        for (TemplateStageConfiguration stageConfig : templateStage.getConfigs().values()) {
            jsonGenerator.writeStartObject();
            if (stageConfig.getId() != null) {
                jsonGenerator.writeNumberField("id", stageConfig.getId());
            }
            jsonGenerator.writeStringField("name", stageConfig.getName());
            writeTemplateStageConfigurationValue(jsonGenerator, stageConfig);
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    private void writeTemplateStageConfigurationValue(JsonGenerator jsonGenerator, TemplateStageConfiguration stageConfig) throws IOException {
        switch (stageConfig.getType()) {
            case RANGE:
                jsonGenerator.writeNumberField("value", stageConfig.getIntValue());
                break;
        }
    }
}
