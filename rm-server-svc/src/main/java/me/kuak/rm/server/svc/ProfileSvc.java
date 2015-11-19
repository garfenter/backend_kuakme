package me.kuak.rm.server.svc;

import me.kuak.rm.server.model.Profile;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface ProfileSvc {

    public Profile getProfileByAccessToken(String accessToken);

    public Profile updateProfile(Profile profile);

}
