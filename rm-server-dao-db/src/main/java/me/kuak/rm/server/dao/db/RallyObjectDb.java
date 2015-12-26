package me.kuak.rm.server.dao.db;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.kuak.rm.server.dao.RallyObjectDao;
import me.kuak.rm.server.model.RallyObject;
import me.kuak.rm.server.model.StatusType;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class RallyObjectDb implements RallyObjectDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createRallyObject(RallyObject rallyObject) {
        rallyObject.setStatus(StatusType.ACTIVE);
        rallyObject.setCreationDate(new Date());
        entityManager.merge(rallyObject);
    }

    @Override
    public RallyObject findRallyObjectById(Integer id, Class clazz) {
        return (RallyObject) entityManager.find(clazz, id);
    }

}
