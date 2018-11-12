package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryUserMember;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.IPermissionService;
import pt.isel.ngspipes.share_share_api.logic.operation.repositoryUserMember.IRepositoryUserMemberOperation;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IRepositoryUserMemberController;

import java.util.Collection;

@RestController
public class RepositoryUserMemberController implements IRepositoryUserMemberController {

    @Autowired
    private IRepositoryUserMemberOperation repositoryUserMemberOperation;
    @Autowired
    private IPermissionService<RepositoryUserMember, Integer> permissionService;



    @Override
    public ResponseEntity<Collection<RepositoryUserMember>> getAll() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryUserMember> members = repositoryUserMemberOperation.getAllMembers();

        ControllerUtils.hidePasswordsOfRepositoryUserMembers(members);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RepositoryUserMember> get(@PathVariable Integer memberId) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        RepositoryUserMember member = repositoryUserMemberOperation.getMember(memberId);

        ControllerUtils.hidePasswordOfRepositoryUserMember(member);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> insert(@RequestBody RepositoryUserMember member) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        repositoryUserMemberOperation.createMember(member);

        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable Integer memberId, @RequestBody RepositoryUserMember member) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if(!memberId.equals(member.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        repositoryUserMemberOperation.updateMember(member);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer memberId) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        repositoryUserMemberOperation.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<RepositoryUserMember>> getMembersOfRepository(@RequestParam String repositoryName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryUserMember> members = repositoryUserMemberOperation.getMembersOfRepository(repositoryName);

        ControllerUtils.hidePasswordsOfRepositoryUserMembers(members);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<RepositoryUserMember>> getMembersWithUser(@RequestParam String userName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryUserMember> members = repositoryUserMemberOperation.getMembersWithUser(userName);

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
