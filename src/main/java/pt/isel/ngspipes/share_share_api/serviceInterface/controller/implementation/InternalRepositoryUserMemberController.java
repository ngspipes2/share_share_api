package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.IPermissionService;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryUserMember;
import pt.isel.ngspipes.share_dynamic_repository.logic.service.repositoryUserMember.IRepositoryUserMemberService;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IInternalRepositoryUserMemberController;

import java.util.Collection;

@RestController
public class InternalRepositoryUserMemberController implements IInternalRepositoryUserMemberController {

    @Autowired
    private IRepositoryUserMemberService repositoryUserMemberService;
    @Autowired
    private IPermissionService<RepositoryUserMember, Integer> permissionService;



    @Override
    public ResponseEntity<Collection<RepositoryUserMember>> getAll() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryUserMember> members = repositoryUserMemberService.getAll();

        ControllerUtils.hidePasswordsOfRepositoryUserMembers(members);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RepositoryUserMember> get(@PathVariable Integer memberId) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        RepositoryUserMember member = repositoryUserMemberService.getById(memberId);

        ControllerUtils.hidePasswordOfRepositoryUserMember(member);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> insert(@RequestBody RepositoryUserMember member) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        repositoryUserMemberService.insert(member);

        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable Integer memberId, @RequestBody RepositoryUserMember member) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if(!memberId.equals(member.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        repositoryUserMemberService.update(member);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer memberId) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        repositoryUserMemberService.delete(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<RepositoryUserMember>> getMembersOfRepository(@RequestParam Integer repositoryId) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryUserMember> members = repositoryUserMemberService.getMembersOfRepository(repositoryId);

        ControllerUtils.hidePasswordsOfRepositoryUserMembers(members);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<RepositoryUserMember>> getMembersWithUser(@RequestParam String userName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryUserMember> members = repositoryUserMemberService.getMembersWithUser(userName);

        ControllerUtils.hidePasswordsOfRepositoryUserMembers(members);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    private boolean isValidAccess(Integer memberId, Access.Operation operation) throws ServiceException {
        Access<Integer> access = new Access<>(getCurrentUserName(), operation, memberId);
        return permissionService.isValidAccess(access);
    }

    private String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
