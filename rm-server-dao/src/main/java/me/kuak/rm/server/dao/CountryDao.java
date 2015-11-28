package me.kuak.rm.server.dao;

import java.util.List;
import me.kuak.rm.server.model.Country;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface CountryDao {

    public List<Country> findAllCountries();

}
