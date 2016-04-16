package me.kuak.rm.server.dao.db;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import me.kuak.rm.server.dao.RallyDao;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.model.MultipleValueQuestion;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.QuestionAnswer;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.RallyCountry;
import me.kuak.rm.server.model.Ranking;
import me.kuak.rm.server.model.Registration;
import me.kuak.rm.server.model.RegistrationCountry;
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
    public Question findQuestionByQuestionId(Integer questionId) {
        TypedQuery<Question> qry = entityManager.createQuery("SELECT q FROM Question q WHERE q.id = :questionId", Question.class);
        qry.setParameter("questionId", questionId);
        return qry.getResultList().get(0);
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
    public Registration register(Integer rallyId, Integer groupId, List<Country> countries) {
        Registration registration = new Registration();
        registration.setRegistrationDate(new Date());
        registration.setRally(entityManager.find(Rally.class, rallyId));
        registration.setGroup(entityManager.find(Group.class, groupId));
        entityManager.merge(registration);
        for (Country country : countries) {
            RegistrationCountry registrationCountry = new RegistrationCountry();
            registrationCountry.setCountry(country);
            registrationCountry.setCreationDate(new Date());
            registrationCountry.setRegistration(registration);
            registrationCountry.setState(StatusType.ACTIVE);
            entityManager.merge(registrationCountry);
        }
        return registration;
    }

    @Override
    public Registration registerCountries(Integer rallyId, Integer groupId, List<Country> countries) {
        TypedQuery<Registration>  q = entityManager.createQuery("SELECT r FROM Registration r WHERE r.group.id = :groupId AND r.rally.id = :rallyId", Registration.class);
        q.setParameter("groupId", groupId);
        q.setParameter("rallyId", rallyId);
        Registration registration = q.getSingleResult();
        
        for (Country country : countries) {
            RegistrationCountry registrationCountry = new RegistrationCountry();
            registrationCountry.setCountry(country);
            registrationCountry.setCreationDate(new Date());
            registrationCountry.setRegistration(registration);
            registrationCountry.setState(StatusType.ACTIVE);
            entityManager.merge(registrationCountry);
        }
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
    public List<Ranking> findRankingsByRallyId(Integer rallyId) {
        TypedQuery<Ranking> qry = entityManager.createNamedQuery("Ranking.findRankingsByRallyId", Ranking.class);
        qry.setParameter("rallyId", rallyId);
        return qry.getResultList();
    }

    @Override
    public QuestionAnswer findAnswerById(Integer groupId, Integer questionId) {
        TypedQuery<QuestionAnswer> qry = entityManager.createQuery("SELECT qa FROM QuestionAnswer qa WHERE qa.group.id = :groupId AND qa.question.id = :questionId", QuestionAnswer.class);
        qry.setParameter("groupId", groupId);
        qry.setParameter("questionId", questionId);
        List<QuestionAnswer> result = qry.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

}
