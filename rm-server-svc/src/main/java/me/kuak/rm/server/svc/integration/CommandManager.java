package me.kuak.rm.server.svc.integration;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Singleton
@Startup
public class CommandManager {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
        for (InitializationCommand command : getInitializationCommands()) {
            if (!command.isExecuted()) {
                command.execute();
            }
        }
    }

    private List<InitializationCommand> getInitializationCommands() {
        List<InitializationCommand> result = new ArrayList<>();
        result.add(new CountryCreationCommand(em));
        result.add(new ResourcesCreationCommand(em));
        result.add(new DemoRallyCreationCommand(em));
        result.add(new EntityConfigurationCreationCommand(em));
        return result;
    }

}
