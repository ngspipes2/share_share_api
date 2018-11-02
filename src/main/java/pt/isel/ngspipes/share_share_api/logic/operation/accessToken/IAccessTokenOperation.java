package pt.isel.ngspipes.share_share_api.logic.operation.accessToken;

import pt.isel.ngspipes.share_core.logic.domain.AccessToken;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IAccessTokenOperation {

    Collection<AccessToken> getAllTokens() throws ServiceException;

    AccessToken getToken(int id) throws ServiceException;

    void createToken(AccessToken token) throws ServiceException;

    void deleteToken(int id) throws ServiceException;

    void updateToken(AccessToken token) throws ServiceException;

    Collection<AccessToken> getTokensOfUser(String userName) throws ServiceException;

    AccessToken getAccessTokenByToken(String token) throws ServiceException;

    void deleteTokensOfUser(String userName) throws ServiceException;

}
