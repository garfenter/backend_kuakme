package me.kuak.rm.server.svc.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import me.kuak.rm.server.dao.RallyObjectDao;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.model.Person;
import me.kuak.rm.server.svc.GroupsSvc;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class GroupSvcImpl implements GroupsSvc {

    @EJB
    private RallyObjectDao rallyObjectDao;

    @Override
    public List<Group> findAllActiveGroups() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Group createGroup(Group group) {
        rallyObjectDao.createRallyObject(group);
        return group;
    }

    @Override
    public Person addGroupMember(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> findGroupMembersByGroupId(Integer groupId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
