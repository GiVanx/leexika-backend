package com.leexika.template.model;

import com.leexika.config.model.GameType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Setter
@Getter
@ToString
public class TemplateStage implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GameType type;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "configuration_type_mapping",
            joinColumns = {@JoinColumn(name = "template_stage_id", referencedColumnName = "id"),
                    @JoinColumn(name = "configuration_id", referencedColumnName = "id")})
    @MapKey(name = "name")
    private Map<String, TemplateStageConfiguration> configs;
}
