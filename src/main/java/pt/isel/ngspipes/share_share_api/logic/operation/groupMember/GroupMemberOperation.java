package pt.isel.ngspipes.share_share_api.logic.operation.groupMember;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.GroupMember;
import pt.isel.ngspipes.share_core.logic.service.exceptions.NonExistentEntityException;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.groupMember.IGroupMemberService;

import java.util.Collection;

@Service
public class GroupMemberOperation implements IGroupMemberOperation {

    @Autowired
    private IGroupMemberService memberService;



    @Override
    public Collection<GroupMember> getAllMembers() throws ServiceException {
        return memberService.getAll();
    }

    @Override
    public GroupMember getMember(int id) throws ServiceException {
        return memberService.getById(id);
    }

    @Override
    public void createMember(GroupMember member) throws ServiceException {
        memberService.insert(member);
    }

    @Override
    @Transactional
    public void deleteMember(int id) throws ServiceException {
        GroupMember member = memberService.getById(id);

        if(member == null)
            throw new NonExistentEntityException("Non existent Group Member with id:" + id);

        Group group = member.getGroup();
        if(group.getOwner().getUserName().equals(member.getUser().getUserName()))
            throw new ServiceException("Group Member representing owner of group cannot be deleted!");

        memberService.delete(id);
    }
    @Override
    public void updateMember(GroupMember member) throws ServiceException {
        memberService.update(member);
    }

    @Override
    public Collection<GroupMember> getMembersWithUser(String userName) throws ServiceException {
        return memberService.getMembersWithUser(userName);
    }

    @Override
    public Collection<GroupMember> getMembersOfGroup(String groupName) throws ServiceException {
        return memberService.getMembersOfGroup(groupName);
    }

    @Override
    public void deleteMembersWithUser(String userName) throws ServiceException {
        memberService.deleteMembersWithUser(userName);
    }

    @Override
    public void deleteMembersOfGroup(String groupName) throws ServiceException {
        memberService.deleteMembersOfGroup(groupName);
    }

    @Override
    public GroupMember createMemberForOwner(Group group) throws ServiceException {
        GroupMember member = new GroupMember();
        member.setGroup(group);
        member.setUser(group.getOwner());
        member.setWriteAccess(true);

        memberService.insert(member);

        return member;
    }

}
