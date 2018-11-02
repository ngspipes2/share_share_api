package pt.isel.ngspipes.share_share_api.logic.operation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.Image;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.accessToken.IAccessTokenService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.groupMember.IGroupMemberService;
import pt.isel.ngspipes.share_core.logic.service.user.IUserService;
import pt.isel.ngspipes.share_dynamic_repository.logic.service.repositoryUserMember.IRepositoryUserMemberService;
import pt.isel.ngspipes.share_share_api.logic.operation.externalRepository.IExternalRepositoryOperation;
import pt.isel.ngspipes.share_share_api.logic.operation.group.IGroupOperation;
import pt.isel.ngspipes.share_share_api.logic.operation.internalRepository.IInternalRepositoryOperation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserOperation implements IUserOperation{

    @Autowired
    private IUserService userService;
    @Autowired
    private IAccessTokenService tokenService;
    @Autowired
    private IGroupOperation groupOperation;
    @Autowired
    private IGroupMemberService groupMemberService;
    @Autowired
    private IInternalRepositoryOperation internalRepositoryOperation;
    @Autowired
    private IExternalRepositoryOperation externalRepositoryOperation;
    @Autowired
    private IRepositoryUserMemberService internalRepositoryUserMemberService;



    @Override
    public Collection<User> getAllUsers() throws ServiceException {
        return userService.getAll();
    }

    @Override
    public User getUser(String userName) throws ServiceException {
        return userService.getById(userName);
    }

    @Override
    @Transactional
    public void createUser(User user) throws ServiceException {
        userService.insert(user);

        setCurrentUserContext(user);

        internalRepositoryOperation.createDefaultToolsRepository(user);
        internalRepositoryOperation.createDefaultPipelinesRepository(user);
    }

    private void setCurrentUserContext(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();

        List<GrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userName, password, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    @Transactional
    public void deleteUser(String userName) throws ServiceException {
        tokenService.deleteTokensOfUser(userName);
        groupMemberService.deleteMembersWithUser(userName);
        externalRepositoryOperation.deleteRepositoriesOfUser(userName);
        internalRepositoryOperation.deleteRepositoriesOfUser(userName);
        internalRepositoryUserMemberService.deleteMembersWithUser(userName);

        for(Group group : groupOperation.getGroupsOfUser(userName))
            groupOperation.deleteGroup(group.getGroupName());

        userService.delete(userName);
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        userService.update(user);
    }

    @Override
    public void changePassword(String userName, String currentPassword, String newPassword) throws ServiceException {
        userService.changePassword(userName, currentPassword, newPassword);
    }

    @Override
    public Collection<String> getUsersNames() throws ServiceException {
        return userService.getUsersNames();
    }

    @Override
    public Image getUserImage(String userName) throws ServiceException {
        return userService.getUserImage(userName);
    }

    @Override
    public void setUserImage(String userName, Image image) throws ServiceException {
        userService.setUserImage(userName, image);
    }

}
