
package me.kuak.rm.server.util;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import me.kuak.rm.server.model.admin.EntityConfiguration;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class HalfDuplexXmlAdapter extends XmlAdapter<EntityConfiguration, EntityConfiguration> {
    private static final Logger logger = Logger.getGlobal();

    @Override
    public EntityConfiguration unmarshal(EntityConfiguration v) throws Exception {
        logger.log(Level.INFO,"UNMARSHAL");
        return v;
    }

    @Override
    public EntityConfiguration marshal(EntityConfiguration v) throws Exception {
        logger.log(Level.INFO, "MARSHAL");
        return null;
    }

}