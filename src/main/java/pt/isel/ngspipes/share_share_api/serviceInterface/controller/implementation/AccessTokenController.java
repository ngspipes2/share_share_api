package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.share_core.logic.domain.AccessToken;
import pt.isel.ngspipes.share_core.logic.service.accessToken.IAccessTokenService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.IPermissionService;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IAccessTokenController;

@RestController
public class AccessTokenController implements IAccessTokenController {

    @Autowired
    private IAccessTokenService accessTokenService;
    @Autowired
    private IPermissionService<AccessToken, Integer> permissionService;



    @Override
    public ResponseEntity<String> insert(@RequestBody AccessToken token) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        accessTokenService.insert(token);

        return new ResponseEntity<>(token.getToken(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> delete(@RequestBody Integer tokenId) throws Exception {
        if(!isValidAccess(tokenId, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        accessTokenService.delete(tokenId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean isValidAccess(Integer tokenId, Access.Operation operation) throws ServiceException {
        Access<Integer> access = new Access<>(getCurrentUserName(), operation, tokenId);
        return permissionService.isValidAccess(access);
    }

    private String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
