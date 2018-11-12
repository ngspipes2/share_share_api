package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryGroupMember;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.IPermissionService;
import pt.isel.ngspipes.share_share_api.logic.operation.repositoryGroupMember.IRepositoryGroupMemberOperation;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IRepositoryGroupMemberController;

import java.util.Collection;

@RestController
public class RepositoryGroupMemberController implements IRepositoryGroupMemberController {

    @Autowired
    private IRepositoryGroupMemberOperation repositoryGroupMemberOperation;
    @Autowired
    private IPermissionService<RepositoryGroupMember, Integer> permissionService;



    @Override
    public ResponseEntity<Collection<RepositoryGroupMember>> getAll() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryGroupMember> members = repositoryGroupMemberOperation.getAllMembers();

        ControllerUtils.hidePasswordsOfRepositoryGroupMembers(members);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RepositoryGroupMember> get(@PathVariable Integer memberId) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        RepositoryGroupMember member = repositoryGroupMemberOperation.getMember(memberId);

        ControllerUtils.hidePasswordOfRepositoryGroupMember(member);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> insert(@RequestBody RepositoryGroupMember member) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        repositoryGroupMemberOperation.createMember(member);

        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable Integer memberId, @RequestBody RepositoryGroupMember member) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if(!memberId.equals(member.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        repositoryGroupMemberOperation.updateMember(member);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer memberId) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        repositoryGroupMemberOperation.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<RepositoryGroupMember>> getMembersOfRepository(@RequestParam String repositoryName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryGroupMember> members = repositoryGroupMemberOperation.getMembersOfRepository(repositoryName);

        ControllerUtils.hidePasswordsOfRepositoryGroupMembers(members);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<RepositoryGroupMember>> getMembersWithGroup(@RequestParam String groupName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryGroupMember> members = repositoryGroupMemberOperation.getMembersWithGroup(groupName);

        ControllerUtils.hidePasswordsOfRepositoryGroupMembers(members);

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
