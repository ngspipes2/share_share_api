package pt.isel.ngspipes.share_share_api.logic.operation.repositoryUserMember;

import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryUserMember;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IRepositoryUserMemberOperation {

    Collection<RepositoryUserMember> getAllMembers() throws ServiceException;

    RepositoryUserMember getMember(int id) throws ServiceException;

    void createMember(RepositoryUserMember member) throws ServiceException;

    void deleteMember(int id) throws ServiceException;

    void updateMember(RepositoryUserMember member) throws ServiceException;

    Collection<RepositoryUserMember> getMembersWithUser(String userName) throws ServiceException;

    Collection<RepositoryUserMember> getMembersOfRepository(String repositoryName) throws ServiceException;

    void deleteMembersWithUser(String userName) throws ServiceException;

    void deleteMembersOfRepository(String repositoryName) throws ServiceException;

    RepositoryUserMember createMemberForOwner(RepositoryInfo repository) throws ServiceException;

}
