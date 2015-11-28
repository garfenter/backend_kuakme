package me.kuak.rm.server.dao.db;

import me.kuak.rm.server.dao.AccessTokenDao;
import me.kuak.rm.server.model.AccessToken;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by guyo on 11/24/15.
 */
@Stateless
public class AccessTokenDB implements AccessTokenDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(AccessToken accessToken) {
        entityManager.persist(accessToken);
    }

}
