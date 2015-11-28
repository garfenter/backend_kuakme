package me.kuak.rm.server.dao;

import me.kuak.rm.server.model.RmResource;

import java.util.List;

/**
 * Created by guyo on 11/28/15.
 */
public interface FileDao {

    RmResource save(RmResource resource);

    RmResource findById(Integer id);

    List<RmResource> findByParentAndType(Integer parent, String type);

}
