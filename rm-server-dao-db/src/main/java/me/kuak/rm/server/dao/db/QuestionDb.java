package me.kuak.rm.server.dao.db;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.kuak.rm.server.dao.QuestionDao;
import me.kuak.rm.server.model.Question;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class QuestionDb implements QuestionDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createQuestion(Question question) {
        entityManager.persist(question);
    }

}
