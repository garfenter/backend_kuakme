package me.kuak.rm.server.svc.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import me.kuak.rm.server.dao.QuestionDao;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.svc.QuestionSvc;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class QuestionSvcImpl implements QuestionSvc {

    @EJB
    QuestionDao questionDao;
    
    @Override
    public void createQuestion(Question question) {
        questionDao.createQuestion(question);
    }
    
}
