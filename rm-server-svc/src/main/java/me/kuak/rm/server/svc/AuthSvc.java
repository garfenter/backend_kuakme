package me.kuak.rm.server.svc;

import me.kuak.rm.server.model.AccessToken;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface AuthSvc {

    AccessToken authenticate(String user, String password) throws Exception;
    
    AccessToken findAccessTokenByCode(String tokenCode);
    
}
