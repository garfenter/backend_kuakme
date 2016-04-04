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
        createCountry(new Country("Brazil"));
        createCountry(new Country("Panama"));
        createCountry(new Country("Mexico"));
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
