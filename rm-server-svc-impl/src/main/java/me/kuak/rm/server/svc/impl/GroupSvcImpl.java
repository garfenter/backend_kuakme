package me.kuak.rm.server.svc.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import me.kuak.rm.server.dao.GroupDao;
import me.kuak.rm.server.dao.RallyDao;
import me.kuak.rm.server.dao.RallyObjectDao;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.model.Person;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.QuestionAnswer;
import me.kuak.rm.server.model.QuestionAnswerState;
import me.kuak.rm.server.model.RallyCountry;
import me.kuak.rm.server.model.Registration;
import me.kuak.rm.server.model.RegistrationCountry;
import me.kuak.rm.server.model.StatusType;
import me.kuak.rm.server.svc.GroupSvc;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class GroupSvcImpl implements GroupSvc {

    @EJB
    private RallyObjectDao rallyObjectDao;

    @EJB
    private GroupDao groupDao;

    @EJB
    private RallyDao rallyDao;

    @Override
    public List<Group> findAllActiveGroups() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public Group createGroup(Group group) {
        try {
            String password = group.getPassword();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] sendedPass = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : sendedPass) {
                sb.append(String.format("%02x", b & 0xff));
            }
            group.setPassword(sb.toString());
            rallyObjectDao.createRallyObject(group);
            for (Person p : group.getMembers()) {
                p.setGroup(group);
            }
            rallyObjectDao.updateRallyObject(group);
            return group;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GroupSvcImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new InternalError();
        }
    }

    @Override
    public Person addGroupMember(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> findGroupMembersByGroupId(Integer groupId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Group setSelectedCountry(Integer countryId, Integer groupId) {
        Country selectedCountry = (Country) rallyObjectDao.findRallyObjectById(countryId, Country.class);
        Group group = (Group) rallyObjectDao.findRallyObjectById(groupId, Group.class);
        
        for (Registration registration : group.getRegistrations()) {
            if (registration.getStatus().equals(StatusType.ACTIVE)) {
                if(!isCountryRegistred(registration, group, selectedCountry)){
                    registration.setSelectedCountry(selectedCountry);
                    RegistrationCountry registrationCountry = new RegistrationCountry();
                    registrationCountry.setCountry(selectedCountry);
                    registrationCountry.setRegistration(registration);
                    registrationCountry.setState(StatusType.ACTIVE);
                    registrationCountry.setIndex(1);
                    registration.getRegistrationCountries().add(registrationCountry);
                    rallyDao.updateRegistration(registration);
                    createQuestionsAnswers(registration, group, selectedCountry);
                }
            }
        }

        return group;
    }

    private void createQuestionsAnswers(Registration registration, Group group, Country selectedCountry) {
        List<Question> questions = rallyDao.findQuestionsByRallyIdAndCountryId(registration.getRally().getId(), selectedCountry.getId());
        for (Question question : questions) {
            QuestionAnswer questionAnswer = new QuestionAnswer();
            questionAnswer.setRegistration(registration);
            questionAnswer.setQuestion(question);
            questionAnswer.setPoints(0);
            questionAnswer.setCreationDate(new Date());
            questionAnswer.setStatus(StatusType.ACTIVE);
            questionAnswer.setQuestionAnswerState(QuestionAnswerState.ACTIVE);
            rallyObjectDao.createRallyObject(questionAnswer);
        }
    }

    private boolean isCountryRegistred(Registration registration, Group group, Country selectedCountry) {
        for(RegistrationCountry countries: registration.getRegistrationCountries()){
            if(countries.getCountry().getId().equals(selectedCountry.getId())){
                return true;
            }
        }
        return false;
    }

}
