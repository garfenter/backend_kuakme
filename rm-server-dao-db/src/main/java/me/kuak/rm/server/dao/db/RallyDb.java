package me.kuak.rm.server.dao.db;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import me.kuak.rm.server.dao.RallyDao;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.model.MultipleValueQuestion;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.QuestionAnswer;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.RallyCountry;
import me.kuak.rm.server.model.Rankin;
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
        TypedQuery<Question> qry = entityManager.createQuery("SELECT q FROM Question q WHERE q.rallyCountry.country.id = :countryId AND q.rallyCountry.rally.id = :rallyId AND q.type='question'", Question.class);
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
    public List<RallyCountry> findCountriesByRally(Integer rallyId) {
        TypedQuery<RallyCountry> qry = entityManager.createQuery("SELECT rc FROM RallyCountry rc WHERE rc.rally.id = :rallyId", RallyCountry.class);
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
    
    @Override
    public List<MultipleValueQuestion> findMultipleValueQuestionsByRallyIdAndCountryId(Integer rallyId, Integer countryId) {
        TypedQuery<MultipleValueQuestion> qry = entityManager.createQuery("SELECT q FROM MultipleValueQuestion q WHERE q.rallyCountry.country.id = :countryId AND q.rallyCountry.rally.id = :rallyId", MultipleValueQuestion.class);
        qry.setParameter("rallyId", rallyId);
        qry.setParameter("countryId", countryId);
        return qry.getResultList();
    }
    
    @Override
    public RallyCountry findRallyCountry(Integer rallyId, Integer countryId) {
        TypedQuery<RallyCountry> qry = entityManager.createQuery("SELECT rc FROM RallyCountry rc WHERE rc.country.id = :countryId AND rc.rally.id = :rallyId", RallyCountry.class);
        qry.setParameter("rallyId", rallyId);
        qry.setParameter("countryId", countryId);
        List<RallyCountry> countries = qry.getResultList();
        return countries.isEmpty() ? null : countries.get(0);
    }
    
    @Override
    public List<QuestionAnswer> findAnswersByQuestionId(Integer questionId) {
        TypedQuery<QuestionAnswer> qry = entityManager.createQuery("SELECT qa FROM QuestionAnswer qa WHERE qa.question.id = :questionId", QuestionAnswer.class);
        qry.setParameter("questionId", questionId);
        return qry.getResultList();
    }
    
    @Override
    public QuestionAnswer findAnswerById(Integer questionAnswerId) {
        return entityManager.find(QuestionAnswer.class, questionAnswerId);
    }

    @Override
    public List<Rankin> findRankinsByRallyId(Integer rallyId) {
        TypedQuery<Rankin> qry = entityManager.createNamedQuery("Rankin.findRankinsByRallyId", Rankin.class);
        qry.setParameter("rallyId", rallyId);
        return qry.getResultList();
    }
    
}
