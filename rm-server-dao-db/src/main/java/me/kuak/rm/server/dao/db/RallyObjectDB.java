package me.kuak.rm.server.dao.db;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.kuak.rm.server.dao.RallyObjectDao;
import me.kuak.rm.server.model.RallyObject;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class RallyObjectDB implements RallyObjectDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createRallyObject(RallyObject rallyObject) {
        entityManager.persist(rallyObject);
    }

}
