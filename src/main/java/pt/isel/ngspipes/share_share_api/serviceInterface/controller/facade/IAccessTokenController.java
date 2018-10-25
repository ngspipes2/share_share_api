package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isel.ngspipes.share_core.logic.domain.AccessToken;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

import java.util.Collection;

@CrossOrigin
@RestController
public interface IAccessTokenController {

    @RequestMapping(value = Routes.CREATE_ACCESS_TOKEN_ROUTE, method = RequestMethod.POST)
    ResponseEntity<String> insert(@RequestBody AccessToken token) throws Exception;

    @RequestMapping(value = Routes.DELETE_ACCESS_TOKEN_ROUTE, method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@RequestBody Integer tokenId) throws Exception;

    @RequestMapping(value = Routes.GET_ACCESS_TOKENS_OF_USER, method = RequestMethod.GET, params = "userName")
    ResponseEntity<Collection<AccessToken>> getAccessTokensOfUser(@RequestParam String userName) throws Exception;

}
