package pt.isel.ngspipes.share_share_api.logic.operation.internalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryMetadata;
import pt.isel.ngspipes.share_dynamic_repository.logic.service.repositoryGroupMember.IRepositoryGroupMemberService;
import pt.isel.ngspipes.share_dynamic_repository.logic.service.repositoryMetadata.IRepositoryMetadataService;
import pt.isel.ngspipes.share_dynamic_repository.logic.service.repositoryUserMember.IRepositoryUserMemberService;
import pt.isel.ngspipes.share_share_api.logic.operation.internalRepositoryUserMember.IInternalRepositoryUserMemberOperation;

import java.util.Collection;

@Service
public class InternalRepositoryOperation implements IInternalRepositoryOperation {

    private static final String DEFAULT_TOOLS_REPOSITORY_KEY = "Tools";
    private static final String DEFAULT_PIPELINES_REPOSITORY_KEY = "Pipelines";



    @Autowired
    private IRepositoryMetadataService repositoryService;
    @Autowired
    private IRepositoryGroupMemberService internalRepositoryGroupMemberService;
    @Autowired
    private IRepositoryUserMemberService internalRepositoryUserMemberService;
    @Autowired
    private IInternalRepositoryUserMemberOperation internalRepositoryUserMemberOperation;



    @Override
    public Collection<RepositoryMetadata> getAllRepositories() throws ServiceException {
        return repositoryService.getAll();
    }

    @Override
    public RepositoryMetadata getRepository(String repositoryName) throws ServiceException {
        return repositoryService.getById(repositoryName);
    }

    @Override
    @Transactional
    public void createRepository(RepositoryMetadata repository) throws ServiceException {
        if(repository.getRepositoryName().contains(DEFAULT_TOOLS_REPOSITORY_KEY) || repository.getRepositoryName().contains(DEFAULT_PIPELINES_REPOSITORY_KEY))
            throw new ServiceException("'" + DEFAULT_TOOLS_REPOSITORY_KEY + "' and '" + DEFAULT_PIPELINES_REPOSITORY_KEY + "' are reserved words for groupName!");

        repositoryService.insert(repository);
        internalRepositoryUserMemberOperation.createMemberForOwner(repository);
    }

    @Override
    @Transactional
    public void deleteRepository(String repositoryName) throws ServiceException {
        internalRepositoryUserMemberService.deleteMembersOfRepository(repositoryName);
        internalRepositoryGroupMemberService.deleteMembersOfRepository(repositoryName);
        repositoryService.delete(repositoryName);
    }

    @Override
    public void updateRepository(RepositoryMetadata repository) throws ServiceException {
        repositoryService.update(repository);
    }

    @Override
    public Collection<RepositoryMetadata> getRepositoriesOfUser(String userName) throws ServiceException {
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
    public RepositoryMetadata createDefaultToolsRepository(User user) throws ServiceException {
        RepositoryMetadata repository = new RepositoryMetadata();

        repository.setRepositoryName(user.getUserName() + "_" + DEFAULT_TOOLS_REPOSITORY_KEY);
        repository.setType(RepositoryMetadata.Type.TOOLS);
        repository.setOwner(user);
        repository.setPublic(true);

        repositoryService.insert(repository);

        return repository;
    }

    @Override
    public RepositoryMetadata createDefaultPipelinesRepository(User user) throws ServiceException {
        RepositoryMetadata repository = new RepositoryMetadata();

        repository.setRepositoryName(user.getUserName() + "_" + DEFAULT_PIPELINES_REPOSITORY_KEY);
        repository.setType(RepositoryMetadata.Type.PIPELINES);
        repository.setOwner(user);
        repository.setPublic(true);

        repositoryService.insert(repository);

        return repository;
    }

}
