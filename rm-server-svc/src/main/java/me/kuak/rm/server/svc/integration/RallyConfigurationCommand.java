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
public class RallyConfigurationCommand extends InitializationCommand {

    public RallyConfigurationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        EntityConfiguration entityConfiguration = new EntityConfiguration();
        entityConfiguration.setEntityName("Rally");
        entityConfiguration.setResourceName("rally");
        entityConfiguration.setVisible(Boolean.TRUE);
        entityConfiguration.setDisplayName("Rally");
        List<FieldConfiguration> fields = new ArrayList<>();
        fields.add(new FieldConfiguration("Nombre", "name", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Recursos", "resources", FieldType.OBJECT_ARRAY, null, "Resource", entityConfiguration));
        fields.add(new FieldConfiguration("Paises del rally", "rallyCountries", FieldType.OBJECT_ARRAY, null, "RallyCountry", entityConfiguration));
        entityConfiguration.setFields(fields);
        getEntityManager().persist(entityConfiguration);
        complete();
    }

    @Override
    String getName() {
        return "rally-configuration-command";
    }

    @Override
    Integer getVersion() {
        return 1;
    }

}
