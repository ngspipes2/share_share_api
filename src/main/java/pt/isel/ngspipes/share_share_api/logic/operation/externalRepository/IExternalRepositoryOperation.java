package pt.isel.ngspipes.share_share_api.logic.operation.externalRepository;

import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_publish_repository.logic.domain.PublishedRepository;

import java.util.Collection;

public interface IExternalRepositoryOperation {

    Collection<PublishedRepository> getAllRepositories() throws ServiceException;

    PublishedRepository getRepository(String repositoryName) throws ServiceException;

    void createRepository(PublishedRepository repository) throws ServiceException;

    void deleteRepository(String repositoryName) throws ServiceException;

    void updateRepository(PublishedRepository repository) throws ServiceException;

    Collection<PublishedRepository> getRepositoriesOfUser(String userName) throws ServiceException;

    void deleteRepositoriesOfUser(String userName) throws ServiceException;

    Collection<String> getRepositoriesNames() throws ServiceException;

}
