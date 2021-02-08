package com.leexika.template.controller;

import com.leexika.template.model.Template;
import com.leexika.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/template")
public class GameTemplateController {

    @Autowired
    private TemplateService templateService;

    @PostMapping
    public Template create(@RequestBody Template template) {
        return templateService.save(template);
    }

    @GetMapping
    public List<Template> getAll() {
        return templateService.getAll();
    }
}
