/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kuak.rm.server.dao.db;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import me.kuak.rm.server.dao.InstitutionDao;
import me.kuak.rm.server.model.Institution;
import me.kuak.rm.server.model.StatusType;

/**
 *
 * @author Jose
 */
@Stateless
public class InstitutionDaoDb implements InstitutionDao{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Institution> findAllInstitutions() {
        TypedQuery<Institution> qry = entityManager.createQuery("SELECT c FROM Institution c WHERE c.status = :status", Institution.class);
        qry.setParameter("status", StatusType.ACTIVE);
        return qry.getResultList();
    }
    
}
