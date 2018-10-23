package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryUserMember;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

import java.util.Collection;

@CrossOrigin
@RestController
public interface IInternalRepositoryUserMemberController {

    @RequestMapping(value = Routes.GET_ALL_INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<RepositoryUserMember>> getAll() throws Exception;

    @RequestMapping(value = Routes.GET_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE, method = RequestMethod.GET)
    ResponseEntity<RepositoryUserMember> get(@PathVariable Integer memberId) throws Exception;

    @RequestMapping(value = Routes.CREATE_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Integer> insert(@RequestBody RepositoryUserMember member) throws Exception;

    @RequestMapping(value = Routes.UPDATE_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE, method = RequestMethod.PUT)
    ResponseEntity<Void> update(@PathVariable Integer memberId, @RequestBody RepositoryUserMember member) throws Exception;

    @RequestMapping(value = Routes.DELETE_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE, method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@PathVariable Integer memberId) throws Exception;

    @RequestMapping(value = Routes.GET_ALL_INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE, method = RequestMethod.GET, params = "repositoryId")
    ResponseEntity<Collection<RepositoryUserMember>> getMembersOfRepository(@RequestParam Integer repositoryId) throws Exception;

    @RequestMapping(value = Routes.GET_ALL_INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE, method = RequestMethod.GET, params = "userName")
    ResponseEntity<Collection<RepositoryUserMember>> getMembersWithUser(@RequestParam String userName) throws Exception;

}
