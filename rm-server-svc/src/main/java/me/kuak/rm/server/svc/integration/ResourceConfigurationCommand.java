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
public class ResourceConfigurationCommand extends InitializationCommand {

    public ResourceConfigurationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        EntityConfiguration entityConfiguration = new EntityConfiguration();
        entityConfiguration.setEntityName("Resource");
        entityConfiguration.setResourceName("resource");
        entityConfiguration.setVisible(Boolean.TRUE);
        entityConfiguration.setDisplayName("Recursos");
        List<FieldConfiguration> fields = new ArrayList<>();
        fields.add(new FieldConfiguration("Nombre", "name", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("URL", "downloadUrl", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Extension", "extension", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Content type", "contentType", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Filename", "filename", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Type", "type", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Pos x", "posx", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Pos y", "posy", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Pos z", "posz", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("width", "width", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("height", "height", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("animation", "animation", FieldType.INPUT, null, null, entityConfiguration));
        fields.add(new FieldConfiguration("Parent", "parent", FieldType.OBJECT, null, "RallyObject", "name", entityConfiguration));
        entityConfiguration.setFields(fields);
        getEntityManager().persist(entityConfiguration);
        complete();
    }

    @Override
    String getName() {
        return "resource-configuration-command";
    }

    @Override
    Integer getVersion() {
        return 1;
    }

}
