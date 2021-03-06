package me.kuak.rm.server.dao.db;

import java.util.Arrays;
import me.kuak.rm.server.dao.GroupDao;
import me.kuak.rm.server.model.Group;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import me.kuak.rm.server.model.QuestionAnswer;
import me.kuak.rm.server.model.QuestionAnswerState;

/**
 * Created by guyo on 11/24/15.
 */
@Stateless
public class GroupDB implements GroupDao {

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
    
    
    @Override
    public List<Group> findByUserRole(Integer position, Integer limit, String role){
        TypedQuery<Group> query = entityManager.createQuery("SELECT g FROM Group g WHERE g.role = :role", Group.class);
        if(position != null){
            query.setFirstResult(position);
        }
        if(limit != null){
            query.setMaxResults(limit);
        }
        query.setParameter("role", role);
        return query.getResultList();
    }
    
    @Override
    public QuestionAnswer findActiveQuestionByGroup(Integer groupId) {
        TypedQuery<QuestionAnswer> qry = entityManager.createQuery("SELECT q FROM QuestionAnswer q WHERE q.registration.group.id = :groupId AND q.questionAnswerState IN :questionAnswerState AND q.question.type = :type", QuestionAnswer.class);
        qry.setParameter("groupId", groupId);
        qry.setParameter("questionAnswerState", Arrays.asList(QuestionAnswerState.ACTIVE, QuestionAnswerState.SUBMITTED));
        qry.setParameter("type", "question");
        List<QuestionAnswer> result = qry.getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

}
