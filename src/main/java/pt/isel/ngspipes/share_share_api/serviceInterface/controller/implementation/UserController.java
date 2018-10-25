package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pt.isel.ngspipes.share_core.logic.domain.Image;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.IPermissionService;
import pt.isel.ngspipes.share_core.logic.service.user.IUserService;
import pt.isel.ngspipes.share_share_api.logic.service.IOperationsService;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IUserController;

import java.util.Collection;

@RestController
public class UserController implements IUserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IPermissionService<User, String> permissionService;
    @Autowired
    private IOperationsService operationsService;



    @Override
    public ResponseEntity<Collection<User>> getAll() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<User> users = userService.getAll();

        ControllerUtils.hidePasswords(users);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> get(@PathVariable String userName) throws Exception {
        if(!isValidAccess(userName, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = userService.getById(userName);

        ControllerUtils.hidePassword(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> insert(@RequestBody User user) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if(user.getRole().equals(User.Role.ADMIN))
            throw new ServiceException("Create Admin user not allowed!");

        operationsService.createUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable String userName, @RequestBody User user) throws Exception {
        if(!isValidAccess(userName, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if(!userName.equals(user.getUserName()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable String userName) throws Exception {
        if(!isValidAccess(userName, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        operationsService.deleteUser(userName);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> getImage(@PathVariable String userName) throws Exception {
        if(!isValidAccess(userName, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Image image = userService.getUserImage(userName);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image == null ? null : image.getContent());
    }

    @Override
    public ResponseEntity<Void> changeImage(@PathVariable String userName, @RequestPart(value = "file") MultipartFile file) throws Exception {
        if(!isValidAccess(userName, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Image image = new Image(null, file.getBytes());
        userService.setUserImage(userName, image);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteImage(@PathVariable String userName) throws Exception {
        if(!isValidAccess(userName, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        userService.setUserImage(userName, null);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> changePassword(@PathVariable String userName, @RequestBody PasswordData data) throws Exception {
        if(!isValidAccess(userName, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        userService.changePassword(userName, data.currentPassword, data.newPassword);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<String>> getUsersNames() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<String> names = userService.getUsersNames();

        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    private boolean isValidAccess(String userName, Access.Operation operation) throws ServiceException  {
        Access<String> access = new Access<>(getCurrentUserName(), operation, userName);
        return permissionService.isValidAccess(access);
    }

    private String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
