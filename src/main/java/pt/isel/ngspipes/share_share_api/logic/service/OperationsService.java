package pt.isel.ngspipes.share_share_api.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.accessToken.IAccessTokenService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.group.IGroupService;
import pt.isel.ngspipes.share_core.logic.service.groupMember.IGroupMemberService;
import pt.isel.ngspipes.share_core.logic.service.user.IUserService;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryMetadata;
import pt.isel.ngspipes.share_dynamic_repository.logic.service.repositoryGroupMember.IRepositoryGroupMemberService;
import pt.isel.ngspipes.share_dynamic_repository.logic.service.repositoryMetadata.IRepositoryMetadataService;
import pt.isel.ngspipes.share_dynamic_repository.logic.service.repositoryUserMember.IRepositoryUserMemberService;
import pt.isel.ngspipes.share_publish_repository.logic.service.publishedRepository.IPublishedRepositoryService;

import java.util.LinkedList;
import java.util.List;

@Service
public class OperationsService implements IOperationsService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserService userService;
    @Autowired
    private IAccessTokenService accessTokenService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IGroupMemberService groupMemberService;
    @Autowired
    private IPublishedRepositoryService publishedRepositoryService;
    @Autowired
    private IRepositoryMetadataService repositoryMetadataService;
    @Autowired
    private IRepositoryUserMemberService repositoryUserMemberService;
    @Autowired
    private IRepositoryGroupMemberService repositoryGroupMemberService;

    @Value("${tools.server.url}")
    private String toolsServerUrl;
    @Value("${pipelines.server.url}")
    private String pipelinesServerUrl;



    @Override
    @Transactional
    public void createUser(User user) throws ServiceException {
        userService.insert(user);

        setCurrentUserContext(user);

        createDefaultToolsRepository(user);
        createDefaultPipelinesRepository(user);
    }

    private void setCurrentUserContext(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();

        List<GrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userName, password, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void createDefaultToolsRepository(User user) throws ServiceException {
        RepositoryMetadata repository = new RepositoryMetadata();

        repository.setType(RepositoryMetadata.Type.TOOLS);
        repository.setOwner(user);
        repository.setName("Default Tools Repository");
        repository.setPublic(true);

        repositoryMetadataService.insert(repository);
    }

    private void createDefaultPipelinesRepository(User user) throws ServiceException {
        RepositoryMetadata repository = new RepositoryMetadata();

        repository.setType(RepositoryMetadata.Type.PIPELINES);
        repository.setOwner(user);
        repository.setName("Default Pipelines Repository");
        repository.setPublic(true);

        repositoryMetadataService.insert(repository);
    }

    @Override
    @Transactional
    public void deleteUser(String userName) throws ServiceException {
        accessTokenService.deleteTokensOfUser(userName);
        groupMemberService.deleteMembersWithUser(userName);
        publishedRepositoryService.deleteRepositoriesOfUser(userName);
        repositoryMetadataService.deleteRepositoriesOfUser(userName);
        repositoryUserMemberService.deleteMembersWithUser(userName);

        for(Group group : groupService.getGroupsOfUser(userName))
            deleteGroup(group.getGroupName());
    }

    @Override
    @Transactional
    public void deleteGroup(String groupName) throws ServiceException {
        repositoryGroupMemberService.deleteMembersWithGroup(groupName);
        groupMemberService.deleteMembersOfGroup(groupName);
        groupService.delete(groupName);
    }

    @Override
    @Transactional
    public void deleteInternalRepository(int repositoryId) throws ServiceException {
        repositoryUserMemberService.deleteMembersOfRepository(repositoryId);
        repositoryGroupMemberService.deleteMembersOfRepository(repositoryId);
        repositoryMetadataService.delete(repositoryId);
    }

}
