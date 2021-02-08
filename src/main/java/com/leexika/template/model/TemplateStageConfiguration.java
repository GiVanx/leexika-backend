package com.leexika.template.model;

import com.leexika.config.model.ConfigurationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@ToString
public class TemplateStageConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private ConfigurationType type;

    private Integer intValue;
    private String stringValue;
}
