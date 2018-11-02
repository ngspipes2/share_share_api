package pt.isel.ngspipes.share_share_api.logic.operation.group;


import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.Image;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IGroupOperation {

    Collection<Group> getAllGroups() throws ServiceException;

    Group getGroup(String groupName) throws ServiceException;

    void createGroup(Group group) throws ServiceException;

    void deleteGroup(String groupName) throws ServiceException;

    void updateGroup(Group group) throws ServiceException;

    Collection<Group> getGroupsOfUser(String userName) throws ServiceException;

    Collection<String> getGroupsNames() throws ServiceException;

    Image getGroupImage(String groupName) throws ServiceException;

    void setGroupImage(String groupName, Image image) throws ServiceException;

    void deleteGroupsOfUser(String userName) throws ServiceException;

    Collection<Group> getGroupsAccessibleByUser(String userName) throws ServiceException;

}
