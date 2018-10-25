package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

@CrossOrigin
@RestController
public interface ISessionController {

    @RequestMapping(value = Routes.LOGIN_ROUTE, method = RequestMethod.POST)
    void login() throws Exception;

}
