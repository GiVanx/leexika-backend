package com.leexika.template.service;

import com.leexika.template.model.Template;
import com.leexika.template.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    public Template save(Template template) {
        return templateRepository.save(template);
    }

    public List<Template> getAll() {
        return templateRepository.findAll();
    }
}
