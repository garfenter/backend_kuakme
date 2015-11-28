package me.kuak.rm.server.dao;

import me.kuak.rm.server.model.AccessToken;

/**
 * Created by guyo on 11/24/15.
 */
public interface AccessTokenDao {

    void save(AccessToken accessToken);

    AccessToken findAccessTokenByCode(String accessToken);
    
}
