package pt.isel.ngspipes.share_share_api.logic.operation.repositoryInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.*;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.repositoryGroupMember.IRepositoryGroupMemberService;
import pt.isel.ngspipes.share_core.logic.service.repositoryInfo.IRepositoryInfoService;
import pt.isel.ngspipes.share_core.logic.service.repositoryUserMember.IRepositoryUserMemberService;
import pt.isel.ngspipes.share_share_api.logic.operation.group.IGroupOperation;
import pt.isel.ngspipes.share_share_api.logic.operation.repositoryUserMember.IRepositoryUserMemberOperation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
public class RepositoryInfoOperation implements IRepositoryInfoOperation {

    private static final String DEFAULT_TOOLS_REPOSITORY_KEY = "Tools";
    private static final String DEFAULT_PIPELINES_REPOSITORY_KEY = "Pipelines";


    @Autowired
    private IRepositoryInfoService repositoryService;
    @Autowired
    private IGroupOperation groupOperation;
    @Autowired
    private IRepositoryGroupMemberService repositoryGroupMemberService;
    @Autowired
    private IRepositoryUserMemberService repositoryUserMemberService;
    @Autowired
    private IRepositoryUserMemberOperation repositoryUserMemberOperation;



    @Override
    public Collection<RepositoryInfo> getAllRepositories() throws ServiceException {
        return repositoryService.getAll();
    }

    @Override
    public RepositoryInfo getRepository(String repositoryName) throws ServiceException {
        return repositoryService.getById(repositoryName);
    }

    @Override
    @Transactional
    public void createRepository(RepositoryInfo repository) throws ServiceException {
        if(repository.getRepositoryName().contains(DEFAULT_TOOLS_REPOSITORY_KEY) || repository.getRepositoryName().contains(DEFAULT_PIPELINES_REPOSITORY_KEY))
            throw new ServiceException("'" + DEFAULT_TOOLS_REPOSITORY_KEY + "' and '" + DEFAULT_PIPELINES_REPOSITORY_KEY + "' are reserved words for groupName!");

        repositoryService.insert(repository);
        repositoryUserMemberOperation.createMemberForOwner(repository);
    }

    @Override
    @Transactional
    public void deleteRepository(String repositoryName) throws ServiceException {
        repositoryUserMemberService.deleteMembersOfRepository(repositoryName);
        repositoryGroupMemberService.deleteMembersOfRepository(repositoryName);
        repositoryService.delete(repositoryName);
    }

    @Override
    public void updateRepository(RepositoryInfo repository) throws ServiceException {
        repositoryService.update(repository);
    }

    @Override
    public Collection<RepositoryInfo> getRepositoriesOfUser(String userName) throws ServiceException {
        return repositoryService.getRepositoriesOfUser(userName);
    }

    @Override
    public void deleteRepositoriesOfUser(String userName) throws ServiceException {
        repositoryService.deleteRepositoriesOfUser(userName);
    }

    @Override
    public Collection<String> getRepositoriesNames() throws ServiceException {
        return repositoryService.getRepositoriesNames();
    }

    @Override
    public RepositoryInfo createDefaultToolsRepository(User user) throws ServiceException {
        RepositoryInfo repository = new RepositoryInfo();

        repository.setRepositoryName(user.getUserName() + "_" + DEFAULT_TOOLS_REPOSITORY_KEY);
        repository.setEntityType(RepositoryInfo.EntityType.TOOLS);
        repository.setLocationType(RepositoryInfo.LocationType.INTERNAL);
        repository.setOwner(user);
        repository.setPublic(true);

        repositoryService.insert(repository);
        repositoryUserMemberOperation.createMemberForOwner(repository);

        return repository;
    }

    @Override
    public RepositoryInfo createDefaultPipelinesRepository(User user) throws ServiceException {
        RepositoryInfo repository = new RepositoryInfo();

        repository.setRepositoryName(user.getUserName() + "_" + DEFAULT_PIPELINES_REPOSITORY_KEY);
        repository.setEntityType(RepositoryInfo.EntityType.PIPELINES);
        repository.setLocationType(RepositoryInfo.LocationType.INTERNAL);
        repository.setOwner(user);
        repository.setPublic(true);

        repositoryService.insert(repository);
        repositoryUserMemberOperation.createMemberForOwner(repository);

        return repository;
    }

    @Override
    public Collection<RepositoryInfo> getRepositoriesAccessibleByUser(String userName) throws ServiceException {
        Collection<RepositoryInfo> repositories = new LinkedList<>();

        repositories.addAll(
            this.repositoryUserMemberOperation.getMembersWithUser(userName)
            .stream()
            .map(RepositoryUserMember::getRepository)
            .collect(Collectors.toList())
        );

        Collection<String> groupsAccessibleByUser = this.groupOperation.getGroupsAccessibleByUser(userName)
            .stream()
            .map(Group::getGroupName)
            .collect(Collectors.toList());

        for(String groupName : groupsAccessibleByUser)
            repositories.addAll(
                this.repositoryGroupMemberService.getMembersWithGroup(groupName)
                .stream()
                .map(RepositoryGroupMember::getRepository)
                .collect(Collectors.toList())
            );

        return removeDuplicates(repositories);
    }

    private Collection<RepositoryInfo> removeDuplicates(Collection<RepositoryInfo> repositories) {
        Collection<String> seen = new LinkedList<>();

        return repositories
                .stream()
                .filter((repository) -> {
                    if(seen.contains(repository.getRepositoryName()))
                        return false;

                    seen.add(repository.getRepositoryName());
                    return true;
                })
                .collect(Collectors.toList());
    }

}
