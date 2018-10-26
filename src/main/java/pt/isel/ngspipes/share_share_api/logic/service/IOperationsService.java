package pt.isel.ngspipes.share_share_api.logic.service;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryMetadata;

@Service
public interface IOperationsService {

    void createUser(User user) throws ServiceException;

    void deleteUser(String userName) throws ServiceException;

    void deleteGroup(String groupName) throws ServiceException;

    void deleteInternalRepository(int repositoryId) throws ServiceException;

    void createInternalRepository(RepositoryMetadata repository) throws ServiceException;

    void deleteInternalRepositoryUserMember(int memberId) throws ServiceException;

}
