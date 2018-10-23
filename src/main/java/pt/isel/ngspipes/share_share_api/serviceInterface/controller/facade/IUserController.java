package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

import java.util.Collection;

@CrossOrigin
@RestController
public interface IUserController {

    class PasswordData {
        public String currentPassword;
        public String newPassword;
    }



    @RequestMapping(value = Routes.GET_ALL_USERS_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<User>> getAll() throws Exception;

    @RequestMapping(value = Routes.GET_USER_ROUTE, method = RequestMethod.GET)
    ResponseEntity<User> get(@PathVariable String userName) throws Exception;

    @RequestMapping(value = Routes.CREATE_USER_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Void> insert(@RequestBody User user) throws Exception;

    @RequestMapping(value = Routes.UPDATE_USER_ROUTE, method = RequestMethod.PUT)
    ResponseEntity<Void> update(@PathVariable String userName, @RequestBody User user) throws Exception;

    @RequestMapping(value = Routes.DELETE_USER_ROUTE, method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@PathVariable String userName) throws Exception;

    @RequestMapping(value = Routes.GET_USER_IMAGE_ROUTE, method = RequestMethod.GET)
    ResponseEntity<byte[]> getImage(@PathVariable String userName) throws Exception;

    @RequestMapping(value = Routes.CHANGE_USER_IMAGE_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Void> changeImage(@PathVariable String userName, @RequestPart(value = "file") MultipartFile file) throws Exception;

    @RequestMapping(value = Routes.CHANGE_USER_PASSWORD_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Void> changePassword(@PathVariable String userName, @RequestBody PasswordData data) throws Exception;

}
