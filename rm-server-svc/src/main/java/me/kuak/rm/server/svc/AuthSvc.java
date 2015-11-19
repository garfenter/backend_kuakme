package me.kuak.rm.server.svc;

import me.kuak.rm.server.model.AccessToken;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface AuthSvc {

    public AccessToken authenticate(String user, String password);
    
}
