package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

import java.util.Collection;

@CrossOrigin
@RestController
public interface IGroupController {

    @RequestMapping(value = Routes.GET_ALL_GROUPS_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<Group>> getAll() throws Exception;

    @RequestMapping(value = Routes.GET_GROUP_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Group> get(@PathVariable String groupName) throws Exception;

    @RequestMapping(value = Routes.CREATE_GROUP_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Void> insert(@RequestBody Group group) throws Exception;

    @RequestMapping(value = Routes.UPDATE_GROUP_ROUTE, method = RequestMethod.PUT)
    ResponseEntity<Void> update(@PathVariable String groupName, @RequestBody Group group) throws Exception;

    @RequestMapping(value = Routes.DELETE_GROUP_ROUTE, method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@PathVariable String groupName) throws Exception;

    @RequestMapping(value = Routes.GET_ALL_GROUPS_ROUTE, method = RequestMethod.GET, params = "userName")
    ResponseEntity<Collection<Group>> getGroupsOfUser(@RequestParam String userName) throws Exception;

    @RequestMapping(value = Routes.GET_GROUP_IMAGE_ROUTE, method = RequestMethod.GET)
    ResponseEntity<byte[]> getImage(@PathVariable String groupName) throws Exception;

    @RequestMapping(value = Routes.CHANGE_GROUP_IMAGE_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Void> changeImage(@PathVariable String groupName, @RequestPart(value = "file") MultipartFile file) throws Exception;

    @RequestMapping(value = Routes.GET_GROUPS_NAMES_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<String>> getGroupsNames() throws Exception;

}
