package me.kuak.rm.server.dao.db;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import me.kuak.rm.server.dao.ResourceDao;
import me.kuak.rm.server.model.RmResource;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class ResourceDb implements ResourceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RmResource> findResourcesByType(String type) {
        TypedQuery<RmResource> qry = entityManager.createQuery("SELECT r FROM RmResource r WHERE r.type = :type", RmResource.class);
        qry.setParameter("type", type);
        return qry.getResultList();
    }

    @Override
    public RmResource createResource(RmResource resource) {
        if (resource.getId() != null) {
            RmResource prev = entityManager.find(RmResource.class, resource.getId());
            prev.setAnimation(resource.getAnimation());
            prev.setContentType(resource.getContentType());
            prev.setDescription(resource.getDescription());
            prev.setDownloadUrl(resource.getDownloadUrl());
            prev.setExtension(resource.getExtension());
            prev.setFilename(resource.getFilename());
            prev.setHeight(resource.getHeight());
            prev.setWidth(resource.getWidth());
            prev.setName(resource.getName());
            prev.setPosx(resource.getPosx());
            prev.setPosy(resource.getPosy());
            prev.setPosz(resource.getPosz());
            prev.setType(resource.getType());
            prev.setType_(resource.getType_());
        } else {
            entityManager.persist(resource);
        }
        return resource;
    }

    @Override
    public void createResourceObject(RmResource resource) {
        entityManager.merge(resource);
    }

    @Override
    public RmResource findResourceById(Integer id, Class clazz) {
        return entityManager.find(RmResource.class, id);
    }

    @Override
    public List<RmResource> findResources(Integer position, Integer limit) {
        TypedQuery<RmResource> qry = entityManager.createQuery("SELECT r FROM RmResource r", RmResource.class);
        qry.setMaxResults(limit);
        qry.setFirstResult(position);
        return qry.getResultList();
    }

    @Override
    public void updateResource(RmResource rmResource) {
        entityManager.merge(rmResource);
    }

}
