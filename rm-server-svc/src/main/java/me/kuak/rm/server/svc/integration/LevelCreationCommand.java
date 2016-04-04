/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kuak.rm.server.svc.integration;

import java.util.Date;
import javax.persistence.EntityManager;
import me.kuak.rm.server.model.Level;
import me.kuak.rm.server.model.StatusType;

/**
 *
 * @author Jose
 */
public class LevelCreationCommand extends InitializationCommand{

    public LevelCreationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        createLevel(new Level("1ro y 2do Primaria", 1));
        createLevel(new Level("3ro y 4to Primaria", 2));
        createLevel(new Level("5to y 6to Primaria", 3));
        complete();
    }
    
    private void createLevel(Level level){
        level.setCreationDate(new Date());
        level.setStatus(StatusType.ACTIVE);
        getEntityManager().persist(level);
    }

    @Override
    String getName() {
        return "level-creation";
    }

    @Override
    Integer getVersion() {
        return 1;
    }
    
}
