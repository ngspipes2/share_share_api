package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.share_core.logic.domain.AccessToken;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.ICurrentUserSupplier;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.IPermissionService;
import pt.isel.ngspipes.share_share_api.logic.operation.accessToken.IAccessTokenOperation;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IAccessTokenController;

import java.util.Collection;

@RestController
public class AccessTokenController implements IAccessTokenController {

    @Autowired
    private IAccessTokenOperation accessTokenOperation;
    @Autowired
    private IPermissionService<AccessToken, Integer> permissionService;
    @Autowired
    private ICurrentUserSupplier currentUserSupplier;



    @Override
    public ResponseEntity<AccessToken> get(@PathVariable  Integer tokenId) throws Exception {
        AccessToken token = accessTokenOperation.getToken(tokenId);

        if(token == null)
            return new ResponseEntity<>((AccessToken) null, HttpStatus.OK);

        if(!token.getOwner().getUserName().equals(getCurrentUserName()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        ControllerUtils.hidePasswordOfAccessToken(token);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewAccessTokenData> insert(@RequestBody AccessToken token) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        accessTokenOperation.createToken(token);

        NewAccessTokenData data = new NewAccessTokenData();
        data.id = token.getId();
        data.token = token.getToken();

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable Integer tokenId, @RequestBody AccessToken token) throws Exception {
        if(!isValidAccess(tokenId, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        accessTokenOperation.updateToken(token);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer tokenId) throws Exception {
        if(!isValidAccess(tokenId, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        accessTokenOperation.deleteToken(tokenId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<AccessToken>> getAccessTokensOfUser(@RequestParam String userName) throws Exception {
        if(!userName.equals(getCurrentUserName()) && !currentUserSupplier.get().getRole().equals(User.Role.ADMIN))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<AccessToken> tokens = accessTokenOperation.getTokensOfUser(userName);

        ControllerUtils.hidePasswordsOfAccessTokens(tokens);

        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }

    private boolean isValidAccess(Integer tokenId, Access.Operation operation) throws ServiceException {
        Access<Integer> access = new Access<>(getCurrentUserName(), operation, tokenId);
        return permissionService.isValidAccess(access);
    }

    private String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
