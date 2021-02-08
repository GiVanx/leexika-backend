package com.leexika.template.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.leexika.config.model.Configuration;
import com.leexika.config.model.ConfigurationType;
import com.leexika.config.model.GameConfiguration;
import com.leexika.config.model.GameType;
import com.leexika.config.service.GameConfigurationService;
import com.leexika.template.model.TemplateStage;
import com.leexika.template.model.TemplateStageConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@JsonComponent
public class TemplateStageDeserializer extends JsonDeserializer<TemplateStage> {

    @Autowired
    private GameConfigurationService configurationService;

    @Override
    public TemplateStage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        TemplateStage stage = new TemplateStage();
        if (node.has("id")) {
            stage.setId(node.get("id").asLong());
        }
        GameType templateType = GameType.valueOf(node.get("name").asText());
        stage.setType(templateType);

        GameConfiguration gameConfig = configurationService.get(templateType);
        stage.setName(gameConfig.getName());

        Map<String, TemplateStageConfiguration> configs = new HashMap<>();

        for (final JsonNode configNode : node.get("configs")) {
            TemplateStageConfiguration stageConfiguration = createStageConfiguration(gameConfig, configNode);
            configs.put(stageConfiguration.getName(), stageConfiguration);
        }

        stage.setConfigs(configs);

        return stage;
    }

    TemplateStageConfiguration createStageConfiguration(GameConfiguration gameConfig, JsonNode jsonConfigNode) {
        TemplateStageConfiguration stageConfiguration = new TemplateStageConfiguration();
        String configName = jsonConfigNode.get("name").asText();

        Configuration config = gameConfig.getConfig(configName);
        stageConfiguration.setType(config.getType());
        stageConfiguration.setName(configName);

        if (config.getType() == ConfigurationType.RANGE) {
            stageConfiguration.setIntValue(jsonConfigNode.get("value").asInt());
        }
        return stageConfiguration;
    }
}
