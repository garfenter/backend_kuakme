package me.kuak.rm.server.svc.integration;

import javax.persistence.EntityManager;
import me.kuak.rm.server.model.RmResource;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class ResourcesCreationCommand extends InitializationCommand {

    public ResourcesCreationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        createResource(new RmResource("/assets/images/avatars/avatar1.png", "avatar1.png", "png", "image/png", "avatar", "avatar1", "avatar1"));
        createResource(new RmResource("/assets/images/avatars/avatar2.png", "avatar2.png", "png", "image/png", "avatar", "avatar2", "avatar2"));
        createResource(new RmResource("/assets/images/avatars/avatar3.png", "avatar3.png", "png", "image/png", "avatar", "avatar3", "avatar3"));
        createResource(new RmResource("/assets/images/avatars/avatar4.png", "avatar4.png", "png", "image/png", "avatar", "avatar4", "avatar4"));
        createResource(new RmResource("/assets/images/avatars/avatar5.png", "avatar5.png", "png", "image/png", "avatar", "avatar5", "avatar5"));
        createResource(new RmResource("/assets/images/avatars/avatar6.png", "avatar6.png", "png", "image/png", "avatar", "avatar6", "avatar6"));
        createResource(new RmResource("/assets/images/avatars/avatar7.png", "avatar7.png", "png", "image/png", "avatar", "avatar7", "avatar7"));
        createResource(new RmResource("/assets/images/transports/transport1.png", "transport1.png", "png", "image/png", "transport", "transport1", "transport1"));
        createResource(new RmResource("/assets/images/transports/transport2.png", "transport2.png", "png", "image/png", "transport", "transport2", "transport2"));
        createResource(new RmResource("/assets/images/transports/transport3.png", "transport3.png", "png", "image/png", "transport", "transport3", "transport3"));
        complete();
    }

    @Override
    String getName() {
        return "resources-creation";
    }

    @Override
    Integer getVersion() {
        return 1;
    }

    private void createResource(RmResource rmResource) {
        getEntityManager().persist(rmResource);
    }

}
