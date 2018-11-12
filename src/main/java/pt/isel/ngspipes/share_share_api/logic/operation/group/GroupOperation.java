package pt.isel.ngspipes.share_share_api.logic.operation.group;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.GroupMember;
import pt.isel.ngspipes.share_core.logic.domain.Image;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.group.IGroupService;
import pt.isel.ngspipes.share_core.logic.service.groupMember.IGroupMemberService;
import pt.isel.ngspipes.share_core.logic.service.repositoryGroupMember.IRepositoryGroupMemberService;
import pt.isel.ngspipes.share_share_api.logic.operation.groupMember.IGroupMemberOperation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
public class GroupOperation implements IGroupOperation {

    @Autowired
    private IGroupService groupService;
    @Autowired
    private IRepositoryGroupMemberService repositoryGroupMemberService;
    @Autowired
    private IGroupMemberService groupMemberService;
    @Autowired
    private IGroupMemberOperation groupMemberOperation;



    @Override
    public Collection<Group> getAllGroups() throws ServiceException {
        return groupService.getAll();
    }

    @Override
    public Group getGroup(String groupName) throws ServiceException {
        return groupService.getById(groupName);
    }

    @Override
    @Transactional
    public void createGroup(Group group) throws ServiceException {
        groupService.insert(group);
        groupMemberOperation.createMemberForOwner(group);
    }

    @Override
    @Transactional
    public void deleteGroup(String groupName) throws ServiceException {
        repositoryGroupMemberService.deleteMembersWithGroup(groupName);
        groupMemberService.deleteMembersOfGroup(groupName);
        groupService.delete(groupName);
    }

    @Override
    public void updateGroup(Group group) throws ServiceException {
        groupService.update(group);
    }

    @Override
    public Collection<Group> getGroupsOfUser(String userName) throws ServiceException {
        return groupService.getGroupsOfUser(userName);
    }

    @Override
    public Collection<String> getGroupsNames() throws ServiceException {
        return groupService.getGroupsNames();
    }

    @Override
    public Image getGroupImage(String groupName) throws ServiceException {
        return groupService.getGroupImage(groupName);
    }

    @Override
    public void setGroupImage(String groupName, Image image) throws ServiceException {
        groupService.setGroupImage(groupName, image);
    }

    @Override
    public void deleteGroupsOfUser(String userName) throws ServiceException {
        groupService.deleteGroupsOfUser(userName);
    }

    @Override
    @Transactional
    public Collection<Group> getGroupsAccessibleByUser(String userName) throws ServiceException {
        Collection<Group> groups = new LinkedList<>();

        Collection<Group> ownerOf = groupService.getGroupsOfUser(userName);
        Collection<Group> memberOf = groupMemberService.getMembersWithUser(userName)
                .stream()
                .map(GroupMember::getGroup)
                .collect(Collectors.toList());

        groups.addAll(ownerOf);
        groups.addAll(memberOf);

        return removeDuplicates(groups);
    }

    private Collection<Group> removeDuplicates(Collection<Group> groups) {
        Collection<String> seen = new LinkedList<>();

        return groups
                .stream()
                .filter((group) -> {
                    if(seen.contains(group.getGroupName()))
                        return false;

                    seen.add(group.getGroupName());
                    return true;
                })
                .collect(Collectors.toList());
    }

}
