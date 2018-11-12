package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryGroupMember;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

import java.util.Collection;

@CrossOrigin
@RestController
public interface IRepositoryGroupMemberController {

    @RequestMapping(value = Routes.GET_ALL_REPOSITORY_GROUP_MEMBERS_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<RepositoryGroupMember>> getAll() throws Exception;

    @RequestMapping(value = Routes.GET_REPOSITORY_GROUP_MEMBER_ROUTE, method = RequestMethod.GET)
    ResponseEntity<RepositoryGroupMember> get(@PathVariable Integer memberId) throws Exception;

    @RequestMapping(value = Routes.CREATE_REPOSITORY_GROUP_MEMBER_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Integer> insert(@RequestBody RepositoryGroupMember member) throws Exception;

    @RequestMapping(value = Routes.UPDATE_REPOSITORY_GROUP_MEMBER_ROUTE, method = RequestMethod.PUT)
    ResponseEntity<Void> update(@PathVariable Integer memberId, @RequestBody RepositoryGroupMember member) throws Exception;

    @RequestMapping(value = Routes.DELETE_REPOSITORY_GROUP_MEMBER_ROUTE, method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@PathVariable Integer memberId) throws Exception;

    @RequestMapping(value = Routes.GET_ALL_REPOSITORY_GROUP_MEMBERS_ROUTE, method = RequestMethod.GET, params = "repositoryName")
    ResponseEntity<Collection<RepositoryGroupMember>> getMembersOfRepository(@RequestParam String repositoryName) throws Exception;

    @RequestMapping(value = Routes.GET_ALL_REPOSITORY_GROUP_MEMBERS_ROUTE, method = RequestMethod.GET, params = "groupName")
    ResponseEntity<Collection<RepositoryGroupMember>> getMembersWithGroup(@RequestParam String groupName) throws Exception;

}
