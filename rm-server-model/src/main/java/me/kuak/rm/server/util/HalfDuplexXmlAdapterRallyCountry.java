package me.kuak.rm.server.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import me.kuak.rm.server.model.RallyCountry;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class HalfDuplexXmlAdapterRallyCountry extends XmlAdapter<RallyCountry, RallyCountry> {

    private static final Logger logger = Logger.getGlobal();

    @Override
    public RallyCountry unmarshal(RallyCountry v) throws Exception {
        return v;
    }

    @Override
    public RallyCountry marshal(RallyCountry v) throws Exception {
        return null;
    }

}
