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
        entityManager.persist(resource);
        return resource;
    }

}
