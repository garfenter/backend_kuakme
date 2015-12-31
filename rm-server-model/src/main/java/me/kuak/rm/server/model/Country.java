package me.kuak.rm.server.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table(name = "country")
@DiscriminatorValue("country")
public class Country extends RallyObject implements Serializable {

    public Country() {
    }

    public Country(String name) {
        setName(name);
    }

}
