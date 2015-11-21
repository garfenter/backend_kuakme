package me.kuak.rm.server.svc.impl;

import javax.ejb.Stateless;
import me.kuak.rm.server.model.AccessToken;
import me.kuak.rm.server.svc.AuthSvc;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class AuthSvcImpl implements AuthSvc {

    @Override
    public AccessToken authenticate(String user, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
