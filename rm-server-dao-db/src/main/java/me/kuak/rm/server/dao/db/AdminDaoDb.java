package me.kuak.rm.server.dao.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import me.kuak.rm.server.dao.AdminDao;
import me.kuak.rm.server.model.admin.EntityConfiguration;
import me.kuak.rm.server.model.admin.FieldConfiguration;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class AdminDaoDb implements AdminDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createEntityConfiguration(EntityConfiguration entityConfiguration) {
        em.persist(entityConfiguration);
    }

    @Override
    public void updateEntityConfiguration(EntityConfiguration entityConfiguration) {
        em.merge(entityConfiguration);
    }

    @Override
    public List<EntityConfiguration> findEntitiesConfiguration(Integer position, Integer limit) {
        TypedQuery<EntityConfiguration> qry = em.createQuery("SELECT f FROM EntityConfiguration f", EntityConfiguration.class);
        qry.setFirstResult(position);
        qry.setMaxResults(limit);
        return qry.getResultList();
    }

    @Override
    public void deleteEntityConfiguration(Integer id) {
        em.remove(findEntityConfigurationById(id));
    }

    @Override
    public void createFieldConfiguration(FieldConfiguration fieldConfiguration) {
        em.persist(fieldConfiguration);
    }

    @Override
    public void updateFieldConfiguration(FieldConfiguration fieldConfiguration) {
        em.merge(fieldConfiguration);
    }

    @Override
    public List<FieldConfiguration> findFieldsConfiguration(Integer position, Integer limit) {
        TypedQuery<FieldConfiguration> qry = em.createQuery("SELECT f FROM FieldConfiguration f", FieldConfiguration.class);
        qry.setFirstResult(position);
        qry.setMaxResults(limit);
        return qry.getResultList();
    }

    @Override
    public void deleteFieldConfiguration(Integer id) {
        em.remove(findFieldConfigurationById(id));
    }

    @Override
    public FieldConfiguration findFieldConfigurationById(Integer id) {
        return em.find(FieldConfiguration.class, id);
    }

    @Override
    public EntityConfiguration findEntityConfigurationById(Integer id) {
        return em.find(EntityConfiguration.class, id);
    }

    @Override
    public EntityConfiguration findConfigurationByEntityName(String entityName) {
        TypedQuery<EntityConfiguration> qry = em.createQuery("SELECT e FROM EntityConfiguration e WHERE e.entityName = :entityName", EntityConfiguration.class);
        qry.setParameter("entityName", entityName);
        List result = qry.getResultList();
        return (EntityConfiguration) (result.isEmpty() ? null : result.get(0));
    }

    @Override
    public EntityConfiguration findConfigurationByResourceName(String resourceName) {
        TypedQuery<EntityConfiguration> qry = em.createQuery("SELECT e FROM EntityConfiguration e WHERE e.resourceName = :resourceName", EntityConfiguration.class);
        qry.setParameter("resourceName", resourceName);
        List result = qry.getResultList();
        return (EntityConfiguration) (result.isEmpty() ? null : result.get(0));
    }

}
