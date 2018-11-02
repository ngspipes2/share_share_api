package pt.isel.ngspipes.share_share_api.logic.operation.internalRepository;

import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryMetadata;

import java.util.Collection;

public interface IInternalRepositoryOperation {

    Collection<RepositoryMetadata> getAllRepositories() throws ServiceException;

    RepositoryMetadata getRepository(String repositoryName) throws ServiceException;

    void createRepository(RepositoryMetadata repository) throws ServiceException;

    void deleteRepository(String repositoryName) throws ServiceException;

    void updateRepository(RepositoryMetadata repository) throws ServiceException;

    Collection<RepositoryMetadata> getRepositoriesOfUser(String userName) throws ServiceException;

    void deleteRepositoriesOfUser(String userName) throws ServiceException;

    Collection<String> getRepositoriesNames() throws ServiceException;

    RepositoryMetadata createDefaultToolsRepository(User user) throws ServiceException;

    RepositoryMetadata createDefaultPipelinesRepository(User user) throws ServiceException;

}
