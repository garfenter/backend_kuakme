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
public class RallyObjectConfigurationCommand extends InitializationCommand {

    public RallyObjectConfigurationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        EntityConfiguration entityConfiguration = new EntityConfiguration();
        entityConfiguration.setEntityName("RallyObject");
        entityConfiguration.setResourceName("rally-object");
        entityConfiguration.setVisible(Boolean.FALSE);
        entityConfiguration.setDisplayName("Rally Object");
        List<FieldConfiguration> fields = new ArrayList<>();
        fields.add(new FieldConfiguration("Nombre", "name", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Recursos", "resources", FieldType.OBJECT_ARRAY, null, "Resource", entityConfiguration));
        entityConfiguration.setFields(fields);
        getEntityManager().persist(entityConfiguration);
        complete();
    }

    @Override
    String getName() {
        return "rally-object-configuration-command";
    }

    @Override
    Integer getVersion() {
        return 1;
    }

}
