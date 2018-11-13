package pt.isel.ngspipes.share_share_api.logic.operation.repositoryInfo;

import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IRepositoryInfoOperation {

    Collection<RepositoryInfo> getAllRepositories() throws ServiceException;

    RepositoryInfo getRepository(String repositoryName) throws ServiceException;

    void createRepository(RepositoryInfo repository) throws ServiceException;

    void deleteRepository(String repositoryName) throws ServiceException;

    void updateRepository(RepositoryInfo repository) throws ServiceException;

    Collection<RepositoryInfo> getRepositoriesOfUser(String userName) throws ServiceException;

    void deleteRepositoriesOfUser(String userName) throws ServiceException;

    Collection<String> getRepositoriesNames() throws ServiceException;

    RepositoryInfo createDefaultToolsRepository(User user) throws ServiceException;

    RepositoryInfo createDefaultPipelinesRepository(User user) throws ServiceException;

    Collection<RepositoryInfo> getRepositoriesAccessibleByUser(String userName) throws ServiceException;

}
