package me.kuak.rm.server.dao.db;

import me.kuak.rm.server.dao.FileDao;
import me.kuak.rm.server.model.RmResource;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by guyo on 11/28/15.
 */
@Stateless
public class FileDB implements FileDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RmResource save(RmResource resource) {
        entityManager.persist(resource);
        entityManager.flush();
        return resource;
    }

    @Override
    public RmResource findById(Integer id) {
        return entityManager.find(RmResource.class, id);
    }

    @Override
    public List<RmResource> findByParentAndType(Integer parent, String type) {
        return null;
    }
}
