package pt.isel.ngspipes.share_share_api.logic.operation.accessToken;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.AccessToken;
import pt.isel.ngspipes.share_core.logic.service.accessToken.IAccessTokenService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

@Service
public class AccessTokenOperation implements IAccessTokenOperation {

    @Autowired
    private IAccessTokenService tokenService;



    @Override
    public Collection<AccessToken> getAllTokens() throws ServiceException {
        return tokenService.getAll();
    }

    @Override
    public AccessToken getToken(int id) throws ServiceException {
        return tokenService.getById(id);
    }

    @Override
    public void createToken(AccessToken token) throws ServiceException {
        tokenService.insert(token);
    }

    @Override
    public void deleteToken(int id) throws ServiceException {
        tokenService.delete(id);
    }

    @Override
    public void updateToken(AccessToken token) throws ServiceException {
        tokenService.update(token);
    }

    @Override
    public Collection<AccessToken> getTokensOfUser(String userName) throws ServiceException {
        return tokenService.getTokensOfUser(userName);
    }

    @Override
    public AccessToken getAccessTokenByToken(String token) throws ServiceException {
        return tokenService.getAccessTokenByToken(token);
    }

    @Override
    public void deleteTokensOfUser(String userName) throws ServiceException {
        tokenService.deleteTokensOfUser(userName);
    }

}
