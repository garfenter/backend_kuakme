/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kuak.rm.server.svc.integration;

import java.util.Date;
import javax.persistence.EntityManager;
import me.kuak.rm.server.model.Institution;
import me.kuak.rm.server.model.StatusType;

/**
 *
 * @author Jose
 */
public class InstitutionCreationCommand extends InitializationCommand{

    public InstitutionCreationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        createInstitution(new Institution("Escuela No. 1"));
        createInstitution(new Institution("Escuela No. 2"));
        createInstitution(new Institution("Escuela No. 3"));
        createInstitution(new Institution("Escuela No. 4"));
        createInstitution(new Institution("Escuela No. 5"));
        complete();
    }
    
    private void createInstitution(Institution institution){
        institution.setCreationDate(new Date());
        institution.setStatus(StatusType.ACTIVE);
        getEntityManager().persist(institution);
    }

    @Override
    String getName() {
        return "institution-creation";
    }

    @Override
    Integer getVersion() {
        return 1;
    }
    
}
