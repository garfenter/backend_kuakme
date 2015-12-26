package me.kuak.rm.server.svc;

import java.util.List;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.model.Person;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface GroupSvc {

    public List<Group> findAllActiveGroups();

    public Group createGroup(Group group);

    public Person addGroupMember(Person person);

    public List<Person> findGroupMembersByGroupId(Integer groupId);
    
    public List<Group> findAll();

}
