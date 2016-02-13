package me.kuak.rm.server.svc.integration;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import me.kuak.rm.server.model.admin.EntityConfiguration;
import me.kuak.rm.server.model.admin.FieldConfiguration;
import me.kuak.rm.server.model.admin.FieldType;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class QuestionConfigurationCommand extends InitializationCommand {

    public QuestionConfigurationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        EntityConfiguration entityConfiguration = new EntityConfiguration();
        entityConfiguration.setEntityName("Question");
        entityConfiguration.setResourceName("question");
        entityConfiguration.setVisible(Boolean.TRUE);
        entityConfiguration.setDisplayName("Preguntas");
        List<FieldConfiguration> fields = new ArrayList<>();
        fields.add(new FieldConfiguration("Pregunta", "plainText", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Recursos", "resources", FieldType.OBJECT_ARRAY, null, "Resource", entityConfiguration));
        entityConfiguration.setFields(fields);
        getEntityManager().persist(entityConfiguration);
        complete();
    }

    @Override
    String getName() {
        return "question-configuration-command";
    }

    @Override
    Integer getVersion() {
        return 1;
    }

}
