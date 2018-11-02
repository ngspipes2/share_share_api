package pt.isel.ngspipes.share_share_api.logic.operation.internalRepositoryUserMember;

import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryMetadata;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryUserMember;

import java.util.Collection;

public interface IInternalRepositoryUserMemberOperation {

    Collection<RepositoryUserMember> getAllMembers() throws ServiceException;

    RepositoryUserMember getMember(int id) throws ServiceException;

    void createMember(RepositoryUserMember member) throws ServiceException;

    void deleteMember(int id) throws ServiceException;

    void updateMember(RepositoryUserMember member) throws ServiceException;

    Collection<RepositoryUserMember> getMembersWithUser(String userName) throws ServiceException;

    Collection<RepositoryUserMember> getMembersOfRepository(String repositoryName) throws ServiceException;

    void deleteMembersWithUser(String userName) throws ServiceException;

    void deleteMembersOfRepository(String repositoryName) throws ServiceException;

    RepositoryUserMember createMemberForOwner(RepositoryMetadata repository) throws ServiceException;
}
