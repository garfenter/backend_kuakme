package me.kuak.rm.server.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table(name = "country")
@DiscriminatorValue("country")
public class Country extends RallyObject implements Serializable {

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "informative_file")
    private RmResource informativeFile;
    
    public Country() {
    }

    public Country(String name) {
        setName(name);
    }

    public RmResource getInformativeFile() {
        return informativeFile;
    }

    public void setInformativeFile(RmResource informativeFile) {
        this.informativeFile = informativeFile;
    }

}
