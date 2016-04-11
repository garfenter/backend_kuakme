package me.kuak.rm.server.svc.integration;

import java.util.Date;
import javax.persistence.EntityManager;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.StatusType;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class CountryCreationCommand extends InitializationCommand {

    public CountryCreationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        createCountry(new Country("Guatemala"));
        createCountry(new Country("Brasil"));
        createCountry(new Country("Panama"));
        createCountry(new Country("Peru"));
        complete();
    }

    private void createCountry(Country country) {
        country.setCreationDate(new Date());
        country.setStatus(StatusType.ACTIVE);
        getEntityManager().persist(country);
    }

    @Override
    String getName() {
        return "country-creation";
    }

    @Override
    Integer getVersion() {
        return 1;
    }

}
