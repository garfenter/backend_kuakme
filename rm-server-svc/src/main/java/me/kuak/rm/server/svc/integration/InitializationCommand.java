package me.kuak.rm.server.svc.integration;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import me.kuak.rm.server.model.integration.Command;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public abstract class InitializationCommand {

    private static final Logger logger = Logger.getLogger(InitializationCommand.class.getSimpleName());

    private final EntityManager em;

    public InitializationCommand(EntityManager em) {
        this.em = em;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    protected void complete() {
        Command command = new Command();
        command.setName(getName());
        command.setVersion(getVersion());
        command.setExecutionDate(new Date());
        command.setExecuted(Boolean.TRUE);
        em.persist(command);
        logger.log(Level.INFO, "Comando de inicializacion {0} ejecutado satisfactoriamente.", this.getName());
    }

    protected boolean isExecuted() {
        TypedQuery<Command> qry = em.createQuery("SELECT c FROM Command c WHERE c.executed = :executed AND c.name = :name AND c.version = :version", Command.class);
        qry.setParameter("executed", Boolean.TRUE);
        qry.setParameter("name", getName());
        qry.setParameter("version", getVersion());
        return !qry.getResultList().isEmpty();
    }

    abstract void execute();

    abstract String getName();

    abstract Integer getVersion();

}
