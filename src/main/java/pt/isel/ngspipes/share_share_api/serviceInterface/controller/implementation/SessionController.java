package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.ISessionController;

@RestController
public class SessionController implements ISessionController {

    @Override
    public void login() throws Exception { }

}
