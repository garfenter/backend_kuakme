package me.kuak.rm.server.dao.db;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import me.kuak.rm.server.dao.RallyDao;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.Registration;
import me.kuak.rm.server.model.StatusType;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class RallyDb implements RallyDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Question> findQuestionsByRallyIdAndCountryId(Integer rallyId, Integer countryId) {
        TypedQuery<Question> qry = entityManager.createQuery("SELECT q FROM Question q WHERE q.rallyCountry.country.id = :countryId AND q.rallyCountry.rally.id = :rallyId", Question.class);
        qry.setParameter("rallyId", rallyId);
        qry.setParameter("countryId", countryId);
        return qry.getResultList();
    }

    @Override
    public List<Rally> findActiveRallies() {
        TypedQuery<Rally> qry = entityManager.createQuery("SELECT r FROM Rally r WHERE r.status = :status", Rally.class);
        qry.setParameter("status", StatusType.ACTIVE);
        return qry.getResultList();
    }

    @Override
    public List<Country> findCountriesByRally(Integer rallyId) {
        TypedQuery<Country> qry = entityManager.createQuery("SELECT rc.country FROM RallyCountry rc WHERE rc.rally.id = :rallyId", Country.class);
        qry.setParameter("rallyId", rallyId);
        return qry.getResultList();
    }

    @Override
    public Registration register(Integer rallyId, Integer groupId) {
        Registration registration = new Registration();
        registration.setRegistrationDate(new Date());
        registration.setRally(entityManager.find(Rally.class, rallyId));
        registration.setGroup(entityManager.find(Group.class, groupId));
        entityManager.persist(registration);
        return registration;
    }

}
