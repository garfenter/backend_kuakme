package me.kuak.rm.server.web.rs;

import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import me.kuak.rm.server.model.AccessToken;
import me.kuak.rm.server.svc.AuthSvc;
import me.kuak.rm.server.web.rs.model.RsResponse;

import static me.kuak.rm.server.web.rs.model.RsResponse.CODE_VALIDATION_ERROR;
import static me.kuak.rm.server.web.rs.model.RsResponse.CODE_UNKNOWN_ERROR;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("auth")
public class AuthEndpoint {

    @Inject
    AuthSvc authSvc;

    @POST
    @Path("authenticate")
    public RsResponse<AccessToken> authenticate(@QueryParam("user") String user, @QueryParam("password") String password) {
        try {
            RsResponse<AccessToken> result = new RsResponse<>(authSvc.authenticate(user, password));
            return result;
        } catch (ValidationException v) {
            RsResponse<AccessToken> result = new RsResponse<>(null);
            result.setCode(CODE_VALIDATION_ERROR);
            return result;
        } catch (Throwable t) {
            RsResponse<AccessToken> result = new RsResponse<>(null);
            result.setCode(CODE_UNKNOWN_ERROR);
            return result;
        }
    }
}
