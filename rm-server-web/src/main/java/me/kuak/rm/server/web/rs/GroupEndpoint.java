package me.kuak.rm.server.web.rs;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.svc.GroupSvc;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("groups")
@Stateless
public class GroupEndpoint {

    @EJB
    private GroupSvc groupSvc;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Group createGroup(Group group) {
        return groupSvc.createGroup(group);
    }

}
