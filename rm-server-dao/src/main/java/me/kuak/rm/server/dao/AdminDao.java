package me.kuak.rm.server.dao;

import java.util.List;
import me.kuak.rm.server.model.admin.EntityConfiguration;
import me.kuak.rm.server.model.admin.FieldConfiguration;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface AdminDao {

    void createEntityConfiguration(EntityConfiguration entityConfiguration);

    void updateEntityConfiguration(EntityConfiguration entityConfiguration);

    List<EntityConfiguration> findEntitiesConfiguration(Integer position, Integer limit);

    void deleteEntityConfiguration(Integer id);

    void createFieldConfiguration(FieldConfiguration fieldConfiguration);

    void updateFieldConfiguration(FieldConfiguration fieldConfiguration);

    List<FieldConfiguration> findFieldsConfiguration(Integer position, Integer limit);

    void deleteFieldConfiguration(Integer id);

    FieldConfiguration findFieldConfigurationById(Integer id);

    EntityConfiguration findEntityConfigurationById(Integer id);

    EntityConfiguration findConfigurationByEntityName(String entityName);
    
    EntityConfiguration findConfigurationByResourceName(String name);
    
}
