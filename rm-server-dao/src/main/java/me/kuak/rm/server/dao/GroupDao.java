package me.kuak.rm.server.dao;

import me.kuak.rm.server.model.Group;

/**
 * Created by guyo on 11/24/15.
 */
public interface GroupDao {

    Group findByUser(String user);

}
