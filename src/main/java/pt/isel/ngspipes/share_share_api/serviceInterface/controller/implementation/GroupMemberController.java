package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.share_core.logic.domain.GroupMember;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.IPermissionService;
import pt.isel.ngspipes.share_share_api.logic.operation.groupMember.IGroupMemberOperation;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IGroupMemberController;

import java.util.Collection;

@RestController
public class GroupMemberController implements IGroupMemberController {

    @Autowired
    private IGroupMemberOperation groupMemberOperation;
    @Autowired
    private IPermissionService<GroupMember, Integer> permissionService;



    @Override
    public ResponseEntity<Collection<GroupMember>> getAll() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<GroupMember> members = groupMemberOperation.getAllMembers();

        ControllerUtils.hidePasswordsOfGroupMembers(members);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GroupMember> get(@PathVariable Integer memberId) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        GroupMember member = groupMemberOperation.getMember(memberId);

        ControllerUtils.hidePasswordOfGroupMember(member);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> insert(@RequestBody GroupMember member) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        groupMemberOperation.createMember(member);

        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable Integer memberId, @RequestBody GroupMember member) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if(!memberId.equals(member.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        groupMemberOperation.updateMember(member);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer memberId) throws Exception {
        if(!isValidAccess(memberId, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        groupMemberOperation.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<GroupMember>> getMembersOfGroup(@RequestParam String groupName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<GroupMember> members = groupMemberOperation.getMembersOfGroup(groupName);

        ControllerUtils.hidePasswordsOfGroupMembers(members);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<GroupMember>> getMembersWithUser(@RequestParam String userName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<GroupMember> members = groupMemberOperation.getMembersWithUser(userName);

        ControllerUtils.hidePasswordsOfGroupMembers(members);

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
