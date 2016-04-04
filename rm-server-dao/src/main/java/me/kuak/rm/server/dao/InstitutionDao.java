/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kuak.rm.server.dao;

import java.util.List;
import me.kuak.rm.server.model.Institution;

/**
 *
 * @author Jose
 */
public interface InstitutionDao {
    public List<Institution> findAllInstitutions();
}
