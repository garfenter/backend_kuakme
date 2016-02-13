package me.kuak.rm.server.util;

import java.util.logging.Logger;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import me.kuak.rm.server.model.admin.EntityConfiguration;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class HalfDuplexXmlAdapterEntityConfiguration extends XmlAdapter<EntityConfiguration, EntityConfiguration> {

    private static final Logger logger = Logger.getGlobal();

    @Override
    public EntityConfiguration unmarshal(EntityConfiguration v) throws Exception {
        return v;
    }

    @Override
    public EntityConfiguration marshal(EntityConfiguration v) throws Exception {
        return null;
    }

}
