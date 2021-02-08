package com.leexika.config.controller;

import com.leexika.config.model.GameConfiguration;
import com.leexika.config.service.GameConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

    @Autowired
    private GameConfigurationService configurationService;

    @GetMapping
    public Collection<GameConfiguration> getConfigurations() {
        return configurationService.getAll();
    }
}
