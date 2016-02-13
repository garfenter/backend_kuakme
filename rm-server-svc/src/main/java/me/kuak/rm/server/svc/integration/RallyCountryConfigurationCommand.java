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
public class RallyCountryConfigurationCommand extends InitializationCommand {

    public RallyCountryConfigurationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        EntityConfiguration entityConfiguration = new EntityConfiguration();
        entityConfiguration.setEntityName("RallyCountry");
        entityConfiguration.setResourceName("rally-country");
        entityConfiguration.setVisible(Boolean.FALSE);
        entityConfiguration.setDisplayName("Paises del Rally");
        List<FieldConfiguration> fields = new ArrayList<>();
        fields.add(new FieldConfiguration("Pais", "country", FieldType.OBJECT, null, "Country", "name", entityConfiguration));
        fields.add(new FieldConfiguration("Rally", "rally", FieldType.OBJECT, null, "Rally", "name", entityConfiguration));
        fields.add(new FieldConfiguration("Recursos", "resources", FieldType.OBJECT_ARRAY, null, "Resource", entityConfiguration));
        fields.add(new FieldConfiguration("Preguntas", "questions", FieldType.OBJECT_ARRAY, null, "Question", entityConfiguration));
        entityConfiguration.setFields(fields);
        getEntityManager().persist(entityConfiguration);
        complete();
    }

    @Override
    String getName() {
        return "rally-country-configuration-command";
    }

    @Override
    Integer getVersion() {
        return 1;
    }

}
