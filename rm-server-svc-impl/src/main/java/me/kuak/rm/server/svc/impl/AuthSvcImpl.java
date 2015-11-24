package me.kuak.rm.server.svc.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ValidationException;

import me.kuak.rm.server.dao.AccessTokenDao;
import me.kuak.rm.server.dao.GroupDao;
import me.kuak.rm.server.model.AccessToken;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.svc.AuthSvc;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Stateless
public class AuthSvcImpl implements AuthSvc {

    private final SessionTokenGenerator tokenGenerator = new SessionTokenGenerator();

    @EJB
    private GroupDao groupDao;

    @EJB
    private AccessTokenDao accessTokenDao;

    @Override
    public AccessToken authenticate(String user, String password) throws ValidationException, NoSuchAlgorithmException {
        AccessToken accessToken;
        Group group = groupDao.findByUser(user);
        if (group != null) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] sendedPass = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : sendedPass) {
                sb.append(String.format("%02x", b & 0xff));
            }
            String strSendedPass = sb.toString();
            if (strSendedPass != null && strSendedPass.equals(group.getPassword())) {
                accessToken = generateAccessToken(group);
                accessTokenDao.save(accessToken);
            } else {
                throw new ValidationException("Contraseña inválida");
            }
        } else {
            throw new ValidationException("Grupo no existente");
        }
        return accessToken;
    }

    private AccessToken generateAccessToken(Group group) {
        AccessToken at = new AccessToken();
        at.setGroup(group);
        at.setAuthenticationDate(new Date());
        at.setToken(tokenGenerator.generateSessionToken());
        return at;
    }

    public final class SessionTokenGenerator {
        private SecureRandom random = new SecureRandom();
        public String generateSessionToken() {
            return new BigInteger(130, random).toString(32);
        }
    }

}
