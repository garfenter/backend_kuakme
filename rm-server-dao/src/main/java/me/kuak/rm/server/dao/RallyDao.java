package me.kuak.rm.server.dao;

import java.util.List;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.MultipleValueQuestion;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.QuestionAnswer;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.RallyCountry;
import me.kuak.rm.server.model.Rankin;
import me.kuak.rm.server.model.Registration;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface RallyDao {

    public List<Question> findQuestionsByRallyIdAndCountryId(Integer rallyId, Integer countryId);
    
    public Question findQuestionByQuestionId(Integer questionId);
    
    public List<QuestionAnswer> findAnswersByQuestionId(Integer questionId);
    
    public QuestionAnswer findAnswerById(Integer questionAnswerId);
    
    public List<MultipleValueQuestion> findMultipleValueQuestionsByRallyIdAndCountryId(Integer rallyId, Integer countryId);
    
    public List<Rally> findActiveRallies();
    
    public List<RallyCountry> findCountriesByRally(Integer rallyId);
    
    public Registration register(Integer rallyId, Integer groupId);  
    
    public RallyCountry findRallyCountry(Integer rallyId, Integer countryId);
    
    public List<Rankin> findRankinsByRallyId(Integer rallyId);
}

