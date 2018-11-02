package pt.isel.ngspipes.share_share_api.logic.operation.internalRepositoryUserMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.service.exceptions.NonExistentEntityException;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryMetadata;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryUserMember;
import pt.isel.ngspipes.share_dynamic_repository.logic.service.repositoryUserMember.IRepositoryUserMemberService;

import java.util.Collection;

@Service
public class InternalRepositoryUserMemberOperation implements IInternalRepositoryUserMemberOperation {

    @Autowired
    private IRepositoryUserMemberService memberService;



    @Override
    public Collection<RepositoryUserMember> getAllMembers() throws ServiceException {
        return memberService.getAll();
    }

    @Override
    public RepositoryUserMember getMember(int id) throws ServiceException {
        return memberService.getById(id);
    }

    @Override
    public void createMember(RepositoryUserMember member) throws ServiceException {
        memberService.insert(member);
    }

    @Override
    @Transactional
    public void deleteMember(int id) throws ServiceException {
        RepositoryUserMember member = memberService.getById(id);

        if(member == null)
            throw new NonExistentEntityException("Non existent User Member with id:" + id);

        RepositoryMetadata repository = member.getRepository();
        if(repository.getOwner().getUserName().equals(member.getUser().getUserName()))
            throw new ServiceException("User Member representing owner of repository cannot be deleted!");

        memberService.delete(id);
    }

    @Override
    public void updateMember(RepositoryUserMember member) throws ServiceException {
        memberService.update(member);
    }

    @Override
    public Collection<RepositoryUserMember> getMembersWithUser(String userName) throws ServiceException {
        return memberService.getMembersWithUser(userName);
    }

    @Override
    public Collection<RepositoryUserMember> getMembersOfRepository(String repositoryName) throws ServiceException {
        return memberService.getMembersOfRepository(repositoryName);
    }

    @Override
    public void deleteMembersWithUser(String userName) throws ServiceException {
        memberService.deleteMembersWithUser(userName);
    }

    @Override
    public void deleteMembersOfRepository(String repositoryName) throws ServiceException {
        memberService.deleteMembersOfRepository(repositoryName);
    }

    @Override
    public RepositoryUserMember createMemberForOwner(RepositoryMetadata repository) throws ServiceException {
        RepositoryUserMember member = new RepositoryUserMember();
        member.setRepository(repository);
        member.setUser(repository.getOwner());
        member.setWriteAccess(true);

        memberService.insert(member);

        return member;
    }

}
