package pt.isel.ngspipes.share_share_api.logic.operation.user;


import pt.isel.ngspipes.share_core.logic.domain.Image;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IUserOperation {

    Collection<User> getAllUsers() throws ServiceException;

    User getUser(String userName) throws ServiceException;

    void createUser(User user) throws ServiceException;

    void deleteUser(String userName) throws ServiceException;

    void updateUser(User user) throws ServiceException;

    void changePassword(String userName, String currentPassword, String newPassword) throws ServiceException;

    Collection<String> getUsersNames() throws ServiceException;

    Image getUserImage(String userName) throws ServiceException;

    void setUserImage(String userName, Image image) throws ServiceException;

}
