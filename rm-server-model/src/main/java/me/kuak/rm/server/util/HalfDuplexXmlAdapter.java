
package me.kuak.rm.server.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class HalfDuplexXmlAdapter extends XmlAdapter<Object, Object> {
    private static final Logger logger = Logger.getGlobal();

    @Override
    public Object unmarshal(Object v) throws Exception {
        logger.log(Level.INFO,"UNMARSHAL");
        return v;
    }

    @Override
    public Object marshal(Object v) throws Exception {
        logger.log(Level.INFO, "MARSHAL");
        return null;
    }

}