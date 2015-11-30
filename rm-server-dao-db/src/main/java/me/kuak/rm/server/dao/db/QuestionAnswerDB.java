/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kuak.rm.server.dao.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.kuak.rm.server.dao.QuestionAnswerDao;
import me.kuak.rm.server.model.QuestionAnswer;

/**
 *
 * @author EduardoAlejandro
 */
public class QuestionAnswerDB implements QuestionAnswerDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<QuestionAnswer> get(Integer question) {
        throw new UnsupportedOperationException();
    }
    
}
