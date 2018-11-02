package pt.isel.ngspipes.share_share_api.logic.operation.internalRepositoryGroupMember;

import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryGroupMember;

import java.util.Collection;

public interface IInternalRepositoryGroupMemberOperation {

    Collection<RepositoryGroupMember> getAllMembers() throws ServiceException;

    RepositoryGroupMember getMember(int id) throws ServiceException;

    void createMember(RepositoryGroupMember member) throws ServiceException;

    void deleteMember(int id) throws ServiceException;

    void updateMember(RepositoryGroupMember member) throws ServiceException;

    Collection<RepositoryGroupMember> getMembersWithGroup(String groupName) throws ServiceException;

    Collection<RepositoryGroupMember> getMembersOfRepository(String repositoryName) throws ServiceException;

    void deleteMembersWithGroup(String groupName) throws ServiceException;

    void deleteMembersOfRepository(String repositoryName) throws ServiceException;

}
