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
public class EntityConfigurationCreationCommand extends InitializationCommand {

    public EntityConfigurationCreationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        EntityConfiguration entityConfiguration = new EntityConfiguration();
        entityConfiguration.setEntityName("EntityConfiguration");
        entityConfiguration.setResourceName("entity-configuration");
        List<FieldConfiguration> fields = new ArrayList<>();
        fields.add(new FieldConfiguration("Entity name", "entityName", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Resource name", "resourceName", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Fields", "fields", FieldType.OBJECT_ARRAY, null, "FieldConfiguration", entityConfiguration));
        entityConfiguration.setFields(fields);
        getEntityManager().persist(entityConfiguration);
        EntityConfiguration entityConfForField = new EntityConfiguration();
        entityConfForField.setEntityName("FieldConfiguration");
        entityConfForField.setResourceName("field-configuration");
        List<FieldConfiguration> fields2 = new ArrayList();
        entityConfForField.setFields(fields2);
        fields2.add(new FieldConfiguration("Display name", "displayName", FieldType.INPUT, null, null, entityConfForField));
        fields2.add(new FieldConfiguration("Field name", "fieldName", FieldType.INPUT, null, null, entityConfForField));
        fields2.add(new FieldConfiguration("Field Type", "fieldType", FieldType.INPUT, null, null, entityConfForField));
        fields2.add(new FieldConfiguration("Default value", "defaultValue", FieldType.INPUT, null, null, entityConfForField));
        fields2.add(new FieldConfiguration("Configuration", "configuration", FieldType.INPUT, null, null, entityConfForField));
        fields2.add(new FieldConfiguration("Parent", "parent", FieldType.OBJECT, null, "EntityConfiguration", entityConfForField));
        getEntityManager().persist(entityConfForField);
        complete();
    }

    @Override
    String getName() {
        return "entity-configuration-creation";
    }

    @Override
    Integer getVersion() {
        return 1;
    }

}
