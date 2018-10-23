package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isel.ngspipes.share_core.logic.domain.GroupMember;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

import java.util.Collection;

@CrossOrigin
@RestController
public interface IGroupMemberController {

    @RequestMapping(value = Routes.GET_ALL_GROUP_MEMBERS_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<GroupMember>> getAll() throws Exception;

    @RequestMapping(value = Routes.GET_GROUP_MEMBER_ROUTE, method = RequestMethod.GET)
    ResponseEntity<GroupMember> get(@PathVariable Integer memberId) throws Exception;

    @RequestMapping(value = Routes.CREATE_GROUP_MEMBER_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Integer> insert(@RequestBody GroupMember member) throws Exception;

    @RequestMapping(value = Routes.UPDATE_GROUP_MEMBER_ROUTE, method = RequestMethod.PUT)
    ResponseEntity<Void> update(@PathVariable Integer memberId, @RequestBody GroupMember member) throws Exception;

    @RequestMapping(value = Routes.DELETE_GROUP_MEMBER_ROUTE, method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@PathVariable Integer memberId) throws Exception;

    @RequestMapping(value = Routes.GET_ALL_GROUP_MEMBERS_ROUTE, method = RequestMethod.GET, params = "groupName")
    ResponseEntity<Collection<GroupMember>> getMembersOfGroup(@RequestParam String groupName) throws Exception;

    @RequestMapping(value = Routes.GET_ALL_GROUP_MEMBERS_ROUTE, method = RequestMethod.GET, params = "userName")
    ResponseEntity<Collection<GroupMember>> getMembersWithUser(@RequestParam String userName) throws Exception;

}
