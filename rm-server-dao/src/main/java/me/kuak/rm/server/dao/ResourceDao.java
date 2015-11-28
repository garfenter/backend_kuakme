package me.kuak.rm.server.dao;

import java.util.List;
import me.kuak.rm.server.model.RmResource;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface ResourceDao {

    public List<RmResource> findResourcesByType(String type);
    
    public RmResource createResource(RmResource resource);
    
}
