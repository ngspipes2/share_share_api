package pt.isel.ngspipes.share_share_api.logic.operation.repositoryInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.repositoryGroupMember.IRepositoryGroupMemberService;
import pt.isel.ngspipes.share_core.logic.service.repositoryInfo.IRepositoryInfoService;
import pt.isel.ngspipes.share_core.logic.service.repositoryUserMember.IRepositoryUserMemberService;
import pt.isel.ngspipes.share_share_api.logic.operation.repositoryUserMember.IRepositoryUserMemberOperation;

import java.util.Collection;

@Service
public class RepositoryInfoOperation implements IRepositoryInfoOperation {

    private static final String DEFAULT_TOOLS_REPOSITORY_KEY = "Tools";
    private static final String DEFAULT_PIPELINES_REPOSITORY_KEY = "Pipelines";


    @Autowired
    private IRepositoryInfoService repositoryService;
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

        return repository;
    }

    @Override
    public RepositoryInfo createDefaultPipelinesRepository(User user) throws ServiceException {
        RepositoryInfo repository = new RepositoryInfo();

        repository.setRepositoryName(user.getUserName() + "_" + DEFAULT_PIPELINES_REPOSITORY_KEY);
        repository.setEntityType(RepositoryInfo.EntityType.TOOLS);
        repository.setLocationType(RepositoryInfo.LocationType.INTERNAL);
        repository.setOwner(user);
        repository.setPublic(true);

        repositoryService.insert(repository);

        return repository;
    }

}
