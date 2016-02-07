package me.kuak.rm.server.dao;

import java.util.List;
import me.kuak.rm.server.model.RallyObject;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface RallyObjectDao {

    void createRallyObject(RallyObject rallyObject);
    
    RallyObject findRallyObjectById(Integer id, Class clazz);
    
    List<? extends RallyObject> findRallyObjectByClass(Integer position, Integer limit, Class clazz);
    
    void updateRallyObject(RallyObject rallyObject);

}
