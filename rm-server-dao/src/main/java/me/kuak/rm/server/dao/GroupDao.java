package me.kuak.rm.server.dao;

import me.kuak.rm.server.model.Group;

import java.util.List;
import me.kuak.rm.server.model.QuestionAnswer;

/**
 * Created by guyo on 11/24/15.
 */
public interface GroupDao {

    Group findByUser(String user);

    List<Group> findAll();
    
    QuestionAnswer findActiveQuestionByGroup(Integer groupId);
    
}
