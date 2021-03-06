package me.kuak.rm.server.dao;

import java.util.List;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.MultipleValueQuestion;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.QuestionAnswer;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.RallyCountry;
import me.kuak.rm.server.model.Ranking;
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
    
    public QuestionAnswer findAnswerByGroupIdAndQuestionId(Integer groupId, Integer questionId);
    
    public List<MultipleValueQuestion> findMultipleValueQuestionsByRallyIdAndCountryId(Integer rallyId, Integer countryId);
    
    public List<Rally> findActiveRallies();
    
    public List<Rally> findActiveRalliesByLevel(Integer level);
    
    public List<RallyCountry> findCountriesByRally(Integer rallyId);
    
    public Registration register(Integer rallyId, Integer groupId);
    
    public Registration updateRegistration(Registration registration);
    
    public Registration registerCountries(Integer rallyId, Integer groupId, List<Country> countries);
    
    public RallyCountry findRallyCountry(Integer rallyId, Integer countryId);
    
    public List<Ranking> findRankingsByRallyId(Integer rallyId);
}

