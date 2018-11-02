package pt.isel.ngspipes.share_share_api.logic.operation.groupMember;


import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.GroupMember;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IGroupMemberOperation {

    Collection<GroupMember> getAllMembers() throws ServiceException;

    GroupMember getMember(int id) throws ServiceException;

    void createMember(GroupMember member) throws ServiceException;

    void deleteMember(int id) throws ServiceException;

    void updateMember(GroupMember member) throws ServiceException;

    Collection<GroupMember> getMembersWithUser(String userName) throws ServiceException;

    Collection<GroupMember> getMembersOfGroup(String groupName) throws ServiceException;

    void deleteMembersWithUser(String userName) throws ServiceException;

    void deleteMembersOfGroup(String groupName) throws ServiceException;

    GroupMember createMemberForOwner(Group group) throws ServiceException;
}
