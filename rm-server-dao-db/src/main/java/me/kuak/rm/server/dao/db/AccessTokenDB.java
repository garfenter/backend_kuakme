package me.kuak.rm.server.dao.db;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import me.kuak.rm.server.dao.AccessTokenDao;
import me.kuak.rm.server.model.AccessToken;

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

    @Override
    public AccessToken findAccessTokenByCode(String tokenCode) {
        TypedQuery<AccessToken> qry = entityManager.createQuery("SELECT a FROM AccessToken a where A.token =:tokenCode", AccessToken.class);
        qry.setParameter("tokenCode", tokenCode);
        List<AccessToken> tokens = qry.getResultList();
        return tokens.isEmpty() ? null : tokens.get(0);
    }

}
