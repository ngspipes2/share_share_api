package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.Image;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.group.IGroupService;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.IPermissionService;
import pt.isel.ngspipes.share_share_api.logic.service.IOperationsService;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IGroupController;

import java.util.Collection;

@RestController
public class GroupController implements IGroupController {

    @Autowired
    private IGroupService groupService;
    @Autowired
    private IPermissionService<Group, String> permissionService;
    @Autowired
    private IOperationsService operationsService;



    @Override
    public ResponseEntity<Collection<Group>> getAll() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<Group> groups = groupService.getAll();

        ControllerUtils.hidePasswordsOfGroups(groups);

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Group> get(@PathVariable String groupName) throws Exception {
        if(!isValidAccess(groupName, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Group group = groupService.getById(groupName);

        ControllerUtils.hidePasswordOfGroup(group);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> insert(@RequestBody Group group) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        operationsService.createGroup(group);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable String groupName, @RequestBody Group group) throws Exception {
        if(!isValidAccess(groupName, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if(!groupName.equals(group.getGroupName()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        groupService.update(group);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable String groupName) throws Exception {
        if(!isValidAccess(groupName, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        operationsService.deleteGroup(groupName);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<Group>> getGroupsOfUser(@RequestParam String userName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<Group> groups = groupService.getGroupsOfUser(userName);

        ControllerUtils.hidePasswordsOfGroups(groups);

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> getImage(@PathVariable String groupName) throws Exception {
        if(!isValidAccess(groupName, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Image image = groupService.getGroupImage(groupName);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image == null ? null : image.getContent());
    }

    @Override
    public ResponseEntity<Void> changeImage(@PathVariable String groupName, @RequestPart(value = "file") MultipartFile file) throws Exception {
        if(!isValidAccess(groupName, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Image image = new Image(null, file.getBytes());
        groupService.setGroupImage(groupName, image);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteImage(@PathVariable String groupName) throws Exception {
        if(!isValidAccess(groupName, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        groupService.setGroupImage(groupName, null);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<String>> getGroupsNames() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<String> names = groupService.getGroupsNames();

        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    private boolean isValidAccess(String groupName, Access.Operation operation) throws ServiceException {
        Access<String> access = new Access<>(getCurrentUserName(), operation, groupName);
        return permissionService.isValidAccess(access);
    }

    private String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
