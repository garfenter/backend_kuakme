package me.kuak.rm.server.svc.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import me.kuak.rm.server.model.Registration;
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
            if(registration.getStatus().equals(StatusType.ACTIVE)){
                registration.setSelectedCountry(selectedCountry);
                rallyDao.updateRegistration(registration);
            }
        }
        
        return group;
    }

}
