package me.kuak.rm.server.svc;

import java.io.InputStream;
import me.kuak.rm.server.model.RmResource;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface FileSvc {

    public RmResource uploadFile(InputStream data, String fileName);
}
