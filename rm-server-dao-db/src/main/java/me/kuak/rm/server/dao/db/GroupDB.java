package me.kuak.rm.server.dao.db;

import me.kuak.rm.server.dao.GroupDao;
import me.kuak.rm.server.model.Group;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by guyo on 11/24/15.
 */
@Stateless
public class GroupDB implements GroupDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Group findByUser(String user) {
        TypedQuery<Group> query = entityManager.createNamedQuery("Group.findByUser", Group.class);
        query.setParameter("user", user);
        List<Group> groups = query.getResultList();
        if (groups.size() > 0) {
            return groups.get(0);
        }
        return null;
    }
    
    @Override
    public List<Group> findAll() {
        TypedQuery<Group> query = entityManager.createQuery("select g from Group g", Group.class);
        return query.getResultList();
    }
    
}
