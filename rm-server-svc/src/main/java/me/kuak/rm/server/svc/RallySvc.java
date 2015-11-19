package me.kuak.rm.server.svc;

import java.util.List;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.QuestionAnswer;
import me.kuak.rm.server.model.QuestionResult;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.Registration;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface RallySvc {

    public List<Rally> findAllActiveRallies();

    public Registration suscribeRally(Integer rallyId, Integer groupId);

    public List<Country> findCountriesByRally(Integer rallyId);

    public List<Question> goToCountry(Integer rallyId, Integer groupId, Integer countryId);

    public List<Question> findRallyByCountryQuestions(Integer rallyId, Integer countryId);

    public List<Question> findRallyQuestions(Integer rallyId);

    public QuestionResult answerQuestion(Integer rallyId, Integer countryId, Integer questionId, QuestionAnswer answer);
}
