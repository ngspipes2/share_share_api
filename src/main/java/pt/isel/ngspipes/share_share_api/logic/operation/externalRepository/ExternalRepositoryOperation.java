package pt.isel.ngspipes.share_share_api.logic.operation.externalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_publish_repository.logic.domain.PublishedRepository;
import pt.isel.ngspipes.share_publish_repository.logic.service.publishedRepository.IPublishedRepositoryService;

import java.util.Collection;

@Service
public class ExternalRepositoryOperation implements IExternalRepositoryOperation {

    private static final String DEFAULT_TOOLS_REPOSITORY_KEY = "Tools";
    private static final String DEFAULT_PIPELINES_REPOSITORY_KEY = "Pipelines";



    @Autowired
    private IPublishedRepositoryService repositoryService;



    @Override
    public Collection<PublishedRepository> getAllRepositories() throws ServiceException {
        return repositoryService.getAll();
    }

    @Override
    public PublishedRepository getRepository(String repositoryName) throws ServiceException {
        return repositoryService.getById(repositoryName);
    }

    @Override
    public void createRepository(PublishedRepository repository) throws ServiceException {
        if(repository.getRepositoryName().contains(DEFAULT_TOOLS_REPOSITORY_KEY) || repository.getRepositoryName().contains(DEFAULT_PIPELINES_REPOSITORY_KEY))
            throw new ServiceException("'" + DEFAULT_TOOLS_REPOSITORY_KEY + "' and '" + DEFAULT_PIPELINES_REPOSITORY_KEY + "' are reserved words for groupName!");

        repositoryService.insert(repository);
    }

    @Override
    public void deleteRepository(String repositoryName) throws ServiceException {
        repositoryService.delete(repositoryName);
    }

    @Override
    public void updateRepository(PublishedRepository repository) throws ServiceException {
        repositoryService.update(repository);
    }

    @Override
    public Collection<PublishedRepository> getRepositoriesOfUser(String userName) throws ServiceException {
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

}
