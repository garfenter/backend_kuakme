package me.kuak.rm.server.dao.db;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import me.kuak.rm.server.dao.CountryDao;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.StatusType;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class CountryDaoDb implements CountryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Country> findAllCountries() {
        TypedQuery<Country> qry = entityManager.createQuery("SELECT c FROM Country c WHERE c.status = :status", Country.class);
        qry.setParameter("status", StatusType.ACTIVE);
        return qry.getResultList();
    }

}
